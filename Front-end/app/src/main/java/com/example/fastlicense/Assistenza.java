package com.example.fastlicense;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
// Importa i tipi di View che stai usando, ad esempio Button o TextView,
// oltre a ImageView se non stai usando solo ImageView per i nuovi elementi.
import android.widget.ImageView;
import android.widget.Button; // Esempio se usi un Button
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fastlicense.api.APIManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Assistenza extends AppCompatActivity {


    private static final String EMAIL_ADDRESS = "assistenza@fastlicense.com";
    private static final String PHONE_NUMBER = "+3902123456";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assistenza);

        BottomNavigationView bottomMenu = findViewById(R.id.bottomNavigation);
        bottomMenu.setOnItemSelectedListener(item -> BottonMenu.switchPage(this, item));

        ImageView mappa = findViewById(R.id.mappa);

        LinearLayout emailButton = findViewById(R.id.emailBox);
        LinearLayout phoneButton = findViewById(R.id.telBox);


        String immagine = APIManager.getBaseUrl() + "immagini/mappa.png";

        Glide.with(this)
                .load(immagine)
                .into(mappa);

        mappa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = "ITS Angelo Rizzoli, Via Amoretti, Milano";
                Uri uri = Uri.parse("geo:0,0?q=" + address);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            }
        });


        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);


                intent.setType("message/rfc822");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_ADDRESS});


                intent.putExtra(Intent.EXTRA_SUBJECT, "Richiesta Assistenza FastLicense");


                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Crea un chooser per permettere all'utente di scegliere l'app di posta
                    startActivity(Intent.createChooser(intent, "Invia Email con..."));
                }

            }
        });


        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri telUri = Uri.parse("tel:" + PHONE_NUMBER);

                Intent intent = new Intent(Intent.ACTION_DIAL, telUri);


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}