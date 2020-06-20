package com.example.demo.controller;

import com.example.demo.service.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuishuoguo
 * Created on 2020/6/17.
 */
@RestController
@AllArgsConstructor
public class DemoController {

    private DemoService service;


    @GetMapping("/demo")
    public void demo() {
        service.demo("1");
    }
}
