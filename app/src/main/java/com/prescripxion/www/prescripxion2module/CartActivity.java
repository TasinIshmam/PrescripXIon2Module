package com.prescripxion.www.prescripxion2module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    ///Declaration Of Recycler Variables For Cart
    private RecyclerView cartRecyclerView;
    private RecyclerView.Adapter cartAdapter;
    private RecyclerView.LayoutManager cartLayoutManager;
    ArrayList<String> mArrayListData = new ArrayList<String>();

    String [] addedTo=new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ///RecyclerView Codes For Cart:
        cartRecyclerView = (RecyclerView) findViewById(R.id.recyclerCart);
        cartLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(cartLayoutManager);
        //TODO : Only Add Searched Indexed Strings From Xl file to The Recyler View of Cart
        int i=0;


        for(String s: MainActivity.addedToCart)
        {
            if(s!= null)
            {
                mArrayListData.add(s);
            }

        }




        cartAdapter = new MyAdapter(mArrayListData);

        cartRecyclerView.setAdapter(cartAdapter);


    }


}
