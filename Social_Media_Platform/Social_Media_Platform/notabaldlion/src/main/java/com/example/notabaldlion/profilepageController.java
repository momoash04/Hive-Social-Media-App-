package com.example.notabaldlion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class profilepageController {
    User controller ;

    @FXML
    private Button backbutton;

    @FXML
    private TextArea biolabel;

    @FXML
    private Button editbutton;

    @FXML
    private Button friendsbutton;

    @FXML
    private ImageView profileimage;

    @FXML
    private Label usernamelabel;

    @FXML
    private VBox vboxpostcontainer;


    @FXML
    void backbuttonAction(ActionEvent event) {
        main.changeScene("feed-page.fxml");
    }

    @FXML
    void editbuttonaction(ActionEvent event) {
        main.changeScene("editing-profile.fxml");

    }

    @FXML
    void friendsbuttonaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("friends-page.fxml"));
            Parent root = loader.load();
            FriendsPageController friendsPageController = loader.getController();
            friendsPageController.initialize();


            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Friends page");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    public void initialize() throws IOException {

        controller = singleton.getuser();
        String username = new String();
        usernamelabel.setText(controller.getUsername());
        String bio = controller.getBio();
        this.biolabel.setText(bio);
        profileimage.setImage(controller.getProfileImage());

        ////////////////////////////

        ArrayList<Post> yourposts = new ArrayList<>();
        yourposts = controller.getPosts();

        for(Post post : yourposts) {


            if(post.getPostImage() != null) {
                System.out.print("showing post");
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("post.fxml"));
                Parent root = fxmlloader.load();
                PostController postController = fxmlloader.getController();
                postController.setdata(post);
                AnchorPane pane = new AnchorPane(root);
                vboxpostcontainer.getChildren().add(pane);
            }

        }

    }

}

