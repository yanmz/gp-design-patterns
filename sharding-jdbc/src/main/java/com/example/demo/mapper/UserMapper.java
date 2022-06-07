package com.example.demo.mapper;

import com.example.demo.entity.Person;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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
    @Insert("insert into user(nickname,password,sex,birthday,age) values(#{nickname},#{password},#{sex},#{birthday},#{age})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Long addUser(User user);

    @Insert("insert into person(nickname,password,sex,birthday,age) values(#{nickname},#{password},#{sex},#{birthday},#{age})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Long addPerson(Person person);

    /**
     * @author 学相伴-飞哥
     * @description 保存用户
     * @params [user]
     * @date 2021/3/10 17:14
     */
    @Select("select * from person")
    List<User> findUsers();
}