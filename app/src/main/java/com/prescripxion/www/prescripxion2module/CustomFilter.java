package com.prescripxion.www.prescripxion2module;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Tasin Ishmam on 11/8/2017.
 */


public class CustomFilter extends Filter{
    MyAdapter adapter;
    ArrayList<String> filterList;

    public CustomFilter(ArrayList<String> filterList,MyAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }
    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY



       // Log.e( "TAG" , "Inside the fucking filter, Alhamdulillah for everything");


        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<String> filteredMeds=new ArrayList<String>();


            for (int i=0;i<filterList.size();i++)
            {
                //CHECK

                if(filterList.get(i).toUpperCase().contains(constraint))
                {

                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredMeds.add(filterList.get(i));
                }
            }
            results.count=filteredMeds.size();
            results.values=filteredMeds;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.mArrayListData = (ArrayList<String>) results.values;

        adapter.notifyDataSetChanged();
    }
}