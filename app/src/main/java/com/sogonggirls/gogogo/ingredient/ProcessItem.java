package com.sogonggirls.gogogo.ingredient;

public class ProcessItem {
    String explain;
    String order;
    String img_url;
    String tip;

    public ProcessItem( String explain, String order, String img_url, String tip) {
        this.explain = explain;
        this.order = order;
        this.img_url = img_url;
        this.tip = tip;
    }

    public String getExplain() {
        return explain;
    }

    public String getOrder() {
        return String.valueOf(order);
    }

    public String getImg_url() {
        return img_url;
    }

    public String getTip() {
        return tip;
    }

}