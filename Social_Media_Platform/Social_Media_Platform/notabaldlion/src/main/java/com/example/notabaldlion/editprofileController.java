package com.example.notabaldlion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class editprofileController {
    User controller = singleton.getuser();

    @FXML
    private TextField changebio;

    @FXML
    private Button backbutton;

    @FXML
    private Button changephoto;

    @FXML
    private Button changesbiobutton;

    @FXML
    private Button changespassword;

    @FXML
    private ImageView newpfp;

    @FXML
    private TextField passwordtextfield;

    private Image profileimage;

    @FXML
    void backbuttonAction(ActionEvent event) {

        main.changeScenewithcontroller("profile scene.fxml");

    }

    @FXML
    void changingbiobuttonAction(ActionEvent event) {
        String bio = changebio.getText();
        controller.setbio(bio);
        System.out.println("bio set");

    }

    @FXML
    void changingpasswordAction(ActionEvent event) {
        String password = passwordtextfield.getText();
        controller.setpassword(password);
        System.out.print("password set");

    }

    @FXML
    void changingphoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

// Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.jpeg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            profileimage = new Image(file.toURI().toString());
            newpfp.setImage(profileimage);
        }
        controller.setProfileImage(profileimage);
        System.out.println("image set");


    }

}

