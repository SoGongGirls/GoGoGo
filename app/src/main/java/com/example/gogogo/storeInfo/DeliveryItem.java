package com.example.gogogo.storeInfo;

import android.util.Log;

import com.example.gogogo.MainActivity;

public class DeliveryItem {

    private int deliveryId;
    private String deliveryLogo;
    private String deliveryName;
    private String deliveryGrade;
    private double deliveryLatitude;
    private double deliveryLongitude;


    public DeliveryItem(String name, String degree, int id, String logo, double latitude, double longitude){
        this.deliveryName = name;
        this.deliveryGrade = degree;
        this.deliveryId = id;
        this.deliveryLogo = logo;
        this.deliveryLatitude = latitude;
        this.deliveryLongitude = longitude;
    }

    public String getDeliveryLogo() {
        return this.deliveryLogo;
    }
    public String getDeliveryName() {
        return this.deliveryName;
    }
    public String getDeliveryGrade() {
        return this.deliveryGrade;
    }
    public int getDeliveryId() { return this.deliveryId; }

    public double getDeliveryTime() {
        return DistanceByDegree(SurveyResult.myLatitude, SurveyResult.myLongitude, deliveryLatitude, deliveryLongitude);
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

