package com.example.fastlicense.api;

import com.example.fastlicense.model.ArgomentiResponse;
import com.example.fastlicense.model.ArgomentoDettaglioResponse;
import com.example.fastlicense.model.CapitoliResponse;
import com.example.fastlicense.model.ListaDomande;
import com.example.fastlicense.model.LoginResponse;
import com.example.fastlicense.model.NewsDTO;
import com.example.fastlicense.model.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @POST("login")
    public Call<LoginResponse> login(@Body UserDTO userDTO);
    @GET("news")
    public Call<List<NewsDTO>> getAllNews();

    @GET("getDomande")
    Call<ListaDomande> getThirtyRandomQuestions();

    @GET("getCapitoli")
    Call<CapitoliResponse> getAllCapitoli();

    @GET("getArgomento/{capitoloId}")
    Call<ArgomentiResponse>getArgomentiByCapitolo (@Path("capitoloId") Integer capitoloId);

    @GET("getArgomentoDettaglio/{capitoloId}/{argomentoId}")
    Call<ArgomentoDettaglioResponse> getArgomentoDettaglio(
            @Path("capitoloId") int capitoloId,
            @Path("argomentoId") int argomentoId
    );




}
