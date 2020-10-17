package com.aanchal.godric.newshub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("everything")
    Call<NewsList> getData(@Query("sources") String sources, @Query("apiKey") String apiKey);
}
