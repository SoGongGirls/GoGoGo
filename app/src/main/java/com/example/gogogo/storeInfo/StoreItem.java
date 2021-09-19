package com.example.gogogo.storeInfo;

import android.graphics.drawable.Drawable;

public class StoreItem {
    private Drawable storeImage;
    private String storeName;
    private String storeGrade;
    private String storeDistance;

    public void setStoreImage(Drawable image) {
        storeImage = image;
    }
    public void setStoreName(String name) {
        storeName = name;
    }
    public void setStoreGrade(String grade) {
        storeGrade = grade;
    }
    public void setStoreDistance(String distance) {
        storeDistance = distance;
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
