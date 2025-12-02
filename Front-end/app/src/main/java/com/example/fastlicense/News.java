package com.example.fastlicense;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.CapitoliAdapter;
import com.example.fastlicense.model.NewsDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);

        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigation);
        bottomMenu.setOnItemSelectedListener(item -> BottonMenu.switchPage(this, item));

        ListView listNews = findViewById(R.id.listNews);
        APIManager.apiService.getAllNews().enqueue(new Callback<List<NewsDTO>>() {
            @Override
            public void onResponse(Call<List<NewsDTO>> call, Response<List<NewsDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<NewsDTO> newsList = response.body();
                    CapitoliAdapter adapter = new CapitoliAdapter(News.this, newsList);
                    listNews.setAdapter(adapter);

                } else {
                    System.out.println("Errore nella risposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<NewsDTO>> call, Throwable t) {
                    t.printStackTrace();
            }
        });
    }
}