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
import com.example.fastlicense.api.APIManager;

import java.util.List;

public class CapitoliAdapter extends ArrayAdapter<CapitoliDTO> {

   public CapitoliAdapter(@NonNull Context context, @NonNull List<CapitoliDTO> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.capitoli_item, parent, false);
        }

        CapitoliDTO capitoli= getItem(position);

        TextView capitoloTitolo = convertView.findViewById(R.id.capitoloTitolo);
        ImageView img = convertView.findViewById(R.id.capitoloImg);

        String  imageUrl = APIManager.getBaseUrl() + "immagini/" + capitoli.getImmagine();

        capitoloTitolo.setText(capitoli.getTitolo());
        Glide.with(img.getContext())
                .load(imageUrl)
                .into(img);

        return convertView;
    }
}

