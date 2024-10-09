package com.example.bai5_bson_22521247.Classes_Folder;

public class Dish {
    private String name;
    private int thumbnail;
    private boolean promotion;

    public Dish(String name, int thumbnail, boolean promotion) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public boolean isPromotion() {
        return promotion;
    }
}

