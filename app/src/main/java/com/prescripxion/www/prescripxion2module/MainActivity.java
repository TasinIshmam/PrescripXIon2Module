package com.prescripxion.www.prescripxion2module;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

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
    public MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mItems;
    SearchView searchView;
    MenuItem cartmenu, cameramenu;

    //Declaration of Medicine Variables:
    public static final int NUMBER_OF_MEDICINES=11;
    public String[] medNamesData=new String[NUMBER_OF_MEDICINES];
    public double[] medPriceData=new double[NUMBER_OF_MEDICINES];
    public static ArrayList<String> addedToCart = new ArrayList<String>();
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


        //adding toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);






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
                                    addedToCart.remove(mAdapter.mArrayListData.get(position));
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mItems.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                   addedToCart.add(mAdapter.mArrayListData.get(position));


                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

        //SearchBar Codes Start here

        searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {


                mAdapter.getFilter().filter(query);
                return false;
            }
        });



        //TODO: Searchbar Using Searchview and AutoCompletion


        ///Bottom Navigation Bar Codes:
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_buttons, menu);



       cartmenu = menu.findItem(R.id.button_checkout);
       cameramenu = menu.findItem(R.id.button_camera);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        switch (item.getItemId())
        {
            case R.id.button_checkout:{
                Intent intentCart = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intentCart);
            }

            case R.id.button_camera: {
                //add camera actiivity creation code here.
            }



        }









        return super.onOptionsItemSelected(item);
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
