package com.burhangok.mavisozluk;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KeywordsAdapter extends RecyclerView.Adapter<KeywordsAdapter.KelimelerHolder> {


    List<Sozluk> kelimelerList;

    LayoutInflater layoutInflater;

    Context context;

    TextToSpeech t1;

    public KeywordsAdapter(Context context, List<Sozluk> kelimelerList) {
        this.kelimelerList = kelimelerList;
        this.context = context;
        this.layoutInflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    };

    @NonNull
    @Override
    public KelimelerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View rowLayout = layoutInflater.inflate(R.layout.item_layout,null);

        KelimelerHolder kelimelerHolder = new KelimelerHolder(rowLayout);


        return kelimelerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final KelimelerHolder kelimelerHolder, int i) {


        final Sozluk kelime = kelimelerList.get(i);

        kelimelerHolder.ustTaraf.setText(kelime.getEnglish());
        kelimelerHolder.altTaraf.setText(kelime.getTurkish());


        kelimelerHolder.shareI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mesaj = "İngilizce: " +kelime.getEnglish()+ "\n\nTürkçe: "+kelime.getTurkish()+ "\n\n\n Bu Mesaj Mavi Sözlük' ten Gönderildi.";


                Intent paylasI = new Intent(Intent.ACTION_SEND);
                paylasI.setType("text/plain");
                paylasI.putExtra(Intent.EXTRA_TEXT, mesaj);
                kelimelerHolder.itemView.getContext().startActivity(paylasI);
            }
        });

   t1 = new TextToSpeech(kelimelerHolder.itemView.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });

        kelimelerHolder.listenI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.speak(kelime.getEnglish(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kelimelerList.size();
    }

    class KelimelerHolder extends RecyclerView.ViewHolder {

        TextView ustTaraf,altTaraf;
        ImageView shareI,listenI;
        public KelimelerHolder(@NonNull View itemView) {
            super(itemView);


             ustTaraf = itemView.findViewById(R.id.ingilizce);

             altTaraf = itemView.findViewById(R.id.turkce);
             shareI = itemView.findViewById(R.id.share);
             listenI = itemView.findViewById(R.id.listen);
        }
    }
}
