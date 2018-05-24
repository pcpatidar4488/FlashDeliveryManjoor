package com.example.manjooralam.flashdelivery.network;

/**
 * Created by manjooralam on 9/29/2017.
 */

import retrofit2.Retrofit;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "https://maps.googleapis.com/maps/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}