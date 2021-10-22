package com.example.gogogo.storeInfo;


import android.util.Log;

import com.example.gogogo.MainActivity;
import com.example.gogogo.my_location;

public class StoreItem {
    private int storeId;
    private String storeLogo;
    private String storeName;
    private String storeGrade;
    private double storeLatitude;
    private double storeLongitude;

    public StoreItem(String name, String degree, int id, String logo, double latitude, double longitude){
        this.storeName = name;
        this.storeGrade = degree;
        this.storeId = id;
        this.storeLogo = logo;
        this.storeLatitude = latitude;
        this.storeLongitude = longitude;
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
    public int getStoreId() { return this.storeId; }

    public double getStoreDistance() {
        // return DistanceByDegree(my_location.myLatitude, my_location.myLongitude, storeLatitude, storeLongitude);
        return DistanceByDegree(SurveyResult.myLatitude, SurveyResult.myLongitude, storeLatitude, storeLongitude);
    }


    // 원하는 위치에서 위치까지 거리를 계산해주는 함수
    public double DistanceByDegree(double lat1, double lng1, double lat2, double lng2) {
        double theta, dist;

        theta = lng1 - lng2;
        dist = Math.sin(DegreeToRadian(lat1)) * Math.sin(DegreeToRadian(lat2)) + Math.cos(DegreeToRadian(lat1))
                * Math.cos(DegreeToRadian(lat2)) * Math.cos(DegreeToRadian(theta));

        dist = Math.acos(dist);
        dist = RadianToDegree(dist);

        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;    // 단위 mile 에서 km 변환

        return dist;
    }

    //degree->radian 변환
    public double DegreeToRadian(double degree){
        return degree * Math.PI / 180.0;
    }

    //randian -> degree 변환
    public double RadianToDegree(double radian){
        return radian * 180d / Math.PI;
    }
}