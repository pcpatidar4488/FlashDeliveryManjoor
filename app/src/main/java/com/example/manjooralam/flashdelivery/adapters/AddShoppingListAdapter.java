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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.models.FruitsModel;
import com.example.manjooralam.flashdelivery.models.ShoppingListModel;

import java.util.List;

public class AddShoppingListAdapter extends RecyclerView.Adapter<AddShoppingListAdapter.MyViewHolder> {

    private  Context context;
    private List<String> shopingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAddListName,tvRemoveList;
        public MyViewHolder(View view) {
            super(view);
            tvAddListName = (TextView) view.findViewById(R.id.tv_listname);
            tvRemoveList = (TextView) view.findViewById(R.id.remove);

        }
    }


    public AddShoppingListAdapter(Context context, List<String> shopingList) {
        this.context = context;
        this.shopingList = shopingList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_addshoping_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.tvAddListName.setText(shopingList.get(position));
        holder.tvRemoveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        //.  return newArrivalItemList.size();
        return  shopingList.size();
    }
}