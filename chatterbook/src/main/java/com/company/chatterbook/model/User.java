package com.company.chatterbook.model;

import java.util.List;

public class User {
    private String name;
    private List<ChatterPost> chatterPosts;

    public User(String name){this.name = name;}

    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }

    public List<ChatterPost> getChatterPosts(){
        return chatterPosts;
    }

    public String getName(){
        return name;
    }
}
