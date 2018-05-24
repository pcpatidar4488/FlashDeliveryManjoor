package com.example.manjooralam.flashdelivery.network;

import com.example.manjooralam.flashdelivery.models.Googledistanceapiresponse.Example;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

@GET("api/directions/json?key=AIzaSyC22GfkHu9FdgT9SwdCWMwKX1a4aohGifM")
Call<Example> getDistanceDuration(@Query("units") String units, @Query("origin") String origin,
                                  @Query("destination") String destination);

}