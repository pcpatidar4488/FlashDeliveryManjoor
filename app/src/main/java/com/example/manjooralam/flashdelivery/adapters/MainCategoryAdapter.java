package com.example.manjooralam.flashdelivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.activities.BeaverageActivity;
import com.example.manjooralam.flashdelivery.activities.BrandedFoodsActivity;
import com.example.manjooralam.flashdelivery.activities.CakesAndFlowersActivity;
import com.example.manjooralam.flashdelivery.activities.DairyAndBreadActivity;
import com.example.manjooralam.flashdelivery.activities.FruitsAndVegitableActivity;
import com.example.manjooralam.flashdelivery.activities.GroceryAndStaplesActivity;
import com.example.manjooralam.flashdelivery.activities.HouseholdActivity;
import com.example.manjooralam.flashdelivery.activities.OffersActivity;
import com.example.manjooralam.flashdelivery.activities.PersonalCareActivity;
import com.example.manjooralam.flashdelivery.models.CategoryModel;

import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolders> {

    private List<CategoryModel> itemList;
    private Context context;

    public MainCategoryAdapter(Context context, List<CategoryModel> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public class MyViewHolders extends RecyclerView.ViewHolder{

        public TextView countryName;
        public ImageView countryPhoto;
        public CardView rootLayout;
        public MyViewHolders(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.country_name);
            countryPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
            rootLayout = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
    @Override
    public MyViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_categories, null);
        MyViewHolders rcv = new MyViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MyViewHolders holder, final int position) {
        holder.countryName.setText(itemList.get(position).getName());
        holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {

                    case 0 :
                        context.startActivity(new Intent(context, OffersActivity.class));
                        break;

                    case 1:
                        context.startActivity(new Intent(context, CakesAndFlowersActivity.class));
                        break;

                    case 2:
                        context.startActivity(new Intent(context, FruitsAndVegitableActivity.class));
                        break;

                    case 3:
                        context.startActivity(new Intent(context, GroceryAndStaplesActivity.class));
                        break;

                    case 4:
                        context.startActivity(new Intent(context, HouseholdActivity.class));

                        break;

                    case 5:
                        context.startActivity(new Intent(context, BeaverageActivity.class));
                        break;

                    case 6:
                        context.startActivity(new Intent(context, PersonalCareActivity.class));
                        break;

                    case 7:
                        context.startActivity(new Intent(context, DairyAndBreadActivity.class));

                        break;

                    case 8:
                        context.startActivity(new Intent(context, BrandedFoodsActivity.class));
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
