package com.example.cardwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {


    public RecordsActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        GameManager manager = new GameManager(this);
        manager.initRecordsViews(this);
        manager.enterFullScreen(this);
        Fragment_List fragment_list = new Fragment_List();
        Intent intent = this.getIntent();
        String winnerName = intent.getStringExtra("winnerName");
        int winnerScore = intent.getIntExtra("winnerScore", -1);

        Bundle bundle = new Bundle();
        bundle.putString("winnerName", winnerName);
        bundle.putInt("winnerScore", winnerScore);
        fragment_list.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.records_LAY_list, fragment_list).commit();

        //Fragment_Map fragment_map = new Fragment_Map();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_LAY_map, fragment_map).commit();

    }
}