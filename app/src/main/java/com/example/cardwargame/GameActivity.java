package com.example.cardwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;


public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameManager manager = new GameManager(this);
        manager.initGameViews(this);
        manager.enterFullScreen(this);
        manager.initCardsArrayList(this);
        manager.shuffleAndSplit();
        ImageView playButton = manager.getPlayButton();
        playButton.setOnClickListener(v -> {
            manager.playSound(R.raw.airhorn, GameActivity.this);
            playButton.setImageResource(R.drawable.ic_yellow_clock);
            manager.startDelay(GameActivity.this);
        });
    }

//
}