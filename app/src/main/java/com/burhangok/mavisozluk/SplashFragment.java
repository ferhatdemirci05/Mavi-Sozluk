package com.burhangok.mavisozluk;


import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }

    public Veritabani vt;
    View fragmentView;

    public SessionManagement sessionManagement;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_splash,null);

        vt = new Veritabani(this.getContext());
        sessionManagement= new SessionManagement(this.getContext());

        if(sessionManagement.girisKontrol()==true){
           //uygulamada datalar yüklenmis

          new Thread(new Runnable() {
              @Override
              public void run() {

                  try{
                    Thread.sleep(200);

                  }
                  catch (Exception e) {

                  }

                  finally {

                      fragmentManager =getFragmentManager();
                      fragmentTransaction = fragmentManager.beginTransaction();
                      fragmentTransaction.addToBackStack(null);
                      fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);

                      fragmentTransaction.replace(R.id.fragment_area,new HomeFragment());
                      fragmentTransaction.commit();
                  }


              }
          }) .start();

        }

        else {

            //datalari yukle

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        veritabaniniKopyala();

                    } catch (Exception e) {

                    } finally {

                        sessionManagement.oturumBaslat();
                        fragmentManager = getFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

                        fragmentTransaction.replace(R.id.fragment_area, new HomeFragment());
                        fragmentTransaction.commit();
                    }


                }
            }).start();


        }
        return fragmentView;
    }


    public void veritabaniniKopyala() throws IOException {

        SQLiteDatabase vt1 = this.vt.getWritableDatabase();
        InputStream insertsStream = this.getContext().getResources().openRawResource(R.raw.sozluk);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));


        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();

            vt1.execSQL(insertStmt);


        }
        insertReader.close();
        vt1.close();

    }



}
