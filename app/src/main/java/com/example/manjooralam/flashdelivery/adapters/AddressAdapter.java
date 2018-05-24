package com.example.manjooralam.flashdelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.activities.AddAdressActivity;
import com.example.manjooralam.flashdelivery.models.NewArrivalsModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    private  Context context;
    private List<NewArrivalsModel> newArrivalItemList;
    private TextView edit_tv,delete_tv;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
        }
    }


    public AddressAdapter(Context context, List<NewArrivalsModel> newArrivalItemList) {
        this.context = context;
        this.newArrivalItemList = newArrivalItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_address_book, parent, false);
        edit_tv=itemView.findViewById(R.id.tv_edit);
        delete_tv=itemView.findViewById(R.id.tv_delete);
        edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, AddAdressActivity.class);
                context.startActivity(intent);
            }
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        //.  return newArrivalItemList.size();
        return  10;
    }
}