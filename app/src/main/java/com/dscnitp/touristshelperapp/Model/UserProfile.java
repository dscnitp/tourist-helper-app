package com.dscnitp.touristshelperapp.Model;

public class UserProfile {
    public String username,email,password,contact,description,gender,location,liked_cities,visited_cities;

    public UserProfile(String username, String email, String password, String contact, String description, String gender, String location, String liked_cities, String visited_cities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.description = description;
        this.gender = gender;
        this.location = location;
        this.liked_cities = liked_cities;
        this.visited_cities = visited_cities;
    }
}
