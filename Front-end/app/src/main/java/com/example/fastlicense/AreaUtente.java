package com.example.fastlicense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fastlicense.model.LoginResponse;
import com.example.fastlicense.model.UserDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AreaUtente extends AppCompatActivity {
    LoginResponse user = new LoginResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.utente);

        ImageView btnLogout = findViewById(R.id.btnLogout);

        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigation);
        bottomMenu.setSelectedItemId(R.id.areaUtente);
        bottomMenu.setOnItemSelectedListener(item -> BottonMenu.switchPage(this, item));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setRisultato(null);
                Intent intent = new Intent(AreaUtente.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
