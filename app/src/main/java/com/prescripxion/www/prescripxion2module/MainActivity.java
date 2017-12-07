package com.prescripxion.www.prescripxion2module;

import android.content.Intent;
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
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import s.ashiqur.lib.SwipeableRecyclerViewTouchListener;

//For Excel Reading
//For Card View



public class MainActivity extends AppCompatActivity {


    //CardView Codes:
    Button buttonCart;
    ///Declaration Of Recycler Variables;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mItems;

    //Declaration of Medicine Variables:
    public static final int NUMBER_OF_MEDICINES=11;
    public String[] medNamesData=new String[NUMBER_OF_MEDICINES];
    public double[] medPriceData=new double[NUMBER_OF_MEDICINES];
    public static String[] addedToCart=new String[NUMBER_OF_MEDICINES];
    ArrayList<String> mArrayListData = new ArrayList<String>();
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reading Medicine Names From Excel File
        getMedNamesData(view,medNamesData);
        getMedPriceData(view,medPriceData);
        //AddtoCart Codes

        buttonCart=(Button)findViewById(R.id.button_cart);
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCart = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intentCart);


            }
        });


        ///RecyclerView Codes:
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        for( String e : medNamesData)
        {
            if(e != null)
            {
                mArrayListData.add(e);
            }
        }
        mAdapter = new MyAdapter(mArrayListData);
        mRecyclerView.setAdapter(mAdapter);



        //TODO: RecyclerView Will Be Visible Only if Click is Detected on SearchBar
        //TODO: Change Recycler View Bg Color If Needed(I Suck At Colors :3 )
        mItems = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            mItems.add(String.format("Card number %02d", i));
        }
        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                    addedToCart[position]=null;
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                   addedToCart[position]=medNamesData[position];


                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

        //SearchBar Codes Start here

        //TODO: Searchbar Using Searchview and AutoCompletion


        ///Bottom Navigation Bar Codes:
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




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
                //Retreives Med Power

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
