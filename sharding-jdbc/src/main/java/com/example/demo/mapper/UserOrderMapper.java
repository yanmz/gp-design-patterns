package com.example.demo.mapper;

import com.example.demo.entity.UserOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserOrderMapper {
    @Insert("insert into user_order(ordernumber,userid,createTime,yearmonth) values(#{ordernumber},#{userid},#{createTime},#{yearmonth})")
    @Options(useGeneratedKeys = true,keyColumn = "orderid",keyProperty = "orderid")
    void addUserOrder(UserOrder userOrder);
}