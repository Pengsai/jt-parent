package com.jt.manage;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {

    private String name;
    private int age;
    private Long hight;
    private int sex;
    //省去相应 get/set方法
    // 设置 年龄和性别的拼接，得出相应分组

    public Long getIwantStudent() {
        return Long.valueOf(this.sex + this.age);
    }
}