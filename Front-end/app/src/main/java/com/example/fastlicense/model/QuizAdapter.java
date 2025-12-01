package com.example.fastlicense.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastlicense.R;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private List<DomandeDTO> questions;

    public QuizAdapter(List<DomandeDTO> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.domanda_item, parent, false);  // ⬅️ layout corretto
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int position) {
        DomandeDTO q = questions.get(position);

        holder.txtDomanda.setText(q.getTesto());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDomanda;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDomanda = itemView.findViewById(R.id.txtDomanda);
        }
    }
}
