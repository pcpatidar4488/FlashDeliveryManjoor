package com.example.manjooralam.flashdelivery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.FeaturedProductModel;

import java.util.List;

public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.MyViewHolder> {

    private  Context context;
    private List<FeaturedProductModel> newArrivalItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvQuantity, tvOriginalPrice, tvDiscountPrice, tvCount;
        public ImageView ivAdd, ivSubstract;
        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvQuantity = (TextView) view.findViewById(R.id.tv_quantity);
            tvDiscountPrice = (TextView) view.findViewById(R.id.tv_discounted_price);
            tvOriginalPrice = view.findViewById(R.id.tv_original_price);
            tvCount = (TextView) view.findViewById(R.id.tv_count);
            ivAdd = view.findViewById(R.id.iv_plus);
            ivSubstract = view.findViewById(R.id.iv_minus);

        }
    }


    public FeaturedProductAdapter(Context context, List<FeaturedProductModel> newArrivalItemList) {
        this.context = context;
        this.newArrivalItemList = newArrivalItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
           holder.tvName.setText(newArrivalItemList.get(position).name);
           holder.tvQuantity.setText(newArrivalItemList.get(position).quantity);
           holder.tvDiscountPrice.setText(Long.toString(newArrivalItemList.get(position).discount_price) + context.getResources().getString(R.string.rs));
           holder.tvOriginalPrice.setText(Long.toString(newArrivalItemList.get(position).original_price) + context.getResources().getString(R.string.rs));
           holder.ivSubstract.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(newArrivalItemList.get(position).count == 1){

                   }else {
                       newArrivalItemList.get(position).count = newArrivalItemList.get(position).count - 1;
                       if(newArrivalItemList.get(position).count == 1){
                          holder.ivSubstract.setImageResource(R.drawable.ic_substract_disable);
                       }
                       holder.ivAdd.setImageResource(R.drawable.ic_add);
                       holder.tvCount.setText(String.valueOf(newArrivalItemList.get(position).count));
                       notifyDataSetChanged();
                   }
               }
           });

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newArrivalItemList.get(position).count == newArrivalItemList.get(position).maxCount){

                }else {
                    newArrivalItemList.get(position).count = newArrivalItemList.get(position).count + 1;
                    if(newArrivalItemList.get(position).count == newArrivalItemList.get(position).maxCount){
                        holder.ivAdd.setImageResource(R.drawable.ic_add_disable);
                    }
                    holder.ivSubstract.setImageResource(R.drawable.icon_substract);
                    holder.tvCount.setText(String.valueOf(newArrivalItemList.get(position).count));
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
      //.  return newArrivalItemList.size();
        return  newArrivalItemList.size();
    }
}