package com.forezp.servicehi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceHiApplication.class, args);
  }

  @Value("${server.port}")
  private String port;
  @GetMapping("/hi")
  public String test(@RequestParam("name") String name){
    return "hi " + name + " ,i am from port:" + port;
  }

}
