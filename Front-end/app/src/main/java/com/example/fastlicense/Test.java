package com.example.fastlicense;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.DomandeDTO;
import com.example.fastlicense.model.ListaDomande;
import com.example.fastlicense.model.PunteggioQuizDto;
import com.example.fastlicense.model.QuizAdapter;
import com.example.fastlicense.model.QuizEseguitoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {

    private ViewPager2 viewPager;
    private QuizAdapter adapter;
    private List<DomandeDTO> questionList = new ArrayList<>();
    private List<PunteggioQuizDto> risposte = new ArrayList<>();
    private TextView nQuiz;
    private Button btnPrev, btnNext, btnInvia, btnTeoria;

    private ImageView book;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 20 * 60 * 1000;
    private int totN = 30;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);



        viewPager = findViewById(R.id.viewPagerQuiz);
        nQuiz = findViewById(R.id.nQuiz);
        timerTextView = findViewById(R.id.timerTextView);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnInvia = findViewById(R.id.btnInvia);
        btnTeoria = findViewById(R.id.btnTeoria);
        book = findViewById(R.id.book);
        boolean showButton = getIntent().getBooleanExtra("showButton", true);

        if(showButton){
            btnTeoria.setVisibility(View.VISIBLE);
            book.setVisibility(View.VISIBLE);
        } else {
            btnTeoria.setVisibility(View.GONE);
            book.setVisibility(View.GONE);
        }

        // Adapter con callback
        adapter = new QuizAdapter(questionList, (domanda, risposta) -> {
            registraRispostaCorrente(domanda, risposta);
        });


        viewPager.setAdapter(adapter);
        nQuiz.setText(viewPager.getCurrentItem()+1 +"/" + totN);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                nQuiz.setText((position + 1) + "/" + totN);
            }
        });

        loadQuizFromServer();
        startTimer();



        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
            }
        });

        btnInvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inviaQuiz();
            }
        });

        btnTeoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test.this, Capitoli.class);
                startActivity(intent);
            }
        });
    }

    private void loadQuizFromServer() {
        APIManager.apiService.getThirtyRandomQuestions().enqueue(new Callback<ListaDomande>() {
            @Override
            public void onResponse(Call<ListaDomande> call, Response<ListaDomande> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Toast.makeText(Test.this, "Errore nel caricamento", Toast.LENGTH_SHORT).show();
                    return;
                }
                questionList.clear();
                questionList.addAll(response.body().getDomande());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListaDomande> call, Throwable t) {
                Toast.makeText(Test.this, "Errore di comunicazione", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                Toast.makeText(Test.this, "Tempo scaduto!", Toast.LENGTH_SHORT).show();
                inviaQuiz();
                finish();
            }
        }.start();
    }

    private void registraRispostaCorrente(DomandeDTO domanda, Boolean risposta) {
        PunteggioQuizDto r = new PunteggioQuizDto();
        r.setDomandaId(domanda.getId());
        r.setRisposta(risposta != null ? risposta.toString() : null); // "true" / "false"

        risposte.removeIf(p -> p.getDomandaId() == domanda.getId());
        risposte.add(r);
    }


    private void inviaQuiz() {
        QuizEseguitoDto quizDto = new QuizEseguitoDto();
        quizDto.setRisposte(risposte);

        List<QuizEseguitoDto> listaQuiz = new ArrayList<>();
        listaQuiz.add(quizDto);

        APIManager.apiService.salvaQuiz(listaQuiz).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Map<String, Object>> quizSalvati = (List<Map<String, Object>>) response.body().get("quizSalvati");
                    if (quizSalvati != null && !quizSalvati.isEmpty()) {
                        if (countDownTimer != null) {
                            countDownTimer.cancel();
                        }

                        int quizId = (int) ((Double) quizSalvati.get(0).get("quizId")).doubleValue();

                        Intent intent = new Intent(Test.this, Risposte.class);
                        intent.putExtra("quizIdAtt", quizId);
                        startActivity(intent);
                        finish();



                    }


                } else {
                    Toast.makeText(Test.this, "Errore salvataggio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(Test.this, "Errore di rete", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

}
