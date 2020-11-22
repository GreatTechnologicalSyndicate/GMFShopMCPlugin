package com.gmf.mc.shop.event;

import lombok.Getter;

@Getter
public enum MoneyEventType {
    BALANCE("balance"),
    PAY("pay"),
    RATING("rating");
    private final String name;
    MoneyEventType(String name){
        this.name = name;
    }
}
