package com.prescripxion.www.prescripxion2module;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    final int NUMBER_OF_MEDICINES=7;
    Medicine[] medList=new Medicine[NUMBER_OF_MEDICINES];
    String[] medNamesData=new String[NUMBER_OF_MEDICINES];
    Button buttonCart;


    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///ListView Codes:
        initialiseMeds(medList,medNamesData);
        final ListView listViewMedicines=(ListView)findViewById(R.id.listview_medicines);
        ArrayAdapter medNameDataAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,medNamesData);
        listViewMedicines.setAdapter(medNameDataAdapter);
        listViewMedicines.setVisibility(View.VISIBLE);
        //TODO: Listview Items Need to be Slideable cards
        //TODO: listView Will Be Visible Only if Click is Detected on SearchBar

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


    private void initialiseMeds(Medicine [] medList,String[] medNamesData)
    {
        medList[0]=new Medicine("Ace",20.50);
        medNamesData[0]=medList[0].getName();

        medList[1]=new Medicine("Deslor",15.50);
        medNamesData[1]=medList[1].getName();

        medList[2]=new Medicine("Reservix",12.25);
        medNamesData[2]=medList[2].getName();

        medList[3]=new Medicine("Azmasol",230.25);
        medNamesData[3]=medList[3].getName();

        medList[4]=new Medicine("Galvus Met A",525.00);
        medNamesData[4]=medList[4].getName();

        medList[5]=new Medicine("Benadryl",23.0);
        medNamesData[5]=medList[5].getName();

        medList[6]=new Medicine("Coversyl Plus",23.0);
        medNamesData[6]=medList[6].getName();



        Arrays.sort(medNamesData);

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
