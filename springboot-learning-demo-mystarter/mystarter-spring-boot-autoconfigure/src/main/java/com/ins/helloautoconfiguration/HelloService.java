package com.ins.helloautoconfiguration;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class HelloService {

    private List<String> members;

    /**
     * say hello to everyone
     */
    public void sayHello(){
        members.forEach(s -> System.out.println("hello " + s));
    }

}
