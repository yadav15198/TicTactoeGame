package com.example.hp.tictactoegame;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.widget.Button;

public class TTTclass extends AppCompatButton{
    private int player = MainActivity.No_player;
    public TTTclass(Context context) {
        super(context);

    }
    public void setPlayer(int player){
        this.player = player;
        if(player == MainActivity.player_X){
            setText("X");
        }
        else if(player == MainActivity.player_0)
            setText("0");
        setEnabled(false);
    }
    public int getplayer(){
        return this.player;
    }
 public    boolean isEmpty(){
        return player == MainActivity.No_player;
    }
}

