package com.example.gogogo.storeInfo;

import android.graphics.drawable.Drawable;

public class StoreItem {
    private int storeId;
    private Drawable storeImage;
    private String storeName;
    private String storeGrade;
    private String storeDistance;

    public StoreItem(String name, String degree, int id){
        this.storeName = name;
        this.storeGrade = degree;
        this.storeId = id;
    }

    public Drawable getStoreImage() {
        return this.storeImage;
    }
    public String getStoreName() {
        return this.storeName;
    }
    public String getStoreGrade() {
        return this.storeGrade;
    }
    public String getStoreDistance() {
        return this.storeDistance;
    }
}