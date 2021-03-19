package com.gjs.fixedassets.entity;


import java.util.HashMap;
import java.util.Map;

public enum FixedStatus {
    USEING(2, "使用中"),
    NOTUSE(1, "闲置"),
    STOPUSING(3, "报废"),
    MAINTAIN(4, "维修中");

    private int code;
    private String name;

    FixedStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getValueByCode(int code) {
        for (FixedStatus fixedStatus : values()) {
            if (fixedStatus.getCode() == code) {
                return fixedStatus.getName();
            }
        }
        return null;
    }

    public static Map<Integer, String> toStatusMap() {
        Map<Integer, String> map = new HashMap<>();
        for (FixedStatus fixdStatus : values()) {
            map.put(fixdStatus.code, fixdStatus.name);
        }
        return map;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
