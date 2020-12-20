package com.example.cardwargame.objects;


public class Player {

    private int score;
    private String name;
    private double latitude;
    private double longitude;


    public Player() {


    }

    public Player(int score, String name, double latitude, double longitude) {
        this.score = score;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName() + " " + getScore();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
