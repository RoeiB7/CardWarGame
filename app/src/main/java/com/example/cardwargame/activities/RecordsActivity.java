package com.example.cardwargame.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.cardwargame.Interfaces.CallBack_Right;
import com.example.cardwargame.fragments.MapsFragment;
import com.example.cardwargame.utilities.GameManager;
import com.example.cardwargame.R;
import com.example.cardwargame.fragments.Fragment_List;
import com.example.cardwargame.utilities.PermissionAndLocation;


public class RecordsActivity extends AppCompatActivity {

    private Fragment_List fragment_list;

    private MapsFragment mapsFragment;


    public RecordsActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        GameManager manager = new GameManager(this);
        manager.initRecordsViews(this);
        manager.enterFullScreen(this);

        Intent intent = this.getIntent();
        String winnerName = intent.getStringExtra("winnerName");
        int winnerScore = intent.getIntExtra("winnerScore", 0);


        Bundle bundle = new Bundle();
        bundle.putString("winnerName", winnerName);
        bundle.putInt("winnerScore", winnerScore);
        bundle.putDouble("userLat", PermissionAndLocation.userLocation.getLatitude());//fix crush when enter leaderboard from main screen
        bundle.putDouble("userLon", PermissionAndLocation.userLocation.getLongitude());//fix crush when enter leaderboard from main screen
        fragment_list = new Fragment_List();
        fragment_list.setArguments(bundle);
        fragment_list.setCallBack_right(callBack_right);
        getSupportFragmentManager().beginTransaction().add(R.id.records_LAY_list, fragment_list).commit();

        mapsFragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.records_LAY_map, mapsFragment).commit();

    }

    private final CallBack_Right callBack_right = new CallBack_Right() {
        @Override
        public void getLocationRight(double lat, double lon) {
            mapsFragment.showUserLocation(lat, lon);
        }
    };


}