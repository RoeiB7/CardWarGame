package com.example.cardwargame.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.cardwargame.objects.Player;
import com.example.cardwargame.R;
import com.example.cardwargame.objects.TopTen;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Fragment_List extends Fragment {


    private ListView listView;
    private final Gson gson = new Gson();


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
        TopTen tt2 = new TopTen();
        if (!extractJson.equals("NA")) {
            tt2 = gson.fromJson(extractJson, TopTen.class);

        }

        Player player = new Player(this.getArguments().getInt("winnerScore"), this.getArguments().getString("winnerName"));

        //todo: change direction of list top down

        if (player.getScore() != 0) {
            tt2.getPlayers().add(player);
            tt2.getPlayers().sort((a, b) -> Integer.compare(a.getScore(), b.getScore()));
        }


        //todo: fix remove lowest score
        if (tt2.getPlayers().size() > 10) {
            tt2.getPlayers().remove(10);
        }
        String ttJson = gson.toJson(tt2);
        editor.putString("ttJson", ttJson);

        editor.apply();

        ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, tt2.getPlayers());

        listView.setAdapter(arrayAdapter);

        return view;
    }

    /**
     * Initial all components in List fragment
     */
    protected void initListFragmentViews(View view) {
        listView = view.findViewById(R.id.fragment_LAY_list);

    }

}
