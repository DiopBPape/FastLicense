package com.example.fastlicense;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.ArgomentiDTO;
import com.example.fastlicense.model.ArgomentoDettaglioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArgomentoDettaglio extends AppCompatActivity {

    TextView titoloView, testoView;
    ImageView imgDettaglio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argomento_dettaglio);

        titoloView = findViewById(R.id.titoloDettaglio);
        testoView = findViewById(R.id.testoDettaglio);
        imgDettaglio = findViewById(R.id.imgDettaglio);

        int capitoloId = getIntent().getIntExtra("capitoloId", -1);
        int argomentoId = getIntent().getIntExtra("argomentoId", -1);

        if (capitoloId == -1 || argomentoId == -1) {
            Toast.makeText(this, "Errore parametri", Toast.LENGTH_SHORT).show();
            return;
        }

        caricaDettaglio(capitoloId, argomentoId);
    }

    private void caricaDettaglio(int capitoloId, int argomentoId) {
        APIManager.apiService.getArgomentoDettaglio(capitoloId, argomentoId)
                .enqueue(new Callback<ArgomentoDettaglioResponse>() {
                    @Override
                    public void onResponse(Call<ArgomentoDettaglioResponse> call, Response<ArgomentoDettaglioResponse> response) {
                        if (!response.isSuccessful() || response.body() == null) {
                            Toast.makeText(ArgomentoDettaglio.this, "Errore", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ArgomentiDTO arg = response.body().getArgomento();
                        titoloView.setText(arg.getTitolo());
                        testoView.setText(arg.getTesto());

                        // Mostra solo la prima immagine se esiste
                        if (arg.getImmagini() != null && !arg.getImmagini().isEmpty()) {
                            String firstImage = arg.getImmagini().get(0);
                            String imageUrl = "http://192.168.1.107:8080/immagini/" + firstImage;
                            Glide.with(ArgomentoDettaglio.this)
                                    .load(imageUrl)
                                    .into(imgDettaglio);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArgomentoDettaglioResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

}

