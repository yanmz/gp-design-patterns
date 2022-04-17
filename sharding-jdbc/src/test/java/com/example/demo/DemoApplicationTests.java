package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.entity.UserOrder;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = User.builder().build().setAge(4).setNickname("111").setBirthday("2022-10-10").setSex(1).setPassword("12121");
        userMapper.addUser(user);

    }

    @Autowired
    private UserOrderMapper userOrderMapper;
    @Test
    public void orderyearMaster() {
        UserOrder userOrder = new UserOrder();
//        userOrder.setOrderid(10000L);
        userOrder.setCreateTime(new Date());
        userOrder.setOrdernumber("133455678");
        userOrder.setYearmonth("202102");
        userOrder.setUserid(1L);
        userOrderMapper.addUserOrder(userOrder);
    }

}
