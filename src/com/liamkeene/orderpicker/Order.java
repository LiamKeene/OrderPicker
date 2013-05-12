package com.liamkeene.orderpicker;

import org.json.JSONException;
import org.json.JSONObject;

public class Order {
    String id;
    String name;
    String date;

    Order(String id, String name, String date) {
        // Explicity set the attributes
        this.id = id;
        this.name = name;
        this.date = date;
    }

    Order(JSONObject jsonObject) {
        // Set attributes from JSONObject - no type checking
        try {
            this.id = jsonObject.getString("id");
            this.name = jsonObject.getString("name");
            this.date = jsonObject.getString("date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Order " + this.id;
    }
}
