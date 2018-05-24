package com.example.manjooralam.flashdelivery;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class CustomFontApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initalize Calligraphy
        CalligraphyConfig.initDefault(
            new CalligraphyConfig.Builder()
                .setDefaultFontPath("SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}