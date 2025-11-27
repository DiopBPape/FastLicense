package com.example.fastlicense;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Assistenza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assistenza);


        FrameLayout mapBox = findViewById(R.id.mapBox);


        mapBox.setOnClickListener(new View.OnClickListener() {
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
    }
}
