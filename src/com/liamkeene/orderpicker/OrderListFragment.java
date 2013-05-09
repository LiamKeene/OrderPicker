package com.liamkeene.orderpicker;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OrderListFragment extends ListFragment
{
    // Implement the OrderList class
    private final String[] orderList = {"Order 1", "Order 2", "Order 3", };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter(getActivity().getApplicationContext(),
            android.R.layout.simple_list_item_activated_1, orderList));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        OrderDetailFragment viewer = (OrderDetailFragment) getFragmentManager()
            .findFragmentById(R.id.order_detail);

        if (viewer == null || !viewer.isInLayout())
        {
            Intent launchingIntent = new Intent(getActivity(),
                OrderDetailActivity.class);
            launchingIntent.putExtra("ORDER", orderList[position]);
            startActivity(launchingIntent);
        } else
        {
            viewer.populateOrderDetails(orderList[position]);
        }
    }
}
