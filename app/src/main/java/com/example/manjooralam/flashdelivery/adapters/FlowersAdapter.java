package com.example.manjooralam.flashdelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.FruitsAndVegitableModel;
import com.example.manjooralam.flashdelivery.models.FruitsModel;

import java.util.List;

public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.MyViewHolder> {

    private  Context context;
    private List<FruitsModel> flowersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvQuantity, tvOriginalPrice, tvDiscountPrice, tvCount;
        public ImageView ivAdd, ivSubstract,ivItemImage;
        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvQuantity = (TextView) view.findViewById(R.id.tv_quantity);
            tvDiscountPrice = (TextView) view.findViewById(R.id.tv_discounted_price);
            tvOriginalPrice = view.findViewById(R.id.tv_original_price);
            tvCount = (TextView) view.findViewById(R.id.tv_count);
            ivAdd = view.findViewById(R.id.iv_plus);
            ivSubstract = view.findViewById(R.id.iv_minus);
            ivItemImage=view.findViewById(R.id.image_product);
        }
    }


    public FlowersAdapter(Context context, List<FruitsModel> flowersList) {
        this.context = context;
        this.flowersList = flowersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

        Glide.with(context).load(flowersList.get(position).imageUrl).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivItemImage);

        holder.tvName.setText(flowersList.get(position).itemName);
        holder.tvQuantity.setText(flowersList.get(position).itemQuantity);
        holder.tvDiscountPrice.setText(flowersList.get(position).itemDiscountPrice + context.getResources().getString(R.string.rs));
        holder.tvOriginalPrice.setText(flowersList.get(position).itemOriginalPrice + context.getResources().getString(R.string.rs));
        holder.ivSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flowersList.get(position).count == 1){

                }else {
                    flowersList.get(position).count = flowersList.get(position).count - 1;
                    if(flowersList.get(position).count == 1){
                        holder.ivSubstract.setImageResource(R.drawable.ic_substract_disable);
                    }
                    holder.ivAdd.setImageResource(R.drawable.ic_add);
                    holder.tvCount.setText(String.valueOf(flowersList.get(position).count));
                    notifyDataSetChanged();
                }
            }
        });

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flowersList.get(position).count == Integer.valueOf(flowersList.get(position).maxItemCount)){

                }else {
                    flowersList.get(position).count = flowersList.get(position).count + 1;
                    if(flowersList.get(position).count == Integer.valueOf(flowersList.get(position).maxItemCount)){
                        holder.ivAdd.setImageResource(R.drawable.ic_add_disable);
                    }
                    holder.ivSubstract.setImageResource(R.drawable.icon_substract);
                    holder.tvCount.setText(String.valueOf(flowersList.get(position).count));
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //.  return newArrivalItemList.size();
        return  flowersList.size();
    }
}