package com.example.manjooralam.flashdelivery.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.DryFruitQuantity;
import com.example.manjooralam.flashdelivery.models.FruitsModel;
import com.example.manjooralam.flashdelivery.models.QuantityBean;

import java.util.List;

public class PackSizeAdapter extends RecyclerView.Adapter<PackSizeAdapter.MyViewHolder> {

    private  Context context;
    private List<QuantityBean> priceQuantityList;
    private TextView tvQuantity, tvDiscountPrice, tvOriginalPrice;
    private Dialog dialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rlContainerPriceQuantity;
        public TextView tvPrice, tvQuantity;
        public MyViewHolder(View view) {
            super(view);
            tvQuantity = (TextView) view.findViewById(R.id.tv_quantity);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            rlContainerPriceQuantity = view.findViewById(R.id.rl_container_price_quantity);
        }
    }


    public PackSizeAdapter(Context context, List<QuantityBean> priceQuantityList, TextView tvQuantity, TextView tvDiscountPrice, TextView tvOriginalPrice, Dialog dialog) {
        this.context = context;
        this.priceQuantityList = priceQuantityList;
        this.tvQuantity = tvQuantity;
        this.tvDiscountPrice = tvDiscountPrice;
        this.tvOriginalPrice = tvOriginalPrice;
        this.dialog = dialog;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_price_quantity, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

        holder.tvPrice.setText(priceQuantityList.get(position).itemDiscountPrice);
        holder.tvQuantity.setText(priceQuantityList.get(position).quantity);

        holder.rlContainerPriceQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvQuantity.setText(priceQuantityList.get(position).quantity);
                tvOriginalPrice.setText(priceQuantityList.get(position).itemOriginalPrice + context.getResources().getString(R.string.rs));
                tvDiscountPrice.setText(priceQuantityList.get(position).itemDiscountPrice + context.getResources().getString(R.string.rs));
                dialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return  priceQuantityList.size();
    }
}