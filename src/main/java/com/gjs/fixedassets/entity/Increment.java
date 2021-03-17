package com.gjs.fixedassets.entity;

import java.util.HashMap;
import java.util.Map;

public enum Increment {
    BUY(1, "外购"),
    BUILD(2, "自行建造"),
    INVEST(3, "投资者投入"),
    EXCHANGE(4, "非货币性资产交换"),
    RECOMBINATION(5, "债务重组"),
    MERGE(6, "企业合并"),
    RENT(7, "融资租赁");

    private Integer code;
    private String name;

    private Increment(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Map<Integer, String> toIncreMap() {
        Map<Integer, String> map = new HashMap<>();
        for (Increment increment : values()) {
            map.put(increment.code, increment.name);
        }
        return map;

    }
}
