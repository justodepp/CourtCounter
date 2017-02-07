package com.example.android.courtcounter;

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
    TextView scoreViewA, scoreViewB, winnerText, gameViewA, gameViewB, setViewA, setViewB;
    private int scoreTeamA, scoreTeamB;
    private int gameA, gameB, setA, setB;
    private boolean advA, advB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recovering the instance state
        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt("TEAM_A_SCORE");
            scoreTeamB = savedInstanceState.getInt("TEAM_B_SCORE");
            gameA = savedInstanceState.getInt("TEAM_A_GAME");
            gameB = savedInstanceState.getInt("TEAM_B_GAME");
            setA = savedInstanceState.getInt("TEAM_A_SET");
            setB = savedInstanceState.getInt("TEAM_B_SET");
        }else
            init();
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
        outState.putInt("TEAM_A_GAME", gameA);
        outState.putInt("TEAM_B_GAME", gameB);
        outState.putInt("TEAM_A_SET", setA);
        outState.putInt("TEAM_B_SET", setB);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scoreViewA.setText(String.valueOf(savedInstanceState.getInt("TEAM_A_SCORE")));
        scoreViewB.setText(String.valueOf(savedInstanceState.getInt("TEAM_B_SCORE")));
        gameViewA.setText(String.valueOf(savedInstanceState.getInt("TEAM_A_GAME")));
        gameViewB.setText(String.valueOf(savedInstanceState.getInt("TEAM_B_GAME")));
        setViewA.setText(String.valueOf(savedInstanceState.getInt("TEAM_A_SET")));
        setViewB.setText(String.valueOf(savedInstanceState.getInt("TEAM_B_SET")));
    }

    //initialize
    public void init() {
        cardWin = (CardView) findViewById(R.id.card_win);
        cardPoint = (CardView) findViewById(R.id.card_point);

        scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);

        gameViewA = (TextView) findViewById(R.id.game_view_A);
        gameViewB = (TextView) findViewById(R.id.game_view_B);

        setViewA = (TextView) findViewById(R.id.set_view_A);
        setViewB = (TextView) findViewById(R.id.set_view_B);

        winnerText = (TextView) findViewById(R.id.winner);

        advA = advB = false;

        setActionOnScrollUp();
    }
    //Set text on actionBar when scrolling up.
    public void setActionOnScrollUp(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("PingPong - Score");
        collapsingToolbar.setExpandedTitleColor(ResourcesCompat.getColor(getResources(), android.R.color.transparent, null));
        //collapsingToolbar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
    }
    //Increase the score.
    public void addForTeamA(View v) {
        if(scoreTeamA < 11)
            scoreTeamA ++;
        else if(scoreTeamA == 10 && scoreTeamA == scoreTeamB)
            if(!advA && !advB)
                advA = true;
            if(!advA && advB)
                advB = false;
            if(advA) {
                advA = false;
                setGameA();
            }
        displayForTeamA(scoreTeamA);
    }
    public void addForTeamB(View v) {
        if(scoreTeamB < 11)
            scoreTeamB ++;
        else if(scoreTeamB == 10 && scoreTeamA == scoreTeamB)
            if(!advB && !advA)
                advB = true;
        if(!advB && advA)
            advA = false;
        if(advB) {
            advB = false;
            setGameB();
        }
        displayForTeamA(scoreTeamB);
    }
    //Control Game point
    public void setGameA(){
        if (scoreTeamA == 11 && gameA < 3){
            gameA++;
            scoreTeamA = 0;
        }else
            setSetA();
        displayGameA(gameA);
    }
    public void setGameB(){
        if (scoreTeamB == 11 && gameB < 3 ){
            gameB++;
            scoreTeamB = 0;
        }else
            setSetB();
        displayGameB(gameB);
    }
    //Control Set point
    public void setSetA(){
        if(gameA == 3) {
            setA++;
            gameA = 0;
        }
        displaySetA(setA);
    }
    public void setSetB(){
        if(gameB == 3) {
            setB++;
            gameB = 0;
        }
        displaySetB(setB);
    }
    //Winner is..
    public void winner(){
        if(setA == 3){
            cardWin.setVisibility(View.VISIBLE);
            cardWin.setPadding(cardWin.getPaddingLeft(),16,cardWin.getPaddingRight(),cardWin.getContentPaddingBottom());
            cardPoint.setPadding(cardPoint.getPaddingLeft(),8,cardPoint.getPaddingRight(),cardPoint.getContentPaddingBottom());
            winnerText.setText("team a win");
            winnerText.setAllCaps(true);
        }else if(setB == 3) {
            cardWin.setVisibility(View.VISIBLE);
            cardWin.setPadding(cardWin.getPaddingLeft(), 16, cardWin.getPaddingRight(), cardWin.getContentPaddingBottom());
            cardPoint.setPadding(cardPoint.getPaddingLeft(), 8, cardPoint.getPaddingRight(), cardPoint.getContentPaddingBottom());
            winnerText.setText("team b win");
            winnerText.setAllCaps(true);
        }
    }
    //Displays the given score.
    public void displayForTeamA(int score) {
        scoreViewA.setText(String.valueOf(score));
    }
    public void displayForTeamB(int score) {
        scoreViewB.setText(String.valueOf(score));
    }
    // Display the Game score.
    public void displayGameA(int score){
        gameViewA.setText(String.valueOf(score));
    }
    public void displayGameB(int score){
        gameViewB.setText(String.valueOf(score));
    }
    // Display the Set score.
    public void displaySetA(int score){
        setViewA.setText(String.valueOf(score));
        winner();
    }
    public void displaySetB(int score){
        setViewB.setText(String.valueOf(score));
        winner();
    }
    // Reset the score for Team A and Team B.
    public void resetScore(View v) {
        scoreTeamA = scoreTeamB = gameA = gameB = setA = setB = 0;
        cardWin.setVisibility(View.GONE);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
