package com.liamkeene.orderpicker;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liamkeene.orderpicker.Order;

public class OrderDetailFragment extends Fragment {
    // Implement the Order class
    TextView orderText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Intent launchingIntent = getActivity().getIntent();
        Order order = launchingIntent.getParcelableExtra("com.liamkeene.orderpicker.Order");
        View viewer = (View) inflater.inflate(
            R.layout.order_detail_fragment, container, false);
        orderText = (TextView) viewer.findViewById(R.id.label_order_name);
        populateOrderDetails(order);
        return viewer;
    }

    public void populateOrderDetails(Order order) {
        orderText.setText(order.toString());
    }
}
