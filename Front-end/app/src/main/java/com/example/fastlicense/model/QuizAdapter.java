package com.example.fastlicense.model;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastlicense.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    public interface OnAnswerSelectedListener {
        void onAnswerSelected(DomandeDTO domanda, Boolean risposta);
    }

    private List<DomandeDTO> questions;
    private OnAnswerSelectedListener listener;
    private Map<Integer, Boolean> selectedAnswers = new HashMap<>();

    public QuizAdapter(List<DomandeDTO> questions, OnAnswerSelectedListener listener) {
        this.questions = questions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.domanda_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.ViewHolder holder, int position) {
        DomandeDTO q = questions.get(position);

        holder.txtDomanda.setText(q.getTesto());

        if (q.getImmagine() != null && !q.getImmagine().isEmpty()) {
            String imageUrl = "http://192.168.1.107:8080/immagini/" + q.getImmagine();
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(holder.image);
        } else {
            holder.image.setImageDrawable(null);
        }

        // Aggiorna colore bottoni in base alla risposta selezionata
        Boolean rispostaSelezionata = selectedAnswers.get(q.getId());
        aggiornaStileBottoni(holder, rispostaSelezionata);

        holder.btnVero.setOnClickListener(v -> {
            selectedAnswers.put(q.getId(), true);
            aggiornaStileBottoni(holder, true);
            if (listener != null) listener.onAnswerSelected(q, true);
        });

        holder.btnFalso.setOnClickListener(v -> {
            selectedAnswers.put(q.getId(), false);
            aggiornaStileBottoni(holder, false);
            if (listener != null) listener.onAnswerSelected(q, false);
        });
    }

    private void aggiornaStileBottoni(ViewHolder holder, Boolean risposta) {
        holder.btnVero.setBackgroundColor(Color.parseColor("#808080"));
        holder.btnFalso.setBackgroundColor(Color.parseColor("#808080"));

        if (Boolean.TRUE.equals(risposta)) {
            holder.btnVero.setBackgroundColor(Color.parseColor("#006400"));
        } else if (Boolean.FALSE.equals(risposta)) {
            holder.btnFalso.setBackgroundColor(Color.parseColor("#ff0000"));
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDomanda;
        ImageView image;
        Button btnVero, btnFalso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDomanda = itemView.findViewById(R.id.txtDomanda);
            image = itemView.findViewById(R.id.image);
            btnVero = itemView.findViewById(R.id.btnVero);
            btnFalso = itemView.findViewById(R.id.btnFalso);
        }
    }
}
