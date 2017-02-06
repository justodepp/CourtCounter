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
    TextView scoreViewA, scoreViewB, winnerText;
    private int scoreTeamA, scoreTeamB;
    private int gameA, gameB, setA, setB;
    private boolean advantage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.activity_main);
        // initialize member view so we can manipulate it later
        init();
    }

    // This callback is called only when there is a saved instance previously saved using
    // onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }
    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    //initialize
    public void init() {
        cardWin = (CardView) findViewById(R.id.card_win);
        cardWin.setVisibility(View.INVISIBLE);
        cardPoint = (CardView) findViewById(R.id.card_point);

        scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);
        winnerText = (TextView) findViewById(R.id.winner);

        advantage = false;

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
        if(scoreTeamA < 10 && !advantage)
        scoreTeamA ++;
        displayForTeamA(scoreTeamA);
    }
    //Increase the score for Team B.
    public void addForTeamB(View v) {
        scoreTeamB ++;
        displayForTeamB(scoreTeamB);
    }
    //Displays the given score for Team A.
    public void displayForTeamA(int score) {
        scoreViewA.setText(String.valueOf(score));
        setGameA();
    }
    // Displays the given score for Team B.
    public void displayForTeamB(int score) {
        scoreViewB.setText(String.valueOf(score));
        setGameB();
    }

    public void setGameA(){
        if (scoreTeamA == 45 ){
            gameA++;
        }
        setSetA();
    }
    public void setGameB(){
        if (scoreTeamB == 45 ){
            gameB++;
        }
        setSetB();
    }

    public void setSetA(){
        if(gameA > gameB && gameA == 11 && gameB >=9)
            setA ++;
        else if(gameA == 10 && gameB == 10) {
            advantage = true;
        }
        winner();
    }
    public void setSetB(){
        if(gameB == 11)
            setB ++;
        winner();
    }

    //winner card
    public void winner(){
        if(setA > setB && setA > 3){
            cardWin.setVisibility(View.VISIBLE);
            cardWin.setPadding(cardWin.getPaddingLeft(),16,cardWin.getPaddingRight(),cardWin.getContentPaddingBottom());
            cardPoint.setPadding(cardPoint.getPaddingLeft(),8,cardPoint.getPaddingRight(),cardPoint.getContentPaddingBottom());
            winnerText.setText("team a win");
            winnerText.setAllCaps(true);
        }else if(setB > setA && setB > 3){
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
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
