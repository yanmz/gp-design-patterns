package com.design.factory.protype;

import com.alibaba.fastjson.JSON;


public class CloneUser extends AbstarctCloneUser {

    @Override
    public User deepClone(User user) {
        String json = JSON.toJSONString(user);
        return JSON.parseObject(json, User.class);
    }
}
