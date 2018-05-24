package com.example.manjooralam.flashdelivery.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.example.manjooralam.flashdelivery.R;
import com.example.manjooralam.flashdelivery.utilities.AppSharedPreferences;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import io.fabric.sdk.android.Fabric;
@ReportsCrashes(mailTo = "manjooralam28@gmail.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text)
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Fabric.with(this, new Crashlytics());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(AppSharedPreferences.getBoolean(SplashActivity.this, AppSharedPreferences.PREF_KEY.ISLOGIN)){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
            }
        },
                3000);
        logUser();
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("manjooralam28@gmail.com");
        Crashlytics.setUserName("Test User");
    }


}
