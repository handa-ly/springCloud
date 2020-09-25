/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cd826dong.clouddemo.product.api;

import com.cd826dong.clouddemo.product.entity.Product;
import com.cd826dong.clouddemo.product.entity.ProductComment;
import com.cd826dong.clouddemo.product.repository.ProductCommentRepository;
import com.cd826dong.clouddemo.product.repository.ProductRepository;
import com.cd826dong.clouddemo.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 产品管理的Endpoint
 *
 * @author CD826
 * @since 1.0.0
 */
@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCommentRepository productCommentRepository;
    @Autowired
    private UserService userService;

    /**
     * 获取产品信息列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> list() {
        return this.productRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product detail(@PathVariable Long id){
        return this.productRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public List<ProductCommentDto> comments(@PathVariable Long id){
        List<ProductComment> commentList = this.productCommentRepository.findByProductIdOrderByCreated(id);
        if (null == commentList || commentList.isEmpty())
            return Collections.emptyList();

        return commentList.stream().map((comment) -> {
            ProductCommentDto dto = new ProductCommentDto(comment);
            dto.setProduct(this.productRepository.findOne(comment.getProductId()));
            dto.setAuthor(this.userService.load(comment.getAuthorId()));
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * FIXME: 这个方法不应该在这里，仅用来演示方便
     * 获取用户信息列表
     * @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<UserDto> userList() {
        return this.userService.findAll();
    }

    /**
     * FIXME: 测试atomic多线程问题
     * 获取用户信息列表
     * @return
     */
    @RequestMapping(value = "/multiThread",method = RequestMethod.GET)
    public String multiThread() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
        List<Map<Long, AtomicInteger>> testList = new ArrayList<>();
        Map<Long,AtomicInteger> map = new HashMap<>();
        map.put(1L,new AtomicInteger(0));
        testList.add(map);
        for (int i =0;i<10000;i++){
            executor.execute(()-> testList.forEach(test-> test.get(1L).incrementAndGet()));
        }
        executor.shutdown();
        while (true){
            if(executor.isTerminated()){
                break;
            }
        }
        return testList.get(0).get(1L).toString();
    }
}
