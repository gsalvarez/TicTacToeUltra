package com.igabeto.tictactoeultra;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Menu extends Fragment {

    public Menu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        TextView btnPlayNormal = (TextView) view.findViewById(R.id.btnPlayNormal);
        TextView btnPlayUltra = (TextView) view.findViewById(R.id.btnPlayUltra);

        btnPlayNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                PlayNormal fragment = new PlayNormal();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        btnPlayUltra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                PlayUltra fragment = new PlayUltra();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        return view;
    }
}