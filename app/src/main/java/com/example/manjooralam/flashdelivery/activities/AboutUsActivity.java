package com.example.manjooralam.flashdelivery.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {
    private WebView webContent;
    private TextView tvToolbarTitle;
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initView();
        initialSetUp();
    }

    private void startWebView(String url) {

        webContent.setWebViewClient(new WebViewClient() {

            ProgressDialog pro;


            public boolean shouldOverrideUrlLoading(String url, WebView view) {
                view.loadUrl(url);
                return true;
            }
        });

        webContent.getSettings().setJavaScriptEnabled(true);
        webContent.getSettings().setLoadsImagesAutomatically(true);
        webContent.getSettings().setJavaScriptEnabled(true);
        webContent.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webContent.loadUrl(url);
    }

    private void initialSetUp() {
        if(getIntent().getStringExtra("EXTRA_FROM").equals("AboutUs")){
            tvToolbarTitle.setText(getResources().getString(R.string.s_about_us).toUpperCase());
            startWebView("https://goo.gl/zT4wq9");
        }else if(getIntent().getStringExtra("EXTRA_FROM").equals("PrivacyPolicy")){
            tvToolbarTitle.setText(getResources().getString(R.string.s_privacy_policy).toUpperCase());
            startWebView("https://goo.gl/nEV3ao");

        }else if(getIntent().getStringExtra("EXTRA_FROM").equals("TermsAndConditions")){
            tvToolbarTitle.setText(getResources().getString(R.string.s_terms_and_conditions).toUpperCase());
            startWebView("https://goo.gl/GNwZyy");

        }
    }

    private void initView() {
        tvToolbarTitle = (TextView) findViewById(R.id.tv_title);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        webContent= (WebView) findViewById(R.id.web_about_us);

        //----- REGISTERING LISTENERS
        ivBack.setOnClickListener(this);
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
