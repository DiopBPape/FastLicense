package com.example.fastlicense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        View cerchio = findViewById(R.id.cerchio);
        View triangolo = findViewById(R.id.triangolo);
        View quadrato = findViewById(R.id.quadrato);
        View rombo = findViewById(R.id.rombo);


        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigation);
        bottomMenu.setSelectedItemId(R.id.home);
        bottomMenu.setOnItemSelectedListener(item -> BottonMenu.switchPage(this, item));

        cerchio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Quiz.class);
                startActivity(intent);
            }
        });


        quadrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Assistenza.class);
                startActivity(intent);
            }
        });

        triangolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, News.class);
                startActivity(intent);
            }
        });

    }
}