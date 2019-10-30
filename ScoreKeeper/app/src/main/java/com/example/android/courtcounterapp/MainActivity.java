package com.example.android.courtcounterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewA.setText(String.valueOf(score));
    }
    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(score));
    }
    public void plus3A(View va){
        scoreTeamA = scoreTeamA +3;
        displayForTeamA(scoreTeamA);
    }
    public void plus2A(View va){
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }
    public void plus1A(View va){
        scoreTeamA = scoreTeamA +1;
        displayForTeamA(scoreTeamA);
    }
    public void plus3B(View v){
        scoreTeamB = scoreTeamB  +3;
        displayForTeamB(scoreTeamB );
    }
    public void plus2B(View v){
        scoreTeamB  = scoreTeamB  + 2;
        displayForTeamB(scoreTeamB );
    }
    public void plus1B(View v){
        scoreTeamB  = scoreTeamB  +1;
        displayForTeamB(scoreTeamB );
    }
    public void reset(View v){
        scoreTeamA = 0;
        scoreTeamB=0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
