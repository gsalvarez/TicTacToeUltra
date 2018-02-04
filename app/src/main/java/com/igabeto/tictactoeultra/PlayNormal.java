package com.igabeto.tictactoeultra;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;


public class PlayNormal extends Fragment {

    private TextView[] cells = new TextView[9];

    private TextView txtTurn;
    private TextView winO;
    private TextView winX;

    private int tieCounter;
    private int oWins;
    private int xWins;
    private String actualTurn;

    private Animation anim;

    public PlayNormal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_normal, container, false);

        this.tieCounter = 0;
        this.oWins = 0;
        this.xWins = 0;
        this.actualTurn = "O";

        cells[0] = (TextView) view.findViewById(R.id.cell0);
        cells[1] = (TextView) view.findViewById(R.id.cell1);
        cells[2] = (TextView) view.findViewById(R.id.cell2);
        cells[3] = (TextView) view.findViewById(R.id.cell3);
        cells[4] = (TextView) view.findViewById(R.id.cell4);
        cells[5] = (TextView) view.findViewById(R.id.cell5);
        cells[6] = (TextView) view.findViewById(R.id.cell6);
        cells[7] = (TextView) view.findViewById(R.id.cell7);
        cells[8] = (TextView) view.findViewById(R.id.cell8);

        txtTurn = (TextView) view.findViewById(R.id.txtTurn);
        winO = (TextView) view.findViewById(R.id.winO);
        winX = (TextView) view.findViewById(R.id.winX);

        TextView playAgain = (TextView) view.findViewById(R.id.btnPlayAgain);
        TextView mainMenu = (TextView) view.findViewById(R.id.btnMainMenu);

        for (final TextView tv: cells) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMark(tv);
                }
            });
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reboot();
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStackImmediate();
            }
        });

        anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(20);

        return view;
    }

    public void setMark(TextView tv){
        tieCounter++;
        tv.setText(actualTurn);
        if(tieCounter > 4){
            checkWinner();
        }
        tv.setTextColor(getResources().getColor(changeTurn()));
        tv.setClickable(false);
    }

    public void checkWinner(){
        if(cells[0].getText().toString().equals(actualTurn) && cells[1].getText().toString().equals(actualTurn) && cells[2].getText().toString().equals(actualTurn) ||
           cells[3].getText().toString().equals(actualTurn) && cells[4].getText().toString().equals(actualTurn) && cells[5].getText().toString().equals(actualTurn) ||
           cells[6].getText().toString().equals(actualTurn) && cells[7].getText().toString().equals(actualTurn) && cells[8].getText().toString().equals(actualTurn) ||
           cells[0].getText().toString().equals(actualTurn) && cells[3].getText().toString().equals(actualTurn) && cells[6].getText().toString().equals(actualTurn) ||
           cells[1].getText().toString().equals(actualTurn) && cells[4].getText().toString().equals(actualTurn) && cells[7].getText().toString().equals(actualTurn) ||
           cells[2].getText().toString().equals(actualTurn) && cells[5].getText().toString().equals(actualTurn) && cells[8].getText().toString().equals(actualTurn) ||
           cells[0].getText().toString().equals(actualTurn) && cells[4].getText().toString().equals(actualTurn) && cells[8].getText().toString().equals(actualTurn) ||
           cells[2].getText().toString().equals(actualTurn) && cells[4].getText().toString().equals(actualTurn) && cells[6].getText().toString().equals(actualTurn)){
                switch(actualTurn){
                    case "O":
                        oWins++;
                        String txtO = "O: " + oWins;
                        winO.setText(txtO);
                        winO.startAnimation(anim);
                        break;
                    case "X":
                        xWins++;
                        String txtX = "X: " + xWins;
                        winX.setText(txtX);
                        winX.startAnimation(anim);
                        break;
                }
                for (TextView tv: cells) {
                    tv.setClickable(false);
                }
        }
        else if(tieCounter == 9){
            winO.startAnimation(anim);
            winX.startAnimation(anim);
        }
    }

    public int changeTurn(){
        int color = 0;
        switch(actualTurn){
            case "X":
                color = R.color.colorX;
                actualTurn = "O";
                txtTurn.setTextColor(getResources().getColor(R.color.colorO));
                break;
            case "O":
                color = R.color.colorO;
                actualTurn = "X";
                txtTurn.setTextColor(getResources().getColor(R.color.colorX));
                break;
        }
        txtTurn.setText(actualTurn);
        return color;
    }

    public void reboot(){
        tieCounter = 0;
        for (final TextView tv: cells) {
            tv.setText("");
            tv.setClickable(true);
        }
    }
}