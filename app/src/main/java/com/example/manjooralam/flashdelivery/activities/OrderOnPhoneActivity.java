package com.example.manjooralam.flashdelivery.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjooralam.flashdelivery.R;

/**
 * Created by manjooralam on 9/16/2017.
 */

public class OrderOnPhoneActivity extends BaseActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1 ;
    private ImageView ivBack;
    private TextView tvPhoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_on_phone);
        initViews();
    }

    /**
     * method for initializing views
     */
    private void initViews() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvPhoneNumber = (TextView) findViewById(R.id.tv_phone);

        //------registering lsteners
        ivBack.setOnClickListener(this);
        tvPhoneNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_phone:

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    requestPhoneCallPermission();
                }else {
                    makeCall();
                }

                break;
        }
    }

    private void requestPhoneCallPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        makeCall();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    requestPhoneCallPermission();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * method for making phone call
     */
    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tvPhoneNumber.getText().toString().trim()));
        startActivity(intent);
    }
}
