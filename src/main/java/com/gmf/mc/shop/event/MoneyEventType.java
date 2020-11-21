package com.gmf.mc.shop.event;

import lombok.Getter;

@Getter
public enum MoneyEventType {
    PAY("pay"),
    SET("set"),
    GET("get");
    private String name;
    MoneyEventType(String name){
        this.name = name;
    }
}
