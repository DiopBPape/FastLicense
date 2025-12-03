package com.example.fastlicense;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.QuizDettaglioResponse;
import com.example.fastlicense.model.DomandaDettaglio;
import com.example.fastlicense.model.RisposteAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Risposte extends AppCompatActivity {

    private TextView tvCorrette, tvErrate; // Rinominato per chiarezza con il layout
    private ListView listViewDomande;
    private RisposteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esito); // Layout principale

        tvCorrette = findViewById(R.id.tvCorrette);
        tvErrate= findViewById(R.id.tvErrate);
        listViewDomande = findViewById(R.id.listViewDomande);

        int quizId = getIntent().getIntExtra("quizIdAtt", -1);

        if (quizId != -1) {
            recuperaDettagliQuiz(quizId);
        } else {
            Toast.makeText(this, "ID Quiz non trovato.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void recuperaDettagliQuiz(int quizId) {
        APIManager.apiService.getQuizEseguitoDettaglio(quizId).enqueue(new Callback<QuizDettaglioResponse>() {
            @Override
            public void onResponse(Call<QuizDettaglioResponse> call, Response<QuizDettaglioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    QuizDettaglioResponse dettagli = response.body();

                    int punteggio = dettagli.getPunteggioCalcolato();
                    List<DomandaDettaglio> risposte = dettagli.getRisposte();


                    String corretteText = "Corrette: " + punteggio + " / 30" ;
                    String errateText = "Errate: " + (30 - punteggio ) ;
                    tvCorrette.setText(corretteText);
                    tvErrate.setText(errateText);



                    adapter = new RisposteAdapter(Risposte.this, risposte);
                    listViewDomande.setAdapter(adapter);

                } else if (response.code() == 404) {
                    Toast.makeText(Risposte.this, "Quiz non trovato o accesso negato (404).", Toast.LENGTH_LONG).show();
                    finish();
                }

                else {
                    Toast.makeText(Risposte.this, "Errore nel recupero dei dettagli: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<QuizDettaglioResponse> call, Throwable t) {
                Toast.makeText(Risposte.this, "Errore di rete/comunicazione: " + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}