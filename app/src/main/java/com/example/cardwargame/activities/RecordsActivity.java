package com.example.cardwargame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.cardwargame.fragments.MapsFragment;
import com.example.cardwargame.utilities.GameManager;
import com.example.cardwargame.R;
import com.example.cardwargame.fragments.Fragment_List;

public class RecordsActivity extends AppCompatActivity {
    private FrameLayout list;
    private FrameLayout map;
    private Fragment_List fragment_list;
    private MapsFragment mapsFragment;

    public RecordsActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        GameManager manager = new GameManager(this);
        initRecordsViews();
        manager.enterFullScreen(this);
        Intent intent = this.getIntent();
        String winnerName = intent.getStringExtra("winnerName");
        int winnerScore = intent.getIntExtra("winnerScore", 0);

        Bundle bundle = new Bundle();
        bundle.putString("winnerName", winnerName);
        bundle.putInt("winnerScore", winnerScore);
        fragment_list = new Fragment_List();
        fragment_list.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.records_LAY_list, fragment_list).commit();


        mapsFragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.records_LAY_map, mapsFragment).commit();


    }

    private void initRecordsViews() {
        list = findViewById(R.id.records_LAY_list);
        map = findViewById(R.id.records_LAY_map);
    }
}