package com.example.manjooralam.flashdelivery.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.adapters.AddressAdapter;
import com.example.manjooralam.flashdelivery.adapters.SearchAdapter;
import com.example.manjooralam.flashdelivery.models.NewArrivalsModel;

import java.util.ArrayList;
import java.util.List;

public class AdressBookActivity extends AppCompatActivity  implements View.OnClickListener{
    private ImageView ivBack,ivAddAddress;
    private TextView tvToolbar;
    private List<NewArrivalsModel> newArrivalItemList = new ArrayList<>();

    private RecyclerView rvSearch;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        init();
        pageSetUp();
        recyclerViewSetup();
        ivAddAddress.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    private void recyclerViewSetup() {
        addressAdapter = new AddressAdapter(this, newArrivalItemList);
        rvSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSearch.setAdapter(addressAdapter);
    }

    private void pageSetUp() {
        tvToolbar.setText(getResources().getString(R.string.s_address_book));
        ivAddAddress.setVisibility(View.VISIBLE);
    }

    private void init() {
        ivBack= (ImageView) findViewById(R.id.iv_back);
        ivAddAddress= (ImageView) findViewById(R.id.iv_next);
        tvToolbar= (TextView) findViewById(R.id.tv_title);
        rvSearch= (RecyclerView) findViewById(R.id.rv_address);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_next:
                Intent intent=new Intent(this,AddAdressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
