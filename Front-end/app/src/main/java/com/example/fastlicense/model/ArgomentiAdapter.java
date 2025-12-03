package com.example.fastlicense.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.fastlicense.R;

import java.util.List;

public class ArgomentiAdapter extends ArrayAdapter<ArgomentiDTO> {

    public ArgomentiAdapter(@NonNull Context context, @NonNull List<ArgomentiDTO> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.argomenti_item, parent, false);
        }

        ArgomentiDTO argomenti= getItem(position);

        TextView argomentiTitolo = convertView.findViewById(R.id.titoloArgomenti);
        ImageView img = convertView.findViewById(R.id.argomentoImg);

        String  imageUrl = "http://192.168.1.107:8080/immagini/" + argomenti.getImmagini();

        argomentiTitolo.setText(argomenti.getTitolo());
        Glide.with(img.getContext())
                .load(imageUrl)
                .into(img);

        return convertView;
    }
}
