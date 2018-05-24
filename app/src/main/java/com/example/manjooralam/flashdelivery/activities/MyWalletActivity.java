package com.example.manjooralam.flashdelivery.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;

public class MyWalletActivity extends BaseActivity {

    private LinearLayout llMoneyWallet;
    private TextView tvTitle, tvSubmit, labelWalletText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        initViews();
        initialPageSetup();
    }

    private void initialPageSetup() {
        if(getIntent().getStringExtra("EXTRA_FROM").equals("MyWallet")){
            tvSubmit.setVisibility(View.GONE);
            llMoneyWallet.setVisibility(View.VISIBLE);
        }else {
            tvTitle.setText(getResources().getString(R.string.s_summary));
            tvSubmit.setVisibility(View.VISIBLE);
            llMoneyWallet.setVisibility(View.GONE);
            labelWalletText.setText(getResources().getString(R.string.s_minimum_points_to_redeem));
        }
    }

    private void initViews() {
        llMoneyWallet = (LinearLayout) findViewById(R.id.ll_money_wallet_container);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        labelWalletText = (TextView) findViewById(R.id.lebel_wallet_text);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }
}
