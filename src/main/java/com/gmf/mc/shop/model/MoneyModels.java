package com.gmf.mc.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class MoneyModels {

    @AllArgsConstructor
    @Data
    @XmlRootElement(name = "pay")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Pay {
        String from;
        String to;
        double count;

        @Override
        public String toString() {

            return "{from : " + this.from + ",\n" +
                    "to : " + this.to + ",\n" +
                    "count : " + this.count + "\n}";
        }
    }

    @AllArgsConstructor
    @Data
    @XmlRootElement(name = "balance")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Balance {
        String from;

        @Override
        public String toString(){
            return "{from : " + this.from + "\n}";
        }
    }
}
