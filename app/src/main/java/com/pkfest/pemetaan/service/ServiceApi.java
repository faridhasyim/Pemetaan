package com.pkfest.pemetaan.service;

import com.pkfest.pemetaan.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rifky on 04/06/17.
 */

public interface ServiceApi {
    @GET("json")
    Call<Data> getResult(
            @Query("query") String query,
            @Query("radius") String radius,
            @Query("types") String types,
            @Query("sensor") String sensor,
            @Query("key") String key
    );
}
