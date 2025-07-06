package com.example.notabaldlion;

import java.io.Serializable;

class Like implements Serializable {
    public String username;

    Like(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
