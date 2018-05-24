package com.example.manjooralam.flashdelivery.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.FeaturedProductAdapter;
import com.example.manjooralam.flashdelivery.models.FeaturedProductModel;
import com.example.manjooralam.flashdelivery.utilities.AppConstants;
import com.example.manjooralam.flashdelivery.utilities.AppUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeaturedProductActivity extends BaseActivity implements View.OnClickListener {
    List<FeaturedProductModel> featuredProductList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FeaturedProductAdapter mAdapter;
    private ImageView iv_back;
    private TextView tvTitle;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private LinearLayout llRootlayout;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_and_vegitables);
        initViews();
        initialPageSetUp();
    }

    private void initialPageSetUp() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        if(getIntent().getStringExtra("EXTRA_FROM").equals("featuredProduct")){
            tvTitle.setText(getResources().getString(R.string.s_featured_product));
            mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.FEATURED_PRODUCT);
        }else if(getIntent().getStringExtra("EXTRA_FROM").equals("heavyDiscounts")){
            tvTitle.setText(getResources().getString(R.string.s_heavy_discounts));
            mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.HEAVY_DISCOUNT);
        }else if(getIntent().getStringExtra("EXTRA_FROM").equals("newArrivals")){
            tvTitle.setText(getResources().getString(R.string.s_new_arrivals));
            mFirebaseDatabase = mFirebaseInstance.getReference(AppConstants.NEW_ARRIVALS);
        }

        listSetUp();
        if(AppUtils.getInstance().isNetworkAvailable(this)) {
            fetchDataFeaturedProduct();
        }else {
            AppUtils.getInstance().showSnackBar(getResources().getString(R.string.s_no_internet), llRootlayout);
        }
    }

    private void fetchDataFeaturedProduct() {
        progressbar.setVisibility(View.VISIBLE);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressbar.setVisibility(View.GONE);
                GenericTypeIndicator<List<FeaturedProductModel>> t = new GenericTypeIndicator<List<FeaturedProductModel>>() {};
                featuredProductList.addAll(dataSnapshot.getValue(t));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }


    private void listSetUp() {
        mAdapter = new FeaturedProductAdapter(this, featuredProductList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void initViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_item);
        llRootlayout = (LinearLayout) findViewById(R.id.ll_rootlayout);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
