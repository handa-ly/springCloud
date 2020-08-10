package com.forezp.serviceribbon.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
    *@ClassName: HellowController
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/26 16:19
    */
    

@RestController
public class HellowController {
  @Autowired
  private HellowService hellowService;
  @GetMapping("/ss")
  public String test(@RequestParam(value = "name", defaultValue = "默认值") String name){
    return hellowService.hiService(name);
  }
}
