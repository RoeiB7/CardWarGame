package com.example.cardwargame;

import java.util.ArrayList;

public class TopTen {
    private ArrayList<Player> players = new ArrayList<>();

    public TopTen() {

    }

    public TopTen(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
