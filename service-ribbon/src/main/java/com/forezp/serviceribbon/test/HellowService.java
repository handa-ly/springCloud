package com.forezp.serviceribbon.test;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
    *@ClassName: HellowService
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/26 16:14
    */
    

@Service
public class HellowService {
  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "hiError")
  public String hiService(String name){
    return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
  }

  public String hiError(String name){
    return "sorry error in port:"+name;
  }
}
