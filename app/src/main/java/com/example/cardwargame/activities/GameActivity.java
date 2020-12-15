package com.example.cardwargame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.cardwargame.utilities.GameManager;
import com.example.cardwargame.R;


public class GameActivity extends AppCompatActivity {


    boolean isPressed = true;

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
            if (isPressed) {
                playButton.setImageResource(R.drawable.ic_yellow_clock);
                manager.playSound(R.raw.airhorn, GameActivity.this);
                manager.startDelay(GameActivity.this);
                isPressed = false;
            }
        });

    }

//
}