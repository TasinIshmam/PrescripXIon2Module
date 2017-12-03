package com.prescripxion.www.prescripxion2module;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Arrays;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class MainActivity extends AppCompatActivity {


    Button buttonCart;

    private TextView mTextMessage;
    ///Declaration Of Recycler Variables
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Declaration of Medicine Variables
    public static final int NUMBER_OF_MEDICINES=10;
    public String[] medNamesData=new String[NUMBER_OF_MEDICINES];
    public double[] medPriceData=new double[NUMBER_OF_MEDICINES];
    View view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Reading Medicine Names From Excel File
        getMedNamesData(view,medNamesData);
        getMedPriceData(view,medPriceData);


        ///RecyclerView Codes:
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(medNamesData);
        mRecyclerView.setAdapter(mAdapter);




        //TODO: Recyclerview Items Need to be Slideable cards
        //TODO: RecyclerView Will Be Visible Only if Click is Detected on SearchBar

        //SearchBar Codes Start here

        //TODO: Searchbar Using Searchview and AutoCompletion


        ///Bottom Navigation Bar Codes:
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //AddtoCart Codes
        //TODO:Cart Image Need to be Changed use:https://material.io/icons/
        buttonCart=(Button)findViewById(R.id.button_cart);
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"No Items Added",Toast.LENGTH_LONG).show();
            }
        });






    }

    public void getMedNamesData(View view, String [] medNamesData)
    {

        try {
            AssetManager am=getAssets();
            InputStream is=am.open("new.xls");
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet s=workbook.getSheet(0);
            int row= s.getRows();
            int col= s.getColumns();

            for(int r = 0; r<row; r++)
            {

                    Cell z=s.getCell(0,r);
                    medNamesData[r]=z.getContents();


            }

            for(int r = 0; r<row; r++)
            {

                Cell z=s.getCell(1,r);
                medNamesData[r]+="--"+z.getContents();


            }



        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"No Xls File",Toast.LENGTH_LONG).show();
        }
    }
    public void getMedPriceData(View view, double [] medPriceData)
    {

        try {
            AssetManager am=getAssets();
            InputStream is=am.open("new.xls");
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet s=workbook.getSheet(0);
            int row= s.getRows();
            int col= s.getColumns();
            for(int r = 0; r<row; r++)
            {
                Cell z=s.getCell(2,r);
                medPriceData[r]=Double.parseDouble(z.getContents());
            }


        }
        catch (Exception e)
        {
             Toast.makeText(getApplicationContext(),"No Xls File",Toast.LENGTH_LONG).show();
        }
    }






    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //TODO: GO TO HOME ACTIVITY;
                    return true;
                case R.id.navigation_medication:
                    //TODO: GO TO MEDICINE ACTIVITY;
                    return true;
                case R.id.navigation_profile:
                    //TODO: GO TO PROFILE ACTIVITY;
                    return true;
            }
            return false;
        }

    };



}
