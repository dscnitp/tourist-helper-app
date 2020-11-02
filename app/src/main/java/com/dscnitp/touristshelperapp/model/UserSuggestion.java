package com.dscnitp.touristshelperapp.model;

public class UserSuggestion {
    private String city;
    private String state;
    private String price;

    public UserSuggestion(String city, String state, String price) {
        this.city = city;
        this.state = state;
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
