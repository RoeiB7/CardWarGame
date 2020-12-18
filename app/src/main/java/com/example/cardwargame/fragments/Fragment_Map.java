package com.example.cardwargame.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.cardwargame.R;

public class Fragment_Map extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_map,container,false);
        initMapFragmentViews(view);


        return view;
    }

    private void initMapFragmentViews(View view) {

    }


}
