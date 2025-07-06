package com.example.notabaldlion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AfterRegistrationController {

    User controller = singleton.getuser();

    @FXML
    private Button addFriendButton;

    @FXML
    private Button addbio;

    @FXML
    private Button addpp;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView ppimage;

    @FXML
    private TextArea biotextfield;

    @FXML
    private TextField friendusernametextfield;

    private Image profileimage;

    @FXML
    void addFriendButtonAction(ActionEvent event) {

        String friendusername = friendusernametextfield.getText();

        controller.friend(friendusername);



    }

    @FXML
    void addbioAction(ActionEvent event) {

        String bio = biotextfield.getText();
        if (bio == null) {
            biotextfield.setStyle("-fx-border-color: red");
        }
        else {
            controller.setbio(bio);
        }


    }

    @FXML
    void addppAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

// Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.jpeg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            profileimage = new Image(file.toURI().toString());
            ppimage.setImage(profileimage);
        }
        controller.setProfileImage(profileimage);
        System.out.println("image set");



    }

    @FXML
    void nextButtonAction(ActionEvent event) {
        main.changeScenewithcontroller2("feed-page.fxml");

        

    }

}

