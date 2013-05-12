package com.liamkeene.orderpicker;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liamkeene.orderpicker.Order;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderListFragment extends ListFragment {
    //
    private List<String> ordersArray = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;

    // Implement the OrderList class
    private final String[] orderList = {"Order 1", "Order 2", "Order 3", };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the latest orders asynchronously
        new LatestOrdersAsyncTask().execute();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        OrderDetailFragment viewer = (OrderDetailFragment) getFragmentManager()
            .findFragmentById(R.id.order_detail);

        if (viewer == null || !viewer.isInLayout()) {
            Intent launchingIntent = new Intent(getActivity(),
                OrderDetailActivity.class);
            launchingIntent.putExtra("ORDER", orderList[position]);
            startActivity(launchingIntent);
        } else {
            viewer.populateOrderDetails(orderList[position]);
        }
    }

    private class LatestOrdersAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            // Any code to execute before fetching the data
        }

        @Override
        protected String doInBackground(String... params) {
            String response = "";

            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://wms.liamkeene.com/api/orders");

            try {
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                // Create a JSON array
                JSONArray jsonOrders = new JSONArray(result);

                // Get each JSON Object and add to the array
                for (int i=0; i < jsonOrders.length(); i++) {
                    // Create an Order
                    Order order = new Order(jsonOrders.getJSONObject(i));
                    ordersArray.add(order.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Create an adapter and add the orders to it
            arrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, ordersArray
            );
            setListAdapter(arrayAdapter);
        }
    }
}
