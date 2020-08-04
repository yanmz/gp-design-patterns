package com.rudecrab.demo.controller;

import com.rudecrab.demo.entity.User;
import com.rudecrab.demo.exception.APIException;
import com.rudecrab.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/4 9:05
 */

@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/addUser")
//    public String addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
//        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
//        for (ObjectError error : bindingResult.getAllErrors()) {
//            return error.getDefaultMessage();
//        }
//        return userService.addUser(user);
//    }



    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @ApiOperation("获得单个用户")
    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("12345678");
        user.setPassword("12345678");
        user.setEmail("123@qq.com");
        if(1==1) {
//            throw new APIException("报错啦");
        }
        return user;
    }
}
