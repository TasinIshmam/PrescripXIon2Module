package com.prescripxion.www.prescripxion2module;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable{

     ArrayList<String> mArrayListData, filterList ;
     CustomFilter filter;

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }

        return filter;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public MyAdapter( ArrayList<String> rhs) {


        this.mArrayListData = rhs;
        this.filterList = rhs;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mArrayListData.get(position));

    }

    @Override
    public int getItemCount() {

        if(mArrayListData != null)

            
        return mArrayListData.size();

        else return 0;

    }


}