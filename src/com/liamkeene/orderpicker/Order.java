package com.liamkeene.orderpicker;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Order implements Parcelable {
    private String id;
    private String name;
    private String date;

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

    @Override
    public int describeContents() {
        // Describe special contents
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Flatten the attributes that we require to a Parcel
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(date);
    }

    public static final Parcelable.Creator<Order> CREATOR
            = new Parcelable.Creator<Order>() {
        // Creator that generates instances of Orders from a parcel
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    private Order(Parcel in) {
        // Constructor to create an Order from a Parcel
        // Attributes are read in the order they were written to the Parcel
        id = in.readString();
        name = in.readString();
        date = in.readString();
    }
}
