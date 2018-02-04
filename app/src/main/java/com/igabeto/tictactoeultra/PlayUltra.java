package com.igabeto.tictactoeultra;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.Arrays;

public class PlayUltra extends Fragment {

    private TextView[][] cells = new TextView[9][9];
    private TextView[][] cellsPos = new TextView[9][9];

    private int[] boardCounter = new int[9];
    private boolean[] boardPlayable = new boolean[9];

    private TextView txtTurn;
    private TextView winO;
    private TextView winX;

    private int oWins;
    private int xWins;
    private String actualTurn;

    private Animation anim;

    public PlayUltra() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_ultra, container, false);

        this.oWins = 0;
        this.xWins = 0;
        this.actualTurn = "O";

        for(int i = 0; i < 9; i++){
            boardPlayable[i] = true;
            boardCounter[i] = 0;
        }
        
        findCells(view);

        txtTurn = (TextView) view.findViewById(R.id.txtTurn);
        winO = (TextView) view.findViewById(R.id.winO);
        winX = (TextView) view.findViewById(R.id.winX);

        TextView playAgain = (TextView) view.findViewById(R.id.btnPlayAgain);
        TextView mainMenu = (TextView) view.findViewById(R.id.btnMainMenu);

        for (TextView[] tv: cells) {
            for (final TextView tv2: tv) {
                int id = 0;
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setMark(tv2);
                    }
                });
            }

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
    
    public void findCells(View view) {
        //Cuadrante 0
        cellsPos[0][0] = cells[0][0] = (TextView) view.findViewById(R.id.cell00);
        cellsPos[1][0] = cells[0][1] = (TextView) view.findViewById(R.id.cell01);
        cellsPos[2][0] = cells[0][2] = (TextView) view.findViewById(R.id.cell02);
        cellsPos[3][0] = cells[0][3] = (TextView) view.findViewById(R.id.cell10);
        cellsPos[4][0] = cells[0][4] = (TextView) view.findViewById(R.id.cell11);
        cellsPos[5][0] = cells[0][5] = (TextView) view.findViewById(R.id.cell12);
        cellsPos[6][0] = cells[0][6] = (TextView) view.findViewById(R.id.cell20);
        cellsPos[7][0] = cells[0][7] = (TextView) view.findViewById(R.id.cell21);
        cellsPos[8][0] = cells[0][8] = (TextView) view.findViewById(R.id.cell22);

        //Cuadrante 1
        cellsPos[0][1] = cells[1][0] = (TextView) view.findViewById(R.id.cell03);
        cellsPos[1][1] = cells[1][1] = (TextView) view.findViewById(R.id.cell04);
        cellsPos[2][1] = cells[1][2] = (TextView) view.findViewById(R.id.cell05);
        cellsPos[3][1] = cells[1][3] = (TextView) view.findViewById(R.id.cell13);
        cellsPos[4][1] = cells[1][4] = (TextView) view.findViewById(R.id.cell14);
        cellsPos[5][1] = cells[1][5] = (TextView) view.findViewById(R.id.cell15);
        cellsPos[6][1] = cells[1][6] = (TextView) view.findViewById(R.id.cell23);
        cellsPos[7][1] = cells[1][7] = (TextView) view.findViewById(R.id.cell24);
        cellsPos[8][1] = cells[1][8] = (TextView) view.findViewById(R.id.cell25);

        //Cuadrante 2
        cellsPos[0][2] = cells[2][0] = (TextView) view.findViewById(R.id.cell06);
        cellsPos[1][2] = cells[2][1] = (TextView) view.findViewById(R.id.cell07);
        cellsPos[2][2] = cells[2][2] = (TextView) view.findViewById(R.id.cell08);
        cellsPos[3][2] = cells[2][3] = (TextView) view.findViewById(R.id.cell16);
        cellsPos[4][2] = cells[2][4] = (TextView) view.findViewById(R.id.cell17);
        cellsPos[5][2] = cells[2][5] = (TextView) view.findViewById(R.id.cell18);
        cellsPos[6][2] = cells[2][6] = (TextView) view.findViewById(R.id.cell26);
        cellsPos[7][2] = cells[2][7] = (TextView) view.findViewById(R.id.cell27);
        cellsPos[8][2] = cells[2][8] = (TextView) view.findViewById(R.id.cell28);

        //Cuadrante 3
        cellsPos[0][3] = cells[3][0] = (TextView) view.findViewById(R.id.cell30);
        cellsPos[1][3] = cells[3][1] = (TextView) view.findViewById(R.id.cell31);
        cellsPos[2][3] = cells[3][2] = (TextView) view.findViewById(R.id.cell32);
        cellsPos[3][3] = cells[3][3] = (TextView) view.findViewById(R.id.cell40);
        cellsPos[4][3] = cells[3][4] = (TextView) view.findViewById(R.id.cell41);
        cellsPos[5][3] = cells[3][5] = (TextView) view.findViewById(R.id.cell42);
        cellsPos[6][3] = cells[3][6] = (TextView) view.findViewById(R.id.cell50);
        cellsPos[7][3] = cells[3][7] = (TextView) view.findViewById(R.id.cell51);
        cellsPos[8][3] = cells[3][8] = (TextView) view.findViewById(R.id.cell52);

        //Cuadrante 4
        cellsPos[0][4] = cells[4][0] = (TextView) view.findViewById(R.id.cell33);
        cellsPos[1][4] = cells[4][1] = (TextView) view.findViewById(R.id.cell34);
        cellsPos[2][4] = cells[4][2] = (TextView) view.findViewById(R.id.cell35);
        cellsPos[3][4] = cells[4][3] = (TextView) view.findViewById(R.id.cell43);
        cellsPos[4][4] = cells[4][4] = (TextView) view.findViewById(R.id.cell44);
        cellsPos[5][4] = cells[4][5] = (TextView) view.findViewById(R.id.cell45);
        cellsPos[6][4] = cells[4][6] = (TextView) view.findViewById(R.id.cell53);
        cellsPos[7][4] = cells[4][7] = (TextView) view.findViewById(R.id.cell54);
        cellsPos[8][4] = cells[4][8] = (TextView) view.findViewById(R.id.cell55);

        //Cuadrante 5
        cellsPos[0][5] = cells[5][0] = (TextView) view.findViewById(R.id.cell36);
        cellsPos[1][5] = cells[5][1] = (TextView) view.findViewById(R.id.cell37);
        cellsPos[2][5] = cells[5][2] = (TextView) view.findViewById(R.id.cell38);
        cellsPos[3][5] = cells[5][3] = (TextView) view.findViewById(R.id.cell46);
        cellsPos[4][5] = cells[5][4] = (TextView) view.findViewById(R.id.cell47);
        cellsPos[5][5] = cells[5][5] = (TextView) view.findViewById(R.id.cell48);
        cellsPos[6][5] = cells[5][6] = (TextView) view.findViewById(R.id.cell56);
        cellsPos[7][5] = cells[5][7] = (TextView) view.findViewById(R.id.cell57);
        cellsPos[8][5] = cells[5][8] = (TextView) view.findViewById(R.id.cell58);

        //Cuadrante 6
        cellsPos[0][6] = cells[6][0] = (TextView) view.findViewById(R.id.cell60);
        cellsPos[1][6] = cells[6][1] = (TextView) view.findViewById(R.id.cell61);
        cellsPos[2][6] = cells[6][2] = (TextView) view.findViewById(R.id.cell62);
        cellsPos[3][6] = cells[6][3] = (TextView) view.findViewById(R.id.cell70);
        cellsPos[4][6] = cells[6][4] = (TextView) view.findViewById(R.id.cell71);
        cellsPos[5][6] = cells[6][5] = (TextView) view.findViewById(R.id.cell72);
        cellsPos[6][6] = cells[6][6] = (TextView) view.findViewById(R.id.cell80);
        cellsPos[7][6] = cells[6][7] = (TextView) view.findViewById(R.id.cell81);
        cellsPos[8][6] = cells[6][8] = (TextView) view.findViewById(R.id.cell82);

        //Cuadrante 7
        cellsPos[0][7] = cells[7][0] = (TextView) view.findViewById(R.id.cell63);
        cellsPos[1][7] = cells[7][1] = (TextView) view.findViewById(R.id.cell64);
        cellsPos[2][7] = cells[7][2] = (TextView) view.findViewById(R.id.cell65);
        cellsPos[3][7] = cells[7][3] = (TextView) view.findViewById(R.id.cell73);
        cellsPos[4][7] = cells[7][4] = (TextView) view.findViewById(R.id.cell74);
        cellsPos[5][7] = cells[7][5] = (TextView) view.findViewById(R.id.cell75);
        cellsPos[6][7] = cells[7][6] = (TextView) view.findViewById(R.id.cell83);
        cellsPos[7][7] = cells[7][7] = (TextView) view.findViewById(R.id.cell84);
        cellsPos[8][7] = cells[7][8] = (TextView) view.findViewById(R.id.cell85);

        //Cuadrante 8
        cellsPos[0][8] = cells[8][0] = (TextView) view.findViewById(R.id.cell66);
        cellsPos[1][8] = cells[8][1] = (TextView) view.findViewById(R.id.cell67);
        cellsPos[2][8] = cells[8][2] = (TextView) view.findViewById(R.id.cell68);
        cellsPos[3][8] = cells[8][3] = (TextView) view.findViewById(R.id.cell76);
        cellsPos[4][8] = cells[8][4] = (TextView) view.findViewById(R.id.cell77);
        cellsPos[5][8] = cells[8][5] = (TextView) view.findViewById(R.id.cell78);
        cellsPos[6][8] = cells[8][6] = (TextView) view.findViewById(R.id.cell86);
        cellsPos[7][8] = cells[8][7] = (TextView) view.findViewById(R.id.cell87);
        cellsPos[8][8] = cells[8][8] = (TextView) view.findViewById(R.id.cell88);
    }

    public void setMark(TextView tv){
        boolean winner = false;
        tv.setText(actualTurn);
        for(int i = 0; i < 9; i++) {
            if(Arrays.asList(cells[i]).contains(tv)){
                boardCounter[i]++;
                winner= checkWinner(i);
                }
                if(boardCounter[i] == 9){
                    boardPlayable[i] = false;
                }
            }

        if(winner){
            finishGame();
        }
        else{
            allCellsDisabled();
            enableNextBoard(String.valueOf(tv.getTag()), boardPlayable[Integer.parseInt(String.valueOf(tv.getTag()))]);
            disableFullBoards();

            tv.setText(actualTurn);
            tv.setTextColor(getResources().getColor(changeTurn()));
            tv.setClickable(false);
        }


    }

    public void allCellsEnabled(){
        for(TextView[] tv : cells){
            for(TextView tv2: tv){
                tv2.setBackground(getResources().getDrawable(R.drawable.cells_border_grey));
                tv2.setClickable(true);
            }
        }
        for (int i = 0; i < 9; i = i+2) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setBackground(getResources().getDrawable(R.drawable.cells_border));
            }
        }
    }

    public void allCellsDisabled(){
        for(TextView[] tv : cells){
            for(TextView tv2: tv){
                tv2.setBackground(getResources().getDrawable(R.drawable.cells_border_disabled_strong));
                tv2.setClickable(false);
            }
        }
        for (int i = 0; i < 9; i = i+2) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setBackground(getResources().getDrawable(R.drawable.cells_border_disabled));
            }
        }
    }

    public void enableNextBoard(String tag, boolean bool){
        if (bool){
            for (int i = 0; i < 9; i++){
                cells[Integer.parseInt(tag)][i].setBackground(getResources().getDrawable(R.drawable.cells_border));
                if(cells[Integer.parseInt(tag)][i].getText().equals("")){
                    cells[Integer.parseInt(tag)][i].setClickable(true);
                }
            }
        }
        else{
            allCellsEnabled();
            Log.d("TAG", "FULL");
        }

    }

    public void disableFullBoards(){
        for (int i = 0; i < 9; i++){
            if(!boardPlayable[i]){
                for (TextView tv : cells[i]){
                    tv.setClickable(false);
                    tv.setBackground(getResources().getDrawable(R.drawable.cells_border_disabled_full));
                }
            }
        }
    }

    public boolean checkWinner(int i){
        for (TextView tv : cells[i]){
            Log.d("TAG", tv.getText().toString());
        }
        if(cells[i][0].getText().toString().equals(actualTurn) && cells[i][1].getText().toString().equals(actualTurn) && cells[i][2].getText().toString().equals(actualTurn) ||
           cells[i][3].getText().toString().equals(actualTurn) && cells[i][4].getText().toString().equals(actualTurn) && cells[i][5].getText().toString().equals(actualTurn) ||
           cells[i][6].getText().toString().equals(actualTurn) && cells[i][7].getText().toString().equals(actualTurn) && cells[i][8].getText().toString().equals(actualTurn) ||
           cells[i][0].getText().toString().equals(actualTurn) && cells[i][3].getText().toString().equals(actualTurn) && cells[i][6].getText().toString().equals(actualTurn) ||
           cells[i][1].getText().toString().equals(actualTurn) && cells[i][4].getText().toString().equals(actualTurn) && cells[i][7].getText().toString().equals(actualTurn) ||
           cells[i][2].getText().toString().equals(actualTurn) && cells[i][5].getText().toString().equals(actualTurn) && cells[i][8].getText().toString().equals(actualTurn) ||
           cells[i][0].getText().toString().equals(actualTurn) && cells[i][4].getText().toString().equals(actualTurn) && cells[i][8].getText().toString().equals(actualTurn) ||
           cells[i][2].getText().toString().equals(actualTurn) && cells[i][4].getText().toString().equals(actualTurn) && cells[i][6].getText().toString().equals(actualTurn)){
                boardPlayable[i] = false;
                switch(actualTurn){
                    case "O":
                        oWins++;
                        String txtO = "O: " + oWins;
                        winO.setText(txtO);
                        if(oWins == 3){
                            anim.setRepeatCount(Animation.INFINITE);
                            winO.startAnimation(anim);
                            return true;
                        }
                        break;
                    case "X":
                        xWins++;
                        String txtX = "X: " + xWins;
                        winX.setText(txtX);
                        if(xWins == 3){
                            anim.setRepeatCount(Animation.INFINITE);
                            winX.startAnimation(anim);
                            return true;
                        }
                        break;
                }
        }
        return false;
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
        allCellsEnabled();
        winO.setText("O: 0");
        winX.setText("X: 0");
        oWins = 0;
        xWins = 0;
        for (int i = 0; i < 9; i++) {
            boardCounter[i] = 0;
            boardPlayable[i] = true;
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText("");
                cells[i][j].setClickable(true);
            }
        }
    }

    public void finishGame(){
        for (TextView[] tv : cells){
            for (TextView tv2 : tv){
                tv2.setBackground(getResources().getDrawable(R.drawable.cells_border_disabled_full));
                tv2.setClickable(false);
            }
        }
    }
}