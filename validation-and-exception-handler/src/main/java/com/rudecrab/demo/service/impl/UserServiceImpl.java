package com.rudecrab.demo.service.impl;

import com.rudecrab.demo.entity.User;
import com.rudecrab.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/4 9:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String addUser(User user) {
        return "success";
    }
}
