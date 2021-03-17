package com.gjs.fixedassets.entity;

import java.util.HashMap;
import java.util.Map;

public enum Unit {
    SET(1, "套"),
    PCS(2, "只"),
    PIECE(3, "片"),
    SQM(4, "m²"),
    CAR(5, "辆");


    private Integer code;
    private String name;

    private Unit(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Map<Integer, String> toUnitMap() {
        Map<Integer, String> map = new HashMap<>();
        for (Unit unit : values()) {
            map.put(unit.code, unit.name);
        }
        return map;
    }
}
