package org.schhx.springbootlearn.controller;

import org.schhx.springbootlearn.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/baseException")
    public void baseException(){
        throw new NotFoundException("对象不存在");
    }

    @GetMapping("/exception")
    public void exception(){
        throw new RuntimeException("RuntimeException");
    }

}
