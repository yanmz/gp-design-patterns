package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/save")
    public Long insert() {
//        User user = new User();
//        user.setNickname("zhangsan"+ new Random().nextInt());
//        user.setPassword("1234567");
//        user.setSex(1);
//        user.setBirthday("2020-03-09");
//        user.setAge(2);

        Person person = new Person();
        person.setNickname("zhangsan"+ new Random().nextInt());
        person.setPassword("1234567");
        person.setSex(1);
        person.setBirthday("2020-03-09");
        person.setAge(2);
        userMapper.addPerson(person);
        return person.getId();
    }
    @GetMapping("/listuser")
    public List<User> listuser() {
        return userMapper.findUsers();
    }
}