package com.forezp.servicefeign.test;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: SchedualServiceHi
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/4/26 16:35
 */


@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

  @GetMapping("/hi")
  String sayHiFromClientOne(@RequestParam String name);
}
