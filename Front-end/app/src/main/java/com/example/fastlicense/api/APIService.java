package com.example.fastlicense.api;

import android.view.PixelCopy;

import com.example.fastlicense.model.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    @FormUrlEncoded
    @POST("login")
    public Call<LoginResponse> login(@Field("username") String username,
                                     @Field("password") String password);
}
