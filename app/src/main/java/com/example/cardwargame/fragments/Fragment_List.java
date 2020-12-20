package com.example.cardwargame.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.cardwargame.Interfaces.CallBack_Right;
import com.example.cardwargame.objects.Player;
import com.example.cardwargame.R;
import com.example.cardwargame.objects.TopTen;
import com.google.gson.Gson;

import java.util.Comparator;

public class Fragment_List extends Fragment {


    private ListView listView;
    private final Gson gson = new Gson();
    private CallBack_Right callBack_right;
    private TopTen tt2;
    private Player player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initListFragmentViews(view);
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("com.example.cardwargame", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String extractJson = sharedPreferences.getString("ttJson", "NA");
        tt2 = new TopTen();
        if (!extractJson.equals("NA")) {
            tt2 = gson.fromJson(extractJson, TopTen.class);

        }

        player = new Player(this.getArguments().getInt("winnerScore"),
                this.getArguments().getString("winnerName"),
                this.getArguments().getDouble("userLat"),
                this.getArguments().getDouble("userLon"));

        if (player.getScore() != 0) {
            tt2.getPlayers().add(player);
            tt2.getPlayers().sort(DESCENDING_COMPARATOR);
        }

        if (tt2.getPlayers().size() > 10) {
            tt2.getPlayers().remove(10);
        }
        String ttJson = gson.toJson(tt2);
        editor.putString("ttJson", ttJson);

        editor.apply();

        ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, tt2.getPlayers());

        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callBack_right.getLocationRight(tt2.getPlayers().get(position).getLatitude(), tt2.getPlayers().get(position).getLongitude());
            }
        });

        return view;
    }

    /**
     * Initial all components in List fragment
     */
    private void initListFragmentViews(View view) {
        listView = view.findViewById(R.id.fragment_LAY_list);

    }

    public static final Comparator<Player> DESCENDING_COMPARATOR =
            Comparator.comparingInt(Player::getScore).reversed();


    public void setCallBack_right(CallBack_Right _callBack_right) {
        this.callBack_right = _callBack_right;
    }

}
