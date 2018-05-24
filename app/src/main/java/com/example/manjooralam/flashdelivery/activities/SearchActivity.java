package com.example.manjooralam.flashdelivery.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.SearchAdapter;
import com.example.manjooralam.flashdelivery.adapters.SubCatergoryAdapter;
import com.example.manjooralam.flashdelivery.models.NewArrivalsModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private List<NewArrivalsModel> newArrivalItemList = new ArrayList<>();
    private ImageView ivBack;
    private TextView tvTitle;
    private RecyclerView rvSearch;
    private SearchAdapter searchAdapter;
    private String all[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        initialPageSetup();
        recyclerViewSetup();
    }

    private void recyclerViewSetup() {

        searchAdapter = new SearchAdapter(this, newArrivalItemList);
        rvSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSearch.setAdapter(searchAdapter);
    }

    private void initialPageSetup() {
        tvTitle.setText(getResources().getString(R.string.s_search));
    }

    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rvSearch= (RecyclerView) findViewById(R.id.search_recycle);
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
