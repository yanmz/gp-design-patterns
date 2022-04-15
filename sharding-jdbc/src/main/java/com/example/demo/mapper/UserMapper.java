package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * @author 学相伴-飞哥
     * @description 保存用户
     * @params [user]
     * @date 2021/3/10 17:14
     */
    @Insert("insert into ksd_user(nickname,password,sex,birthday) values(#{nickname},#{password},#{sex},#{birthday})")
    void addUser(User user);
    /**
     * @author 学相伴-飞哥
     * @description 保存用户
     * @params [user]
     * @date 2021/3/10 17:14
     */
    @Select("select * from ksd_user")
    List<User> findUsers();
}