package com.example.cardwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerAnnouncementActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_announcement);

        GameManager manager = new GameManager(this);
        manager.initWinnerViews(this);
        manager.enterFullScreen(this);
        manager.getDataFromGameActivity(this);
        EditText winnerName = manager.getWinnerName();
        if (manager.getScoreFromGameActivity(WinnerAnnouncementActivity.this) == 0) {
            winnerName.setAlpha(0);
        } else {
            winnerName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        Intent intent = new Intent(WinnerAnnouncementActivity.this, RecordsActivity.class);
                        intent.putExtra("winnerName", winnerName.getText().toString());
                        intent.putExtra("winnerScore", manager.getScoreFromGameActivity(WinnerAnnouncementActivity.this));
                        startActivity(intent);
                        finish();
                    }
                    return false;
                }
            });
        }
    }
}