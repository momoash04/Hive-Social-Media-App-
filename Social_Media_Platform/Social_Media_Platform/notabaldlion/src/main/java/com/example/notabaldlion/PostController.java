package com.example.notabaldlion;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

public class PostController {
    User controller;

    @FXML
    private Button comm_nm;

    @FXML
    private Label Caption;
    @FXML
    private Label likenum;

    @FXML
    private Label Caption1;

    @FXML
    private ImageView Like;

    @FXML
    private ImageView Post_image;


    @FXML
    private ImageView liked;

    @FXML
    private ImageView user_Image;

    @FXML
    private Label username;
    Post postdata = new Post();
    private Image imageuser;

    public void setdata(Post post) {
        controller = singleton.getuser();
        this.postdata = post;
        username.setText(post.getCreator());
        likenum.setText(String.valueOf(post.getNumOfLikes()));
        Caption.setText(post.getContent());
        System.out.print(postdata.getPostImage());

            Post_image.setImage(postdata.getPostImage());


        for (int i = 0; i < (singleton.getusers()).size(); i++) {
            if (singleton.getusers().get(i).getUsername().equals(post.getCreator()))
            {
                imageuser = controller.getProfileImage();
                break;
            }
        }
        user_Image.setImage(imageuser);

    }
    @FXML
    void Like(MouseEvent event) {


        Like.setVisible(false);
        liked.setVisible(true);
        controller.likepost(postdata);



    }

    @FXML
    void unlike(MouseEvent event) {

        liked.setVisible(false);
        Like.setVisible(true);
        controller.unlikepost(postdata);

    }
    @FXML
    void commentbuttonAction(ActionEvent event) {
        main.changeScenewithcontroller3("Comments_Page.fxml",postdata);

    }


}



