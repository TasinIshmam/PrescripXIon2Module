package com.prescripxion.www.prescripxion2module;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Tasin Ishmam on 12/14/2017.
 */

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView medNameTxt,medPriceTxt, medAmountTxt;
    ImageButton itemCancelButton;
    WeakReference<ClickListener> listenerRef;




    public CartViewHolder(View itemView, ClickListener listener) {
        super(itemView);

        listenerRef = new WeakReference<>(listener);
        this.medNameTxt= (TextView) itemView.findViewById(R.id.CartMedNameText);
        this.medPriceTxt= (TextView) itemView.findViewById(R.id.CartMedPriceText);
        this.itemCancelButton= (ImageButton) itemView.findViewById(R.id.CartItemCancelButton);
        this.medAmountTxt = (TextView) itemView.findViewById(R.id.CartMedAmountText);

        itemCancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
