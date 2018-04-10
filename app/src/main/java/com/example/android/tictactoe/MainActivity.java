package com.example.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
 Modified by danmoindi on 08/03/18.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    DESCRIPTION OF VARIABLES

    TAG ---------> Used for log debugging
    PLAYER_X ----> Variable to identify player turn
    TURN_COUNT --> Variable to keep count of number of turns
    b00 - b22 ---> Button for each 3x3 box
    tvStatus ------> Information regarding game status
    tvXPoints ------> Information regarding player X points
    tv0Points ------> Information regarding player 0 points
    boardStatus -> 2-d array to identify board status
        -1 means, No one has played on that box yet
        0 means, Player 0 has played on that box
        1 means, Player X has played on that box
    */

    /*
    DESCRIPTION OF METHODS

    onClick --------> Called whenever any box or reset button is clicked.
        This method places either X or 0 according to player turn
        and updates other information

    checkWinner ----> This method checks the board status and identifies the winner
        It checks for all the 3 rows, 3 columns and 2 diagonals

    resetBoard -----> This method is called whenever reset button is pressed.
        updates the board to initial values of the game

    resetGame -----> This method is called whenever new button is pressed.
        updates everything to initial values of the game

    updatePointsText -> This method updates points in the TextView

    enableAllBoxes -> This method can either enable or disable all boxes

    setInfo --------> This method updates the string in TextView

    result ---------> This method works whenever a player wins or game draws

    initializeBoardStatus -> Method which set the value of integer array to -1

    */

    private final static String TAG = MainActivity.class.getSimpleName();
    boolean PLAYER_X = true;

    int TURN_COUNT = 0;

    private int playerXPoints;
    private int player0Points;

    Button b00;
    Button b01;
    Button b02;

    Button b10;
    Button b11;
    Button b12;

    Button b20;
    Button b21;
    Button b22;

    Button bReset;
    Button bNewGame;

    TextView tvStatus;
    TextView tvXPoints;
    TextView tv0Points;

    int[][] boardStatus = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b00 = findViewById(R.id.b00);
        b01 = findViewById(R.id.b01);
        b02 = findViewById(R.id.b02);

        b10 = findViewById(R.id.b10);
        b11 = findViewById(R.id.b11);
        b12 = findViewById(R.id.b12);

        b20 = findViewById(R.id.b20);
        b21 = findViewById(R.id.b21);
        b22 = findViewById(R.id.b22);

        bReset = findViewById(R.id.bReset);
        bNewGame = findViewById(R.id.bNewGame);
        tvStatus = findViewById(R.id.tvStatus);
        tvXPoints = findViewById(R.id.tvXPoints);
        tv0Points = findViewById(R.id.tv0Points);

        bReset.setOnClickListener(this);
        bNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        b00.setOnClickListener(this);
        b01.setOnClickListener(this);
        b02.setOnClickListener(this);

        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);

        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);

        initializeBoardStatus();

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "Inside onClick");

        boolean resetButtonPressed = false;

        switch (view.getId()) {
            case R.id.b00:
                if (PLAYER_X) {
                    b00.setText("X");
                    boardStatus[0][0] = 1;
                } else {
                    b00.setText("0");
                    boardStatus[0][0] = 0;
                }
                b00.setEnabled(false);
                break;

            case R.id.b01:
                if (PLAYER_X) {
                    b01.setText("X");
                    boardStatus[0][1] = 1;
                } else {
                    b01.setText("0");
                    boardStatus[0][1] = 0;
                }
                b01.setEnabled(false);
                break;

            case R.id.b02:
                if (PLAYER_X) {
                    b02.setText("X");
                    boardStatus[0][2] = 1;
                } else {
                    b02.setText("0");
                    boardStatus[0][2] = 0;
                }
                b02.setEnabled(false);
                break;

            case R.id.b10:
                if (PLAYER_X) {
                    b10.setText("X");
                    boardStatus[1][0] = 1;
                } else {
                    b10.setText("0");
                    boardStatus[1][0] = 0;
                }
                b10.setEnabled(false);
                break;

            case R.id.b11:
                if (PLAYER_X) {
                    b11.setText("X");
                    boardStatus[1][1] = 1;
                } else {
                    b11.setText("0");
                    boardStatus[1][1] = 0;
                }
                b11.setEnabled(false);
                break;

            case R.id.b12:
                if (PLAYER_X) {
                    b12.setText("X");
                    boardStatus[1][2] = 1;
                } else {
                    b12.setText("0");
                    boardStatus[1][2] = 0;
                }
                b12.setEnabled(false);
                break;

            case R.id.b20:
                if (PLAYER_X) {
                    b20.setText("X");
                    boardStatus[2][0] = 1;
                } else {
                    b20.setText("0");
                    boardStatus[2][0] = 0;
                }
                b20.setEnabled(false);
                break;

            case R.id.b21:
                if (PLAYER_X) {
                    b21.setText("X");
                    boardStatus[2][1] = 1;
                } else {
                    b21.setText("0");
                    boardStatus[2][1] = 0;
                }
                b21.setEnabled(false);
                break;

            case R.id.b22:
                if (PLAYER_X) {
                    b22.setText("X");
                    boardStatus[2][2] = 1;
                } else {
                    b22.setText("0");
                    boardStatus[2][2] = 0;
                }
                b22.setEnabled(false);
                break;

            case R.id.bReset:
                resetButtonPressed = true;
                break;

            default:
                break;

        }

        if (resetButtonPressed) {
            resetBoard();
        } else {
            TURN_COUNT++;
            PLAYER_X = !PLAYER_X;

            if (PLAYER_X) {
                setInfo("Player X\nturn");
            } else {
                setInfo("Player 0\nturn");
            }

            if (TURN_COUNT == 9) {
                result("Game Draw");
            }

            checkWinner();
        }
    }

    private void checkWinner() {

        Log.d(TAG, "Inside checkWinner");

        //Horizontal --- rows
        for (int i = 0; i < 3; i++) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    result("X winner\n" + (i + 1) + " row");
                    playerXPoints++;
                    updatePointsText();
                    break;
                } else if (boardStatus[i][0] == 0) {
                    result("0 winner\n" + (i + 1) + " row");
                    player0Points++;
                    updatePointsText();
                    break;
                }
            }
        }

        //Vertical --- columns
        for (int i = 0; i < 3; i++) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    result("X winner\n" + (i + 1) + " column");
                    playerXPoints++;
                    updatePointsText();
                    break;
                } else if (boardStatus[0][i] == 0) {
                    result("0 winner\n" + (i + 1) + " column");
                    player0Points++;
                    updatePointsText();
                    break;
                }
            }
        }

        //First diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                result(" X winner\nFirst Diagonal");
                playerXPoints++;
                updatePointsText();

            } else if (boardStatus[0][0] == 0) {
                result("0 winner\nFirst Diagonal");
                player0Points++;
                updatePointsText();

            }
        }

        //Second diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                result("X winner\nSecond Diagonal");
                playerXPoints++;
                updatePointsText();

            } else if (boardStatus[0][2] == 0) {
                result("0 winner\nSecond Diagonal");
                player0Points++;
                updatePointsText();

            }
        }
    }


    private void updatePointsText() {
        tvXPoints.setText("X Points:\n" + playerXPoints);
        tv0Points.setText("0 Points:\n" + player0Points);
    }

    private void enableAllBoxes(boolean value) {
        Log.d(TAG, "Inside enableAllBoxes");
        b00.setEnabled(value);
        b01.setEnabled(value);
        b02.setEnabled(value);

        b10.setEnabled(value);
        b11.setEnabled(value);
        b12.setEnabled(value);

        b20.setEnabled(value);
        b21.setEnabled(value);
        b22.setEnabled(value);
    }

    private void result(String winner) {
        Log.d(TAG, "Inside result");

        setInfo(winner);
        enableAllBoxes(false);
    }

    private void resetBoard() {
        Log.d(TAG, "Inside resetBoard");
        b00.setText("");
        b01.setText("");
        b02.setText("");

        b10.setText("");
        b11.setText("");
        b12.setText("");

        b20.setText("");
        b21.setText("");
        b22.setText("");

        enableAllBoxes(true);

        PLAYER_X = true;
        TURN_COUNT = 0;

        initializeBoardStatus();

        setInfo("Next\nRound");

        Toast.makeText(this, "Board Reset", Toast.LENGTH_SHORT).show();
    }

    private void setInfo(String text) {
        tvStatus.setText(text);
    }

    private void initializeBoardStatus() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardStatus[i][j] = -1;
            }
        }

    }

    private void resetGame() {
        playerXPoints = 0;
        player0Points = 0;
        updatePointsText();
        resetBoard();
        {
            setInfo("New\nGame");

            Toast.makeText(this, "Game Reset", Toast.LENGTH_SHORT).show();
        }
    }

//Saves the  current orientation state

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("TURN_COUNT", TURN_COUNT);
        outState.putInt("playerXPoints", playerXPoints);
        outState.putInt("player2Points", player0Points);

    }

    //Retrives information from the previous orientation to current orientation state
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TURN_COUNT = savedInstanceState.getInt("TURN_COUNT");
        playerXPoints = savedInstanceState.getInt("playerXPoints");
        player0Points = savedInstanceState.getInt("player0Points");

    }


}