package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    // 主键
    private Long id;
    // 昵称
    private String nickname;
    // 密码
    private String password;
    // 性
    private Integer sex;

    private Integer age;
    // 性
    private String birthday;

}