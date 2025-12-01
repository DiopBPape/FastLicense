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
import com.example.fastlicense.model.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test extends AppCompatActivity {

    private ViewPager2 viewPager;
    private QuizAdapter adapter;
    private Button btnNext, btnPrev;
    private List<DomandeDTO> questionList = new ArrayList<>();
    private Button vero, falso;
    private TextView nQuiz;

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 20 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);

        // 🔹 Riferimenti alle view
        viewPager = findViewById(R.id.viewPagerQuiz);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        vero = findViewById(R.id.btnVero);
        falso= findViewById(R.id.btnFalso);
        timerTextView = findViewById(R.id.timerTextView);



        // Adapter per ViewPager2
        adapter = new QuizAdapter(questionList);
        viewPager.setAdapter(adapter);

         nQuiz = findViewById(R.id.nQuiz);




        loadQuizFromServer();
        startTimer();

        // 🔹 Freccia avanti
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() < questionList.size() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
                }
            }
        });

        // 🔹 Freccia indietro
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewPager.getCurrentItem() > 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //nQuiz.setText((position + 1) + "/" + questionList.size());
                nQuiz.setText(viewPager.getCurrentItem()+1 + "/30");
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

                // formattazione "MM:SS"
                String timeFormatted = String.format("%02d:%02d", minutes, seconds);
                timerTextView.setText(timeFormatted);
                timerTextView.setText(timerTextView.getText());
            }

            @Override
            public void onFinish() {
                // azione quando finisce il tempo, es. bloccare quiz
                Intent intent = new Intent(Test.this, Quiz.class);
                startActivity(intent);
                Toast.makeText(Test.this, "Tempo scaduto!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }


}


