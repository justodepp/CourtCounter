package com.example.android.courtcounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    Toolbar toolbar;
    CardView cardWin, cardPoint;
    TextView scoreViewA, scoreViewB, winnerText;
    private int scoreTeamA, scoreTeamB;
    private int gameA, gameB, setA, setB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt("TEAM_A_SCORE");
            scoreTeamB = savedInstanceState.getInt("TEAM_B_SCORE");
        }else
            init();
        setContentView(R.layout.activity_main);
    }

    // This callback is called only when there is a saved instance previously saved using
    // onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("TEAM_A_SCORE", scoreTeamA);
        outState.putInt("TEAM_B_SCORE", scoreTeamB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreViewA.setText(String.valueOf(savedInstanceState.getInt("TEAM_A_SCORE")));
        scoreViewB.setText(String.valueOf(savedInstanceState.getInt("TEAM_B_SCORE")));
    }

    //initialize
    public void init() {
        cardWin = (CardView) findViewById(R.id.card_win);
        cardWin.setVisibility(View.INVISIBLE);
        cardPoint = (CardView) findViewById(R.id.card_point);

        scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);
        winnerText = (TextView) findViewById(R.id.winner);

        setActionOnScrollUp();
    }
    //Set text on actionBar when scrolling up.
    public void setActionOnScrollUp(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("PingPong - Score");
        collapsingToolbar.setExpandedTitleColor(ResourcesCompat.getColor(getResources(), android.R.color.transparent, null));
    }
    //Increase the score for Team A.
    public void addForTeamA(View v) {
        setGameA();
        if(scoreTeamA < 11)
            scoreTeamA ++;
        else
            scoreTeamA = 0;
        displayForTeamA(scoreTeamA);
    }
    //Increase the score for Team B.
    public void addForTeamB(View v) {
        setGameB();
        if(scoreTeamB < 11)
            scoreTeamB ++;
        else
            scoreTeamB = 0;
        displayForTeamB(scoreTeamB);
    }
    //Displays the given score for Team A.
    public void displayForTeamA(int score) {
        scoreViewA.setText(String.valueOf(score));
    }
    // Displays the given score for Team B.
    public void displayForTeamB(int score) {
        scoreViewB.setText(String.valueOf(score));
    }

    public void setGameA(){
        setSetA();
        if (scoreTeamA == 11 && gameA < 3){
            gameA++;
        }else
            gameA = 0;
    }
    public void setGameB(){
        setSetB();
        if (scoreTeamB == 11 && gameB < 3 ){
            gameB++;
        }else
            gameB = 0;
    }

    public void setSetA(){
        if(gameA == 3 && setA < 3)
            setA ++;
        winner();
    }
    public void setSetB(){
        if(gameB == 3 && setB < 3)
            setB ++;
        winner();
    }

    //winner card
    public void winner(){
        if(setA > setB && setA == 3){
            cardWin.setVisibility(View.VISIBLE);
            cardWin.setPadding(cardWin.getPaddingLeft(),16,cardWin.getPaddingRight(),cardWin.getContentPaddingBottom());
            cardPoint.setPadding(cardPoint.getPaddingLeft(),8,cardPoint.getPaddingRight(),cardPoint.getContentPaddingBottom());
            winnerText.setText("team a win");
            winnerText.setAllCaps(true);
        }else if(setB > setA && setB == 3){
            cardWin.setVisibility(View.VISIBLE);
            cardWin.setPadding(cardWin.getPaddingLeft(),16,cardWin.getPaddingRight(),cardWin.getContentPaddingBottom());
            cardPoint.setPadding(cardPoint.getPaddingLeft(),8,cardPoint.getPaddingRight(),cardPoint.getContentPaddingBottom());
            winnerText.setText("team b win");
            winnerText.setAllCaps(true);
        }
    }
    /**
     * Reset the score for Team A and Team B.
    */
    public void resetScore(View v) {
        scoreTeamA = scoreTeamB = gameA = gameB = setA = setB = 0;
        cardWin.setVisibility(View.INVISIBLE);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
