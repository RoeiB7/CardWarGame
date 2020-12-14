package com.example.cardwargame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class Fragment_List extends Fragment {


    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private Gson gson = new Gson();


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
        Log.d("String:", extractJson + "");
        TopTen tt2 = new TopTen();
        if (!extractJson.equals("NA")) {
            tt2 = gson.fromJson(extractJson, TopTen.class);

        }
        Player player = new Player(this.getArguments().getInt("winnerScore"), this.getArguments().getString("winnerName"));



        //todo: check if score is higher then the lowest score
        tt2.getPlayers().add(player);
        tt2.getPlayers().sort((a, b) -> Integer.compare(a.getScore(), b.getScore()));
        tt2.getPlayers().sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));

        //todo: sort list

        String ttJson = gson.toJson(tt2);
        editor.putString("ttJson", ttJson);

        editor.commit();

        arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, tt2.getPlayers());

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
