package com.example.gogogo.storeInfo;


public class StoreItem {
    private int storeId;
    private String storeLogo;
    private String storeName;
    private String storeGrade;
    private String storeDistance;

    public StoreItem(String name, String degree, int id, String logo){
        this.storeName = name;
        this.storeGrade = degree;
        this.storeId = id;
        this.storeLogo = logo;
    }

    public String getStoreLogo() {
        return this.storeLogo;
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