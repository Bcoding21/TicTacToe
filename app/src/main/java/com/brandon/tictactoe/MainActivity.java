package com.brandon.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] buttons = new Button[10];
    private TicTacToeLogic.TTTElement[] board_status = new TicTacToeLogic.TTTElement[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void onClick(View v) {

        if (v.getId() == buttons[9].getId()) {  // if "New game" is clicked
            buttons[9].setVisibility(View.INVISIBLE);
            activate_buttons();
            reset_board();
        }

        else {
            Button some_button = (Button) findViewById(v.getId());

            if (some_button.getText() == "") {

                some_button.setText("X");
                update_board_status(some_button, TicTacToeLogic.TTTElement.X);

                if (TicTacToeLogic.isGameOver(board_status)) {
                    if (buttons[9].getVisibility() == View.INVISIBLE) {
                        buttons[9].setVisibility(View.VISIBLE);
                        buttons[9].setText("New Game");
                        deactivate_buttons();
                    }
                }

                else {
                    int best_position_index = TicTacToeLogic.getBestMove(board_status);
                    buttons[best_position_index].setText("O");
                    update_board_status(buttons[best_position_index], TicTacToeLogic.TTTElement.O);

                    if (TicTacToeLogic.isGameOver(board_status)) {
                        if (buttons[9].getVisibility() == View.INVISIBLE) {
                            buttons[9].setVisibility(View.VISIBLE);
                            buttons[9].setText("New Game");
                            deactivate_buttons();
                            highlight_winning_row();
                        }
                    }
                }
            }
        }
    }

    public void update_board_status(Button b, TicTacToeLogic.TTTElement type){
        for (int i = 0; i < buttons.length; i++){
            if (buttons[i].getId() == b.getId()){
                board_status[i] = type;
                break;
            }
        }
    }

    public void reset_board(){
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setText("");
        }
        for (int i = 0; i < board_status.length; i++){
            board_status[i] = TicTacToeLogic.TTTElement.EMPTY;
        }
    }

    public void deactivate_buttons(){
        for (int i = 0; i < buttons.length -1; i++){
            buttons[i].setEnabled(false);
        }
    }

    public void activate_buttons(){
        for (int i = 0; i < buttons.length -1; i++){
            buttons[i].setEnabled(true);
        }
    }

    public void initialize() {
        buttons[0] = (Button) findViewById(R.id.but1);
        buttons[1] = (Button) findViewById(R.id.but2);
        buttons[2] = (Button) findViewById(R.id.but3);
        buttons[3] = (Button) findViewById(R.id.but4);
        buttons[4] = (Button) findViewById(R.id.but5);
        buttons[5] = (Button) findViewById(R.id.but6);
        buttons[6] = (Button) findViewById(R.id.but7);
        buttons[7] = (Button) findViewById(R.id.but8);
        buttons[8] = (Button) findViewById(R.id.but9);
        buttons[9] = (Button) findViewById(R.id.but10);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(MainActivity.this);
        }

        for (int i = 0; i < board_status.length; i++) {
            board_status[i] = TicTacToeLogic.TTTElement.EMPTY;
        }
    }


    public void highlight_winning_row(){

    }








}






