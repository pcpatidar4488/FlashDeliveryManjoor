package com.example.manjooralam.flashdelivery.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.activities.BeaverageActivity;
import com.example.manjooralam.flashdelivery.activities.BrandedFoodsActivity;
import com.example.manjooralam.flashdelivery.activities.CakesAndFlowersActivity;
import com.example.manjooralam.flashdelivery.activities.DairyAndBreadActivity;
import com.example.manjooralam.flashdelivery.activities.FruitsAndVegitableActivity;
import com.example.manjooralam.flashdelivery.activities.GroceryAndStaplesActivity;
import com.example.manjooralam.flashdelivery.activities.HomeActivity;
import com.example.manjooralam.flashdelivery.activities.HouseholdActivity;
import com.example.manjooralam.flashdelivery.activities.OffersActivity;
import com.example.manjooralam.flashdelivery.activities.PersonalCareActivity;
import com.example.manjooralam.flashdelivery.models.NewArrivalsModel;

import java.util.ArrayList;
import java.util.List;

public class SubCatergoryAdapter extends RecyclerView.Adapter<SubCatergoryAdapter.MyViewHolder> {

    private Context mActivity;
    private String[] subCategoryArray;

    public void addList(String[] all) {
        subCategoryArray = all;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subCategory, year, genre;
        public RelativeLayout rvRootSubCategory;

        public MyViewHolder(View view) {
            super(view);

            subCategory = view.findViewById(R.id.tv_sub_category);
            rvRootSubCategory = view.findViewById(R.id.rl_root_sub_category);
        }
    }


    public SubCatergoryAdapter(Context context, String[] subCategoryArray) {
        this.mActivity = context;
        this.subCategoryArray = subCategoryArray;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sub_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.subCategory.setText(subCategoryArray[position]);
        holder.rvRootSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((CakesAndFlowersActivity) mActivity).handleSubCategory(position);

            }
        });
        holder.rvRootSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mActivity instanceof OffersActivity)
                    ((OffersActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof FruitsAndVegitableActivity)
                    ((FruitsAndVegitableActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof CakesAndFlowersActivity)
                    ((CakesAndFlowersActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof GroceryAndStaplesActivity)
                    ((GroceryAndStaplesActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof HouseholdActivity)
                    ((HouseholdActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof BeaverageActivity)
                    ((BeaverageActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof PersonalCareActivity)
                    ((PersonalCareActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof DairyAndBreadActivity)
                    ((DairyAndBreadActivity) mActivity).handleSubCategory(position);
                else if (mActivity instanceof BrandedFoodsActivity)
                    ((BrandedFoodsActivity) mActivity).handleSubCategory(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        //.  return newArrivalItemList.size();
        return subCategoryArray.length;
    }
}