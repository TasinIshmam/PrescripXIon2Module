package com.prescripxion.www.prescripxion2module;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
/**
 * Created by Hp on 3/17/2016.
 */
public class MyMainAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable{
    Context c;
    ArrayList<Medicine> medicineList ,filterList;
    NewCustomFilter filter;
    public MyMainAdapter(Context ctx,ArrayList<Medicine> medicineList)
    {
        this.c=ctx;
        this.medicineList=medicineList;
        this.filterList=medicineList;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW ONBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        //HOLDER
        MyHolder holder=new MyHolder(v);
        return holder;
    }
    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //BIND DATA
        holder.medNameTxt.setText(medicineList.get(position).getName());
        holder.medDetailsTxt.setText(medicineList.get(position).getDetails());
       // holder.itemCheckoutButton.setImageResource(medicineList.get(position).getImg());
        //IMPLEMENT CLICK LISTENET

    }
    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return medicineList.size();
    }
    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new NewCustomFilter(filterList,this);
        }
        return filter;
    }
}