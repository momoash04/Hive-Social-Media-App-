package com.example.notabaldlion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class feedPageController {

    User controller ;
    private main Main;
    @FXML
    private Button exitButton;

    @FXML
    private VBox feedpane;

    @FXML
    public Label nameLabel;


    @FXML
    private Button postButton;

    @FXML
    private Button profileButton;

    @FXML
    private ImageView profileImage;

    @FXML
    void exitButtonAction(ActionEvent event) throws IOException {
        User.storeAllUsers();
        Post.storePosts();

    }

    public void initialize() throws IOException {
        controller = singleton.getuser();
//        String username = controller.getUsername();
//        this.nameLabel.setText(username);
//        profileImage.setImage(controller.getProfileImage());

        /////////////////////////////////////
        ArrayList<Post> yourposts = new ArrayList<>();
        yourposts = controller.getfeed();

        for(Post post : yourposts) {
            System.out.print("showing post");
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("post.fxml"));
            Parent root = fxmlloader.load();
            PostController postController = fxmlloader.getController();
            postController.setdata(post);
            AnchorPane pane = new AnchorPane(root);
            feedpane.getChildren().add(pane);
        }

    }

    @FXML
    void postbuttonAction() throws IOException {

       main.changeScene("post-page.fxml");

    }

    @FXML
    void profileButtonAction(ActionEvent event) throws IOException {

        main.changeScenewithcontroller("profile scene.fxml");

    }

}
