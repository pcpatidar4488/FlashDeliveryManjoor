package com.example.manjooralam.flashdelivery.adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.FruitsAndVegitableModel;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {

    private  Context context;
    private List<FruitsAndVegitableModel> newArrivalItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
        }
    }


    public OrderHistoryAdapter(Context context, List<FruitsAndVegitableModel> newArrivalItemList) {
        this.context = context;
        this.newArrivalItemList = newArrivalItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderHistoryAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //.  return newArrivalItemList.size();
        return  10;
    }
}