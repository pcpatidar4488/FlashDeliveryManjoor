package com.example.manjooralam.flashdelivery.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.DryFruitQuantity;
import com.example.manjooralam.flashdelivery.models.DryFruitsModel;
import com.example.manjooralam.flashdelivery.models.FruitsAndVegitableModel;
import com.example.manjooralam.flashdelivery.models.FruitsModel;
import com.example.manjooralam.flashdelivery.models.QuantityBean;
import com.example.manjooralam.flashdelivery.models.offers_response.OffersModel;

import java.util.ArrayList;
import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private  Context context;
    private List<OffersModel> offersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvQuantity, tvOriginalPrice, tvDiscountPrice, tvCount;
        public ImageView ivAdd, ivSubstract,ivItemImage;
        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvQuantity = (TextView) view.findViewById(R.id.tv_weight);
            tvDiscountPrice = (TextView) view.findViewById(R.id.tv_discounted_price);
            tvOriginalPrice = view.findViewById(R.id.tv_original_price);
            tvCount = (TextView) view.findViewById(R.id.tv_count);
            ivAdd = view.findViewById(R.id.iv_plus);
            ivSubstract = view.findViewById(R.id.iv_minus);
            ivItemImage=view.findViewById(R.id.image_product);
        }
    }


    public OffersAdapter(Context context, List<OffersModel> offersList) {
        this.context = context;
        this.offersList = offersList;
    }

    @Override
    public OffersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowlist_item2, parent, false);

        return new OffersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OffersAdapter.MyViewHolder holder, final int position) {

        Glide.with(context).load(offersList.get(position).imageUrl).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivItemImage);

        holder.tvName.setText(offersList.get(position).itemName);
        holder.tvQuantity.setText(offersList.get(position).itemQuantity.get(0).itemDiscountPrice);
        holder.tvDiscountPrice.setText(offersList.get(position).itemDiscountPrice + context.getResources().getString(R.string.rs));
        holder.tvOriginalPrice.setText(offersList.get(position).itemOriginalPrice + context.getResources().getString(R.string.rs));
        holder.ivSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(offersList.get(position).count == 1){

                }else {
                    offersList.get(position).count = offersList.get(position).count - 1;
                    if(offersList.get(position).count == 1){
                        holder.ivSubstract.setImageResource(R.drawable.ic_substract_disable);
                    }
                    holder.ivAdd.setImageResource(R.drawable.ic_add);
                    holder.tvCount.setText(String.valueOf(offersList.get(position).count));
                    notifyDataSetChanged();
                }
            }
        });

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(offersList.get(position).count == Integer.valueOf(offersList.get(position).maxItemCount)){

                }else {
                    offersList.get(position).count = offersList.get(position).count + 1;
                    if(offersList.get(position).count == Integer.valueOf(offersList.get(position).maxItemCount)){
                        holder.ivAdd.setImageResource(R.drawable.ic_add_disable);
                    }
                    holder.ivSubstract.setImageResource(R.drawable.icon_substract);
                    holder.tvCount.setText(String.valueOf(offersList.get(position).count));
                    notifyDataSetChanged();
                }
            }
        });


        holder.tvQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(offersList.get(position).itemQuantity, holder.tvQuantity, holder.tvDiscountPrice, holder.tvOriginalPrice);
            }
        });
    }

    @Override
    public int getItemCount() {
        //.  return newArrivalItemList.size();
        return  offersList.size();
    }




    private void openDialog(ArrayList<QuantityBean> itemQuantity, TextView tvQuantity, TextView tvDiscountPrice, TextView tvOriginalPrice) {
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_pack_size);
        final RecyclerView rvPackSize = dialog.findViewById(R.id.rv_pack_size);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.show();

        rvPackSize.setLayoutManager(new LinearLayoutManager(context));
        PackSizeAdapter packSizeAdapter = new PackSizeAdapter(context, itemQuantity, tvQuantity, tvDiscountPrice, tvOriginalPrice, dialog);
        rvPackSize.setAdapter(packSizeAdapter);
    }
}