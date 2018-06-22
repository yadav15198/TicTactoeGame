package com.example.hp.tictactoegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
LinearLayout rootLayout;
ArrayList<LinearLayout>rows;
public static final int No_player = -1;
public static final int player_X = 0;
public static final int player_0 = 1;
public static final int incomplete = 2;
public static final int Player_0Won = 3 ;
public static final int Player_XWon = 4;
public static final int drawn = 5;
    TTTclass [][] board;
    public int currentstatus = incomplete;
    public int currentplayer ;
    int size = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = findViewById(R.id.linearLayout);
setupBoard();
    }
    void setupBoard() {
        currentstatus = incomplete;
        currentplayer = player_0;
        rows = new ArrayList<>();
        board = new TTTclass[size][size];
        rootLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
            linearLayout.setLayoutParams(layoutParams);
            rootLayout.addView(linearLayout);
            rows.add(linearLayout);
        }

        for (int i = 0; i < size; i++) {
for (int j = 0; j < size; j++){
  TTTclass  button = new TTTclass(this);

  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
  button.setLayoutParams(layoutParams);
  LinearLayout row = rows.get(i);
  button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
  button.setOnClickListener(this);
  row.addView(button);
  board[i][j] = button;
}
        }
    }
    @Override

    public void onClick(View view) {
if(currentstatus == incomplete){
    TTTclass button = (TTTclass) view;
button.setPlayer(currentplayer);
    chaeckstatus();
toggleplayer();
}
        }
  public void toggleplayer(){
        if(currentplayer ==  player_0 ){
            currentplayer = player_X;}
        else
            currentplayer = player_0;
       }

private void chaeckstatus() {
        //rows
    for (int i = 0; i < size; i++) {
        boolean rowsame = true;
        TTTclass firstbutton = board[i][0];
        for (int j = 1; j < size; j++) {
            TTTclass button = board[i][j];
            if (button.isEmpty() || button.getplayer() != firstbutton.getplayer()) {
                rowsame = false;
                break;
            }
        }
        if (rowsame) {
            int playerwon = firstbutton.getplayer();
            updatestatus(playerwon);
            return;
        }
    }
        //column
    for (int i = 0; i < size; i++) {
        boolean colsame = true;
        TTTclass firstbutton = board[0][i];
        for (int j = 1; j < size; j++) {
            TTTclass button = board[j][i];
            if (button.isEmpty() || button.getplayer() != firstbutton.getplayer()) {
                colsame = false;
                break;
            }
        }
        if (colsame) {
            int playerwon = firstbutton.getplayer();

            updatestatus(playerwon);
            return;
        }
    }
//diag1

        boolean digsame = true;
        TTTclass firstbutton = board[0][size -1];
        for (int j = 0; j < size; j++) {
            TTTclass button = board[j][size-1-j];
            if (button.isEmpty() || button.getplayer() != firstbutton.getplayer()) {
                digsame = false;
                break;
            }
        }
        if (digsame) {
            int playerwon = firstbutton.getplayer();
            updatestatus(playerwon);
            return;
        }

   // diag2

        boolean dig2same = true;
        TTTclass firstbutton1 = board[0][0];
        for (int j = 1; j < size; j++) {
            TTTclass button = board[j][j];
            if (button.isEmpty() || button.getplayer() != firstbutton1.getplayer()) {
                dig2same = false;
                break;
            }
        }
        if (dig2same) {
            int playerwon = firstbutton1.getplayer();
            updatestatus(playerwon);
            return;
        }
        for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            TTTclass button = board[i][j];
            if (button.isEmpty()) {
                return;
            }
        }
    }
    Toast.makeText(this,"match_Drawn",Toast.LENGTH_LONG).show();
currentstatus = drawn;
}
public void updatestatus(int p){
        if(p == player_0){
            currentstatus =Player_0Won;

            Toast.makeText(this,"player_0won",Toast.LENGTH_LONG).show();
        }
        else if( p == player_X){
            currentstatus =  Player_XWon ;
            Toast.makeText(this," Player_XWon",Toast.LENGTH_LONG).show();
        }

}
public boolean onCreateOptionsMenu(Menu menu){
    MenuInflater InFlator = getMenuInflater();
    InFlator.inflate(R.menu.main_menu,menu);
    return true;
}
public boolean onOptionsItemSelected(MenuItem item){
    int id = item.getItemId();
    if(id == R.id.reset){
        setupBoard();}
        else if(id == R.id.size3){
        size = 3;
            setupBoard();}
         else if (id == R.id.size4) {
            size = 4;
            setupBoard();
        } else if (id == R.id.size5) {
            size = 5;
            setupBoard();
        }

    return super.onOptionsItemSelected(item);
}

}
