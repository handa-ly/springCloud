package com.forezp.servicefeign.test;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
    *@ClassName: SchedualServiceHiHystric
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/26 17:16
    */
    
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
  @Override
  public String sayHiFromClientOne(@RequestParam String name) {
    return "sorry! error";
  }
}
