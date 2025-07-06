package com.example.notabaldlion;

import java.util.ArrayList;

public class singleton {
    private static User userdata;
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Post> posts = new ArrayList<>();

    private singleton(User userdata) {
        this.userdata = userdata;
    }

    public static User getuser() {

        return userdata;
    }
    public static void setuser(User userdata) {
        singleton.userdata = userdata;
    }
    public static ArrayList<User> getusers() {
        return users;
    }
    public static void setusers(ArrayList<User> users) {
        singleton.users = users;
    }
    public static ArrayList<Post> getposts() {
        return posts;
    }
    public static void setposts(ArrayList<Post> posts) {
        singleton.posts = posts;
    }
    public static void addpost(Post post) {
        posts.add(post);
    }
}
