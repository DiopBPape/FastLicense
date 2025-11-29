package com.example.fastlicense.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fastlicense.R;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsDTO> {

    public NewsAdapter(@NonNull Context context, @NonNull List<NewsDTO> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }

        NewsDTO news = getItem(position);

        TextView tvTitolo = convertView.findViewById(R.id.newsTitolo);
        TextView tvTesto = convertView.findViewById(R.id.newsTesto);

        tvTitolo.setText(news.getTitolo());
        tvTesto.setText(news.getTesto());

        return convertView;
    }
}

