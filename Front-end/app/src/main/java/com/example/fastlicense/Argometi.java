package com.example.fastlicense;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.ArgomentiAdapter;
import com.example.fastlicense.model.ArgomentiDTO;
import com.example.fastlicense.model.ArgomentiResponse;
import com.example.fastlicense.model.CapitoliDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Argometi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_argometi);

        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigation);
        bottomMenu.setOnItemSelectedListener(item -> BottonMenu.switchPage(this, item));

        ListView argomentiList = findViewById(R.id.listArgometi);
        int capitoloId = getIntent().getIntExtra("capitoloId", 0); // 0 valore di default


        APIManager.apiService.getArgomentiByCapitolo(capitoloId).enqueue(new Callback<ArgomentiResponse>() {
            @Override
            public void onResponse(Call<ArgomentiResponse> call, Response<ArgomentiResponse> response) {

                if(response.isSuccessful() && response.body() != null){
                    List<ArgomentiDTO> listArgomenti = response.body().getArgomenti();
                    ArgomentiAdapter adapter = new ArgomentiAdapter(Argometi.this, listArgomenti);
                    argomentiList.setAdapter(adapter);

                }else {
                    System.out.println("Errore nella risposta: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<ArgomentiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        argomentiList.setOnItemClickListener((parent, view, position, id) -> {
            ArgomentiDTO arg = (ArgomentiDTO) parent.getItemAtPosition(position);

            Intent intent = new Intent(Argometi.this, ArgomentoDettaglio.class);
            intent.putExtra("capitoloId", arg.getCapitoloId());
            intent.putExtra("argomentoId", arg.getId());
            startActivity(intent);
        });




    }
}