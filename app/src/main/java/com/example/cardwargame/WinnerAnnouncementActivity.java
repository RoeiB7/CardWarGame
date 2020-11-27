package com.example.cardwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerAnnouncementActivity extends AppCompatActivity {


    ImageView winnerImage;
    ImageView leftPic;
    ImageView rightPic;
    TextView title;
    TextView score;


    /**
     * Clean and short onCreate for activating all functions
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_announcement);

        initViews();
        enterFullScreen();
        getDataFromGameActivity();

    }

    /**
     * Initial all components in the View
     */
    private void initViews() {
        winnerImage = findViewById(R.id.winner_IMG_winnerPlayer);
        title = findViewById(R.id.winner_LBL_winnerMessage);
        score = findViewById(R.id.winner_LBL_score);
        leftPic = findViewById(R.id.winner_IMG_leftFirework);
        rightPic = findViewById(R.id.winner_IMG_rightFirework);
    }

    /**
     * Enter full screen mode by hiding navigation bar and status bar
     */
    private void enterFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     * Getting winner identity and score from first activity (Game activity) and displaying
     */
    private void getDataFromGameActivity() {
        Intent intent = getIntent();
        String winnerName = intent.getStringExtra(GameActivity.WINNER);
        int winnerScore = intent.getIntExtra(GameActivity.SCORE, -1);
        if (winnerName.equalsIgnoreCase("PlayerLeft")) {
            winnerImage.setImageResource(R.drawable.man);
            score.setText("Your Score: " + winnerScore);
        } else if (winnerName.equalsIgnoreCase("PlayerRight")) {
            winnerImage.setImageResource(R.drawable.woman);
            score.setText("Your Score: " + winnerScore);
        } else {
            title.setText("It's A Tie!");
            score.setText("");
            winnerImage.setImageResource(R.drawable.monster);
            leftPic.setImageResource(R.drawable.facepalm_man);
            rightPic.setImageResource(R.drawable.facepalm_woman);

        }
    }
}