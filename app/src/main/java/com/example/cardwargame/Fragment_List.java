package com.example.cardwargame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Fragment_List extends Fragment {


    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayList<Player> topTen = new ArrayList<>();
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

        if (topTen.size() > 0) {
            String listJson = sharedPreferences.getString("ttJson", "NA");
            topTen = gson.fromJson(listJson, ArrayList.class);
        }
        Player player = new Player(this.getArguments().getInt("winnerScore"), this.getArguments().getString("winnerName"));

        //todo: add shared sharedPreferences for saving list

        //todo: check if score is higher then the lowest score
        if (player.getScore() != -1 && !player.getName().equals(null)) {
            topTen.add(player);
        }

        //todo: sort list

        String ttJson = gson.toJson(topTen);
        editor.putString("ttJson", ttJson);
        editor.apply();

        arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, topTen);

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
