package com.example.cardwargame.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cardwargame.utilities.GameManager;
import com.example.cardwargame.R;
import com.example.cardwargame.utilities.PermissionAndLocation;


public class GameActivity extends AppCompatActivity {


    private boolean isPressed = true;
    private boolean isTick = false;
    private PermissionAndLocation pl;
    private GameManager manager;
    private ImageView playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pl = new PermissionAndLocation();
        pl.requestPermission(this);

        manager = new GameManager(this);
        manager.initGameViews(this);
        manager.enterFullScreen(this);
        manager.initCardsArrayList(this);
        manager.shuffleAndSplit();
        playButton = manager.getPlayButton();

        playButton.setOnClickListener(v -> {
            if (isPressed) {
                playButton.setImageResource(R.drawable.ic_yellow_clock);
                manager.playSound(R.raw.airhorn, GameActivity.this);
                manager.startDelay(GameActivity.this);
                isPressed = false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if ((grantResults.length > 0) && grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED && requestCode == 100) {
            pl.getCurrentLocation(this);
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (!isTick) {
            CountDownTimer countDownTimer = manager.getCdt();
            if (countDownTimer != null) {
                countDownTimer.cancel();
                isTick = true;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTick) {
            CountDownTimer countDownTimer = manager.getCdt();
            countDownTimer.start();
            isTick = false;
        }
    }


}