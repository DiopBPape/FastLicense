package com.example.fastlicense.model; // o il tuo pacchetto model

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fastlicense.R;
import com.example.fastlicense.model.DomandaDettaglio;

import java.util.List;

public class RisposteAdapter extends BaseAdapter {

    private final Context context;
    private final List<DomandaDettaglio> domandeList;

    public RisposteAdapter(Context context, List<DomandaDettaglio> domandeList) {
        this.context = context;
        this.domandeList = domandeList;
    }

    @Override
    public int getCount() {
        return domandeList.size();
    }

    @Override
    public Object getItem(int position) {
        return domandeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Usa il layout item_risposta.xml che hai fornito
            convertView = LayoutInflater.from(context).inflate(R.layout.risultato_item, parent, false);
        }

        DomandaDettaglio domanda = domandeList.get(position);

        TextView tvTestoDomanda = convertView.findViewById(R.id.testoDomanda);
        TextView tvRispostaData = convertView.findViewById(R.id.rispostaData);
        TextView tvRispostaCorretta = convertView.findViewById(R.id.rispostaCorretta);

        // 1. Testo Domanda
        String stato = domanda.getIsGiusta() ? " (Corretta)" : " (Sbagliata)";
        tvTestoDomanda.setText((position + 1) + ". " + domanda.getTestoDomanda() + stato);

        // Colora il testo della domanda in base alla correttezza
        if (domanda.getIsGiusta()) {
            tvTestoDomanda.setTextColor(Color.parseColor("#008000")); // Verde scuro
        } else {
            tvTestoDomanda.setTextColor(Color.parseColor("#FF0000")); // Rosso
        }

        // 2. Risposta Data dall'Utente
        String rispostaUtente = domanda.getRispostaDataUtente() == null ?
                "Risposta data: Non risposto" :
                "La tua risposta: " + (domanda.getRispostaDataUtente() ? "Vero" : "Falso");
        tvRispostaData.setText(rispostaUtente);

        // 3. Risposta Corretta
        String rispostaCorretta = "Risposta esatta: " + (domanda.getRispostaCorretta() ? "Vero" : "Falso");
        tvRispostaCorretta.setText(rispostaCorretta);

        // Puoi aggiungere qui la logica per l'immagine se necessario

        return convertView;
    }
}