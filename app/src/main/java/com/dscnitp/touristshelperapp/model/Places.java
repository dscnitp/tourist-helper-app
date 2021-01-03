package com.dscnitp.touristshelperapp.model;

public class Places {
    private String placeTitle;
    private String placeUrl;

    public Places(String placeTitle, String placeUrl) {
        this.placeTitle = placeTitle;
        this.placeUrl = placeUrl;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }
}
