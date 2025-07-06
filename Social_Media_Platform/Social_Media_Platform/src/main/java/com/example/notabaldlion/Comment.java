package com.example.notabaldlion;

import java.io.Serializable;

public class Comment implements Serializable {
    private String content;
    private String author;

    public Comment(String content, String author) {
        this.content = content;
        this.author = author;
    }
    public String getauthor() {
        return author;
    }

    public String toString(){
        return author + ": " + content;

    }
}
