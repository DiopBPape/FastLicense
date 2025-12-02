package com.example.fastlicense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.LoginResponse;
import com.example.fastlicense.model.UserDTO;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.example.fastlicense.api.APIManager;
import com.example.fastlicense.model.LoginResponse;
import com.example.fastlicense.model.UserDTO;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        EditText usernameTxt = findViewById(R.id.editEmail);
        EditText passwordTxt = findViewById(R.id.editPassword);
        Button loginBtn = findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameIn = usernameTxt.getText().toString();
                String passwordIn = passwordTxt.getText().toString();
                UserDTO user = new UserDTO(usernameIn, passwordIn);
                APIManager.apiService.login(user).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            finish();
                            //String body = response.body();

                        } else {
                            Toast.makeText(MainActivity.this, "Login fallito", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                        Toast.makeText(MainActivity.this, "colpa di gabri", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}



