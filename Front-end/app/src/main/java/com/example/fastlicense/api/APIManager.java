package com.example.fastlicense.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    private static final String URL = "http://10.238.206.82:8080/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final APIService apiService = retrofit.create(APIService.class);

    public static String getBaseUrl(){
        return URL;
    }
}
