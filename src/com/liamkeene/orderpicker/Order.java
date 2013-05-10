package com.liamkeene.orderpicker;

public class Order {
    String id;
    String name;
    String date;

    Order(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public String toDisplay() {
        return "Order " + this.id;
    }
}
