package com.prescripxion.www.prescripxion2module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CartActivity extends AppCompatActivity {


    ///Declaration Of Recycler Variables For Cart
    private RecyclerView cartRecyclerView;
    private RecyclerView.Adapter cartAdapter;
    private RecyclerView.LayoutManager cartLayoutManager;



    ArrayList<Medicine> medicines;
    TreeMap<Medicine , Integer> addedToCartMap;
    ArrayList<MyPair>  addedToCartArrayList = new ArrayList<MyPair>();

    String [] addedTo=new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ///RecyclerView Codes For Cart:
        cartRecyclerView = (RecyclerView) findViewById(R.id.recyclerCart);
        cartLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(cartLayoutManager);


        Intent activityThatCalled = getIntent();


        addedToCartMap = new TreeMap<Medicine, Integer> ((HashMap<Medicine , Integer>) activityThatCalled.getSerializableExtra("currentCartSelection"));





        for(Map.Entry<Medicine , Integer> entry : addedToCartMap.entrySet())
        {
            MyPair temp = new MyPair(entry.getKey(), entry.getValue());




            addedToCartArrayList.add(temp);

        }







        cartAdapter = new CartAdapter(this, addedToCartArrayList, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {

            }
        });

        cartRecyclerView.setAdapter(cartAdapter);


    }


}
