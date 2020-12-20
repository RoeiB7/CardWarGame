package com.example.cardwargame.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import com.example.cardwargame.utilities.GameManager;
import com.example.cardwargame.R;

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