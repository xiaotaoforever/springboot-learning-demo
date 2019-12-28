package com.ins.mystarterdemo.demo;

import com.ins.helloautoconfiguration.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class DemoController {
    @Autowired
    HelloService helloService;

    @RequestMapping("/demo/1")
    public String demo1(HttpServletRequest request, @RequestParam String uid, HttpServletResponse response) {
        return "aaaaa";
    }

    @RequestMapping("/demo/2")
    public String demo2(HttpServletRequest request, @RequestParam String uid, HttpServletResponse response) {
        helloService.sayHello();
        return "aaaaa";
    }

}
