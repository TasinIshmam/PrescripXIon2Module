package com.prescripxion.www.prescripxion2module;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> mArrayListData, filteredList ;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public MyAdapter(ArrayList<String> rhs) {


        mArrayListData = rhs;
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
        return mArrayListData.size();
    }

}