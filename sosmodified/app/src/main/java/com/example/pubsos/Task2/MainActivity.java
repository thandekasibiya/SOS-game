package com.example.pubsos.Task2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pubsos.R;
import com.example.pubsos.Task1.PubSubBroker;

public class MainActivity extends AppCompatActivity
{

    private PubSubBroker pubSubBroker;
    private Controller controller;
    int PlayerTurn = 1;
    int curPos;
    int p1ScoreVal = 0;
    int p2ScoreVal = 0;
    GridLayout gridLayout;
    TextView p1Score;
    TextView p2Score;
    TextView tvTurn;
    Button btnDone;
    String turn = "";
    int NumberofTurns = 25;
    int matches=0;
    int numberofS=0;
    int numberOfO=0;
    String curLetter = "";
    private int player2Turns;
    private int player1Turns;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller();
        player1Turns = 0;
        player2Turns = 0;
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        p1Score = (TextView) findViewById(R.id.p1Score);
        p2Score = (TextView) findViewById(R.id.p2Score);
        btnDone = (Button) findViewById(R.id.btnDone);
        turn = getResources().getString(R.string.player1);
        tvTurn = (TextView) findViewById(R.id.tvTurn);
        tvTurn.setText("Turn: ".concat(turn));
        Toast.makeText(MainActivity.this, turn, Toast.LENGTH_SHORT).show();
        tvTurn.setTextColor(getResources().getColor(R.color.Player1));

        createButtons();
        playGame();
    }

    public void createButtons()
    {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button btn = new Button(this);
                gridLayout.addView(btn,200,200);
            }
        }
    }
    public void setText(Button button)
    {
        if (button.getText() == "") {
            button.setText("S");
            curLetter="S";
        }
        else if (button.getText() == "S") {
            button.setText("O");
            curLetter ="O";
        }
        else
            button.setText("");
    }
    public void playGame()
    {
        for (int i = 0; i < 25; i++) {
            View view = gridLayout.getChildAt(i);
            final Button curBtn = (Button) view;
            final int finalI = i;

            curBtn.setOnClickListener(view12 -> {
                curPos = finalI;
                setText(curBtn);
            });

            btnDone.setOnClickListener(view1 -> {
                findMatch();
                gridLayout.getChildAt(curPos).setEnabled(false);
            });
        }
    }
    private void findMatch()
    {
        Boolean matchFound = false;
        int num = 0;
        for (int i = 0; i < 25; i++) {
            if (!gridLayout.getChildAt(i).isEnabled()) {
                num++;
            }
        }
        if (num == 24) {
            Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();

            if (p1ScoreVal == p2ScoreVal)
                Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_SHORT).show();
            else {
                if (p1ScoreVal > p2ScoreVal)
                    Toast.makeText(MainActivity.this, "Player 1 has won", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Player 2 has won", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            matchFound = LeftToRight();
        } catch (Exception e) {
        }

        if (!matchFound)
            try {
                matchFound = LeftToRightDiagDown();
            } catch (Exception e1) {
            }

        if (!matchFound)
            try {
                matchFound = LeftToRightDiagUp();
            } catch (Exception e2) {
            }

        if (!matchFound)
            try {
                matchFound = RightToLeft();
            } catch (Exception e3) {
            }

        if (!matchFound)
            try {
                matchFound = RightToLeftDiagDown();
            } catch (Exception e4) {
            }

        if (!matchFound)
            try {
                matchFound = RightToLeftDiagUp();
            } catch (Exception e5) {
            }

        if (!matchFound)
            try {
                matchFound = TopDown();
            } catch (Exception e6) {
            }

        if (!matchFound)
            try {
                matchFound = BottomUp();
            } catch (Exception e7) {
            }

        if (!matchFound)
            try {
                matchFound = CenterLeftRight();
            } catch (Exception e8) {
            }

        if (!matchFound)
            try {
                matchFound = CenterTopBottom();
            } catch (Exception e9) {
            }

        if (!matchFound)
            try {
                matchFound = CenterLeftRightDiagonal();
            } catch (Exception e10) {
            }

        if (!matchFound)
            try {
                matchFound = CenterRightLeftDiagonal();
            } catch (Exception e11) {
            }

        if (!matchFound)
            changeTurn();
        else
            {
            if (turn.equals("Player1"))
            {
                p1ScoreVal++;
                if (p1ScoreVal == 1) {

                    p1Score.setText("Player 1: ".concat(String.valueOf(p1ScoreVal)).concat("point"));
                }
                else {
                    p1Score.setText("Player 1: ".concat(String.valueOf(p1ScoreVal)).concat("points"));
                }
            }
            if (turn.equals("Player2")) {
                p2ScoreVal++;
                if (p2ScoreVal == 1)
                    p2Score.setText("Player 2: ".concat(String.valueOf(p2ScoreVal)).concat(" point"));
                else
                    p2Score.setText("Player 2: ".concat(String.valueOf(p2ScoreVal)).concat(" points"));
            }
        }
    }
    private void changeTurn()
    {
        if (PlayerTurn == 1)
        {
            player2Turns++;
            turn = getResources().getString(R.string.player2);
            Toast.makeText(MainActivity.this, turn, Toast.LENGTH_SHORT).show();
            tvTurn.setTextColor(getResources().getColor(R.color.Player2));
            tvTurn.setText("Turn: ".concat(turn));
            PlayerTurn = 2;
        } else {
            player1Turns++;
            turn = getResources().getString(R.string.player1);
            Toast.makeText(MainActivity.this, turn, Toast.LENGTH_SHORT).show();
            tvTurn.setTextColor(getResources().getColor(R.color.Player1));
            tvTurn.setText("Turn: ".concat(turn));
            PlayerTurn = 1;
        }
    }

    private void SOSCompleted(Button S1, Button O, Button S2)
    {
        if (PlayerTurn == 1) {
            S1.setBackgroundColor(getResources().getColor(R.color.Player1));
            O.setBackgroundColor(getResources().getColor(R.color.Player1));
            S2.setBackgroundColor(getResources().getColor(R.color.Player1));
        } else {
            S1.setBackgroundColor(getResources().getColor(R.color.Player2));
            O.setBackgroundColor(getResources().getColor(R.color.Player2));
            S2.setBackgroundColor(getResources().getColor(R.color.Player2));
        }
    }

    public boolean LeftToRight() {
        boolean matchFound = false;

        Button S1 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt(curPos + 1), S2 = (Button) gridLayout.getChildAt(curPos + 2);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean LeftToRightDiagDown() {
        boolean matchFound = false;

        Button S1 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt((curPos + 5) + 1), S2 = (Button) gridLayout.getChildAt((curPos + 10) + 2);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean LeftToRightDiagUp() {
        boolean matchFound = false;

        Button S1 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt((curPos - 5) + 1), S2 = (Button) gridLayout.getChildAt((curPos - 10) + 2);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean RightToLeft() {
        boolean matchFound = false;

        Button S1 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt(curPos - 1), S2 = (Button) gridLayout.getChildAt(curPos - 2);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean RightToLeftDiagDown() {
        boolean matchFound = false;

        Button S1 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt((curPos + 5) - 1), S2 = (Button) gridLayout.getChildAt((curPos + 10) - 2);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean RightToLeftDiagUp() {
        boolean matchFound = false;

        Button S1 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt((curPos - 5) - 1), S2 = (Button) gridLayout.getChildAt((curPos - 10) - 2);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean TopDown() {
        boolean matchFound = false;

        Button S2 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt((curPos + 5)), S1 = (Button) gridLayout.getChildAt((curPos + 10));
        if (S2.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S2, O, S2);
        }

        return matchFound;
    }

    public boolean BottomUp() {
        boolean matchFound = false;

        Button S1 = (Button) gridLayout.getChildAt(curPos), O = (Button) gridLayout.getChildAt((curPos - 5)), S2 = (Button) gridLayout.getChildAt((curPos - 10));
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean CenterLeftRight() {
        boolean matchFound = false;

        Button O = (Button) gridLayout.getChildAt(curPos), S1 = (Button) gridLayout.getChildAt((curPos) - 1), S2 = (Button) gridLayout.getChildAt((curPos) + 1);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean CenterTopBottom() {
        boolean matchFound = false;

        Button O = (Button) gridLayout.getChildAt(curPos), S1 = (Button) gridLayout.getChildAt((curPos) + 5), S2 = (Button) gridLayout.getChildAt((curPos) - 5);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean CenterLeftRightDiagonal() {
        boolean matchFound = false;

        Button O = (Button) gridLayout.getChildAt(curPos), S1 = (Button) gridLayout.getChildAt((curPos - 5) - 1), S2 = (Button) gridLayout.getChildAt((curPos + 5) + 1);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }

    public boolean CenterRightLeftDiagonal() {
        boolean matchFound = false;

        Button O = (Button) gridLayout.getChildAt(curPos), S1 = (Button) gridLayout.getChildAt((curPos - 5) + 1), S2 = (Button) gridLayout.getChildAt((curPos + 5) - 1);
        if (S1.getText().equals("S") && O.getText().equals("O") && S2.getText().equals("S")) {
            matchFound = true;
            SOSCompleted(S1, O, S2);
        }

        return matchFound;
    }


}
