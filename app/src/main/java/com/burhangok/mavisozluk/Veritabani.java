package com.burhangok.mavisozluk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {

    public static final String VT_ADI="mavi";
    public static final String TABLO_ADI="sozluk";
    public Context mcontext;


    public Veritabani(Context context) {
        super(context, VT_ADI, null, 1);
        this.mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


            String sqlCumlesi = "CREATE TABLE IF NOT EXISTS "+TABLO_ADI+" (ID INTEGER PRIMARY KEY , English TEXT, Turkish TEXT" + ")";

            db.execSQL(sqlCumlesi);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public List<Sozluk> kelimeleriGetir (String aranacak) {
        List<Sozluk> kelimeler = new ArrayList<Sozluk>();

        SQLiteDatabase vt =this.getWritableDatabase();

        String sqlCumlesi="SELECT * FROM sozluk WHERE English LIKE '"+aranacak+"%' OR Turkish LIKE '"+aranacak+"%' ORDER BY English ";
        Cursor cursor = vt.rawQuery(sqlCumlesi,null);

        while (cursor.moveToNext()) {

            Sozluk kelime = new Sozluk();
            kelime.setEnglish(cursor.getString(1));
            kelime.setTurkish(cursor.getString(2));
            kelimeler.add(kelime);
        }

        return kelimeler;

    }

}
