package com.example.notabaldlion;

import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;


import javafx.scene.image.Image;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private byte[] imageData=null;
    private  transient Image pfp=null;
    private String password;
    public String username;
    private ArrayList<Post> posts;
    private ArrayList<String> friends;
    public String bio=null;

    User(String name, String password, String email, String username) throws IOException {
        this.name = name;
        this.password = password;
        this.email = email;
        this.username = username;
        this.posts = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setProfileImage(Image profileimage) {
        this.pfp = profileimage;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this.pfp.getUrl());
            this.imageData = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Image getProfileImage() {
        if (imageData != null) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(this.imageData);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                String imageUrl = (String) ois.readObject();
                this.pfp = new Image(imageUrl);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return this.pfp;
        }
        else return null;
    }
    public static void storeAllUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        users = singleton.getusers();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            oos.writeObject(users);
        }
    }

    // Load all users from a file
    public static void loadAllUsers() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            singleton.setusers((ArrayList<User>) ois.readObject());
        }
    }


    public static User signup(String name, String email, String password, String username) throws IOException, ClassNotFoundException {
        ArrayList<User> users = singleton.getusers();
        boolean istaken = false;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("This username is taken, please choose another username");
                istaken = true;
                break;
            }
        }

        if (!istaken) {
            User user = new User(name, password, email, username);
            users.add(user);
            System.out.println("user registered");
            return user;
        }
        else{
            return null;
        }

    }

    public static User login(String username, String password) {
        ArrayList<User> users = new ArrayList<>();
        users = singleton.getusers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void createPost(String caption, Image image) {
        if(image !=null) {
            String creator = this.getUsername();
            Post newpost = new Post(creator, caption, image);
            singleton.addpost(newpost);
            posts.add(newpost);
            System.out.println(this.username + " post created");
            System.out.print(newpost.getPostImage());
        }
        else{
            System.out.println("image is null");
        }
    }

    public void setpassword(String password) {
        this.password = password;
    }
    public void createComment(Post post, String comment){
        post.addComment(comment, username);
    }

    public ArrayList<Comment> getComments(Post post){
        return post.viewComments();
    }

    public void likepost(Post post){
        username = this.username;
        post.addLike(username);
    }

    public void unlikepost(Post post){
        username = this.username;
        post.unlike(username);
    }

    public ArrayList<Like> getLikes(Post post){
        return post.getLikes();
    }

    public ArrayList<Post> getPosts(){

        return posts;
    }

    public void friend(String username){
        System.out.print("user added");

        friends.add(username);

    }
    public void unfriend(String username){
        friends.remove(username);
    }


    public String getUsername() {
        return username;
    }
    private String getPassword() {return password;}


    public void setbio(String bio) {
        this.bio = bio;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<Post> getfeed() {
        ArrayList<Post> posts = new ArrayList<>();
        posts = singleton.getposts();
        ArrayList<Post> showedposts = new ArrayList<>();
        for(Post post : posts){
            for(String friend : friends){
                if(post.getCreator().equals(friend)){
                    showedposts.add(post);
                }
            }
        }
        return showedposts;



    }


}
