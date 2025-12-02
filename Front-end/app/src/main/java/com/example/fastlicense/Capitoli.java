package com.example.fastlicense;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.CapitoliAdapter;
import com.example.fastlicense.model.CapitoliDTO;
import com.example.fastlicense.model.CapitoliResponse;
import com.example.fastlicense.model.NewsAdapter;
import com.example.fastlicense.model.NewsDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Capitoli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_capitoli);

        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigation);
        bottomMenu.setOnItemSelectedListener(item -> BottonMenu.switchPage(this, item));

        ListView listCapitoli = findViewById(R.id.listCapitoli);

        APIManager.apiService.getAllCapitoli().enqueue(new Callback<CapitoliResponse>() {
            @Override
            public void onResponse(Call<CapitoliResponse> call, Response<CapitoliResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<CapitoliDTO> capitoliList = response.body().getCapitoli();

                    CapitoliAdapter adapter = new CapitoliAdapter(Capitoli.this, capitoliList);
                    listCapitoli.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CapitoliResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}