package com.example.cardwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        GameManager manager = new GameManager(this);
        manager.initEntryViews(this);
        manager.enterFullScreen(this);
        Button startGameButton = manager.getStartGameButton();
        startGameButton.setOnClickListener(v -> {
            manager.startGame(EntryActivity.this);
        });

        Button leaderBoardButton = manager.getLeaderBoardButton();
        leaderBoardButton.setOnClickListener(v -> {
            manager.leaderBoard(EntryActivity.this);
        });
    }
}