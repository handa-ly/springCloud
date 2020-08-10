package com.forezp.servicefeign.test;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
    *@ClassName: HiController
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/26 16:37
    */
    
@RestController
public class HiController {

  @Resource
  SchedualServiceHi schedualServiceHi;

  @GetMapping("/hi")
  public String test(@RequestParam(value = "name",required = false,defaultValue = "默认值") String name){
    return schedualServiceHi.sayHiFromClientOne(name);
  }
}
