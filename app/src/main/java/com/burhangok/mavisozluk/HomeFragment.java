package com.burhangok.mavisozluk;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    View fragmentView;

    public EditText keywordET;

    public Button searchBTN;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       fragmentView = inflater.inflate(R.layout.fragment_home,null);

        init();
        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchedKeyword = keywordET.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("keyword",searchedKeyword);

                KeywordsFragment keywordsFragment = new KeywordsFragment();
                keywordsFragment.setArguments(bundle);

                fragmentManager=getFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);

                fragmentTransaction.replace(R.id.fragment_area,keywordsFragment);
                fragmentTransaction.commit();


            }
        });

       return fragmentView;
    }

    void init () {

        keywordET= fragmentView.findViewById(R.id.searchKeyword);

        searchBTN = fragmentView.findViewById(R.id.searchBtn);

    }

}
