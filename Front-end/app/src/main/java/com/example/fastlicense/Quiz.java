package com.example.fastlicense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Quiz extends AppCompatActivity {


    Button btnRandomQuiz, btnMinisteriale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigation);
        bottomMenu.setOnItemSelectedListener(item -> BottonMenu.switchPage(this, item));

        btnRandomQuiz = findViewById(R.id.btnRandom);
        btnMinisteriale = findViewById(R.id.btnMinisteriale);

        btnRandomQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quiz.this, Test.class);
                intent.putExtra("showButton", true);
                startActivity(intent);
                finish();
            }
        });

        btnMinisteriale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Quiz.this, Test.class);
                intent.putExtra("showButton", false); // o false
                startActivity(intent);
                finish();

            }
        });
    }
}
