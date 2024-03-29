package com.brandon.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] mButtons = new Button[10];
    private TicTacToeLogic.TTTElement[] mBoardStatus = new TicTacToeLogic.TTTElement[9];
    private Button mTempButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void onClick(View v) {

        if (v.getId() == mButtons[9].getId()) {  // if "New game" is clicked
            mButtons[9].setVisibility(View.INVISIBLE);
            activate_buttons();
            reset_board();
        }

        else { // if a tic-tac-toe box is clicked
            mTempButton = (Button)findViewById(v.getId());

            if (mTempButton.getText() == "") { // set text to "x" and update board if text is empty
                mTempButton.setText("X");
                update_board_status(mTempButton, TicTacToeLogic.TTTElement.X);

                if (TicTacToeLogic.isGameOver(mBoardStatus)) { // check if game has been won
                    if (mButtons[9].getVisibility() == View.INVISIBLE) {
                        mButtons[9].setVisibility(View.VISIBLE);
                        mButtons[9].setText("New Game");
                        deactivate_buttons();
                    }
                }

                else {
                    int best_position_index = TicTacToeLogic.getBestMove(mBoardStatus);
                    mButtons[best_position_index].setText("O");
                    update_board_status(mButtons[best_position_index], TicTacToeLogic.TTTElement.O);

                    if (TicTacToeLogic.isGameOver(mBoardStatus)) {
                        if (mButtons[9].getVisibility() == View.INVISIBLE) {
                            mButtons[9].setVisibility(View.VISIBLE);
                            mButtons[9].setText("New Game");
                            deactivate_buttons();
                        }
                    }
                }
            }
        }
    }

    public void update_board_status(Button b, TicTacToeLogic.TTTElement type){
        for (int i = 0; i < mButtons.length; i++){
            if (mButtons[i].getId() == b.getId()){
                mBoardStatus[i] = type;
                break;
            }
        }
    }

    public void reset_board(){
        for (int i = 0; i < mButtons.length; i++){
            mButtons[i].setText("");
        }
        for (int i = 0; i < mBoardStatus.length; i++){
            mBoardStatus[i] = TicTacToeLogic.TTTElement.EMPTY;
        }
    }

    public void deactivate_buttons(){
        for (int i = 0; i < mButtons.length -1; i++){
            mButtons[i].setEnabled(false);
        }
    }

    public void activate_buttons(){
        for (int i = 0; i < mButtons.length -1; i++){
            mButtons[i].setEnabled(true);
        }
    }

    public void initialize() {
        mButtons[0] = (Button) findViewById(R.id.but1);
        mButtons[1] = (Button) findViewById(R.id.but2);
        mButtons[2] = (Button) findViewById(R.id.but3);
        mButtons[3] = (Button) findViewById(R.id.but4);
        mButtons[4] = (Button) findViewById(R.id.but5);
        mButtons[5] = (Button) findViewById(R.id.but6);
        mButtons[6] = (Button) findViewById(R.id.but7);
        mButtons[7] = (Button) findViewById(R.id.but8);
        mButtons[8] = (Button) findViewById(R.id.but9);
        mButtons[9] = (Button) findViewById(R.id.but10);

        for (int i = 0; i < mButtons.length; i++) {
            mButtons[i].setOnClickListener(MainActivity.this);
        }

        for (int i = 0; i < mBoardStatus.length; i++) {
            mBoardStatus[i] = TicTacToeLogic.TTTElement.EMPTY;
        }
    }

}


