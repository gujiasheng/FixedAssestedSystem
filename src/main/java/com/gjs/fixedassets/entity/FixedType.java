package com.gjs.fixedassets.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FixedType {
    BOOKS(5, "图书"),
    BUILDING(1, "房屋和建筑物"),
    EXHIBITS(4, "文物和陈列品"),
    MECHANICALEQUIP(7, "机械设备"),
    OFFICEEQUIP(2, "一般办公设备"),
    OTHERS(8, "其他固定资产"),
    SPECICALEQUIP(3, "专用设备"),
    TRANSPORTEQUIP(6, "运输设备");

    private int code;
    private String name;

    FixedType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    //根据不同code获取name
    public static String getValueByCode(int code) {
        for (FixedType fixedType : values()) {
            if (fixedType.getCode() == code) {
                return fixedType.getName();
            }
        }
        return null;
    }

    //把枚举类变成list
    public static Map<Integer, String> toList() {
        Map<Integer, String> map = new HashMap<>();
        for (FixedType fixedType : values()) {
            map.put(fixedType.code, fixedType.name);
        }

        return map;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
//测试枚举
//    public static void main(String[] args) {
//        int code=1;
//        String value ;
//        switch (code){
//            case  1:
//                value=FixedType.getValueByCode(code);//BUILDING
//                System.out.println(value);
//                break;
//            case 2:
//                value=FixedType.getValueByCode(code);//BUILDING
//                System.out.println(value);
//                break;
//        }
//        toList();
//    }
}
