package com.pkfest.pemetaan.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rifky on 04/06/17.
 */

public class ServiceGenerator {
    public static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builderOkhttp = new OkHttpClient.Builder();
        builderOkhttp.connectTimeout(20, TimeUnit.SECONDS);
        builderOkhttp.readTimeout(15, TimeUnit.SECONDS);
        builderOkhttp.writeTimeout(15, TimeUnit.SECONDS);
        builderOkhttp.addInterceptor(httpLoggingInterceptor);

        OkHttpClient mOkHttpClient = builderOkhttp.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mOkHttpClient).build();

        return retrofit.create(serviceClass);

    }
}
