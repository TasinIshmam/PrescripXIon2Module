package com.tutorials.hp.customrecyclerfiltering;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.prescripxion.www.prescripxion2module.R;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyHolder extends RecyclerView.ViewHolder  {
    //OUR VIEWS

    TextView medNameTxt,medDetailsTxt;
    ImageButton itemCheckoutButton;

    public MyHolder(View itemView) {
        super(itemView);
        this.medNameTxt= (TextView) itemView.findViewById(R.id.medNameText);
        this.medDetailsTxt= (TextView) itemView.findViewById(R.id.medDetailsText);
        this.itemCheckoutButton= (ImageButton) itemView.findViewById(R.id.itemCheckoutButton);
        
    }
    /*@Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }*/
}