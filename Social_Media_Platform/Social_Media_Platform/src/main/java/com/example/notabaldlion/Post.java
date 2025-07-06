package com.example.notabaldlion;


import java.awt.*;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;

public class Post implements Serializable {
    private String creator;
    private String content;
    private ArrayList<Like> likes;
    private ArrayList<Comment> comments;
    private transient Image postImage = null;
    private byte[] postData = null;
    private int likesCount;



    public Post(){

    }
    public Post(String creator, String content, Image postImage) {
        this.creator = creator;
        this.content = content;
        this.likes = new ArrayList<>();
        this.likesCount = 0;
        this.comments = new ArrayList<>();
        this.postImage = postImage;
        setPostImage(postImage);
    }






    //comments
    public void addComment(String comment, String username) {
        comments.add(new Comment(comment,username));
    }
    public ArrayList<Comment> viewComments(){
        return comments;

    }



    //Likes
    public void addLike(String username) {
        boolean likedbefore = false;
        for (Like like1 : likes) {
            if (like1.getUsername().equals(username)) {
                System.out.println("You can't like the same post twice");
                likedbefore = true;
            }
        }
        if (!likedbefore) {
            likes.add(new Like(username));
            likesCount = likes.size();
        }
        else{
            System.out.println("You can't like the same post twice");
        }
    }

    public void unlike(String username) {
        boolean likedbefore = false;
        for (Like like1 : likes) {
            if (like1.getUsername().equals(username)) {
                System.out.println("You can't unlike the same post twice");
                likedbefore = true;
            }
        }
        if (likedbefore) {
            likes.remove(new Like(username));
            likesCount = likes.size();
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getContent() {
        return content;
    }
    public int getNumOfLikes(){
        return likesCount;
    }
    public ArrayList<Like> getLikes() {
        return likes;
    }


    public int getLikesCount() {
        return likesCount;
    }


    public String getCreator(){
        return creator;
    }

    public static void storePosts() throws IOException {
        ArrayList<Post> posts = singleton.getposts();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("posts.ser"))) {
            oos.writeObject(posts);
            System.out.print("Posts stored successfully");
        }
    }

    // Method to reload the ArrayList of posts from a file
    public static void loadPosts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("posts.ser"))) {
            singleton.setposts((ArrayList<Post>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public void setPostImage(Image postImage) {
        this.postImage = postImage;
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(this.postImage.getUrl());
            this.postData = baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Method to convert byte array to Image

    public Image getPostImage() {
        if (postData != null) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(this.postData);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                String imageUrl = (String) ois.readObject();
                this.postImage = new Image(imageUrl);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return this.postImage;
        } else return null;
    }
}
