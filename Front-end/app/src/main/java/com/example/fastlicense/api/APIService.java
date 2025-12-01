package com.example.fastlicense.api;

import android.view.PixelCopy;

import com.example.fastlicense.model.DomandeDTO;
import com.example.fastlicense.model.ListaDomande;
import com.example.fastlicense.model.LoginResponse;
import com.example.fastlicense.model.NewsDTO;
import com.example.fastlicense.model.UserDTO;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {

    @POST("login")
    public Call<LoginResponse> login(@Body UserDTO userDTO);
    @GET("news")
    public Call<List<NewsDTO>> getAllNews();

    @GET("getDomande")
    Call<ListaDomande> getThirtyRandomQuestions();


}
