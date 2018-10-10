package com.example.shadow47.movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface weatherApi {

    String Base_Url="https://api.darksky.net/forecast/";
    @GET("34761c9544db076cc9a37f365d1c1da8/{id},{id1}")
    Call<weatherPojo> getWeather(@Path("id") double lat,@Path("id1") double log);
}
