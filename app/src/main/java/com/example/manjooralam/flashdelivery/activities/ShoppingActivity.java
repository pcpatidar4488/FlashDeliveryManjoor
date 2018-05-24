package com.example.manjooralam.flashdelivery.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.OrderHistoryAdapter;
import com.example.manjooralam.flashdelivery.adapters.ShoppingHistoryAdapter;
import com.example.manjooralam.flashdelivery.models.FruitsAndVegitableModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private RecyclerView rvOrderHistory;
    private ShoppingHistoryAdapter orderHistoryAdapter;
    private List<FruitsAndVegitableModel> newArrivalItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        initializeViews();
        initialPageSetup();
    }

    private void initialPageSetup() {
        tvTitle.setText(getResources().getString(R.string.s_shopping_list));
        rvOrderHistory.setLayoutManager(new LinearLayoutManager(this));
        orderHistoryAdapter = new ShoppingHistoryAdapter(this, newArrivalItemList);
        rvOrderHistory.setAdapter(orderHistoryAdapter);
    }

    private void initializeViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rvOrderHistory = (RecyclerView) findViewById(R.id.rv_shopping);
        ivBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
