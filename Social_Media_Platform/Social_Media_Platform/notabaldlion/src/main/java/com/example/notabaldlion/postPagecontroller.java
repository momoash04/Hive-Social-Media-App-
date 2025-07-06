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


public class postPagecontroller {
User controller = singleton.getuser();

    @FXML
    private Button backbutton;

    @FXML
    private Button comm_nm;

    @FXML
    private Button finalPostButton;

    @FXML
    private ImageView previewpost;

    @FXML
    private Button chooseimagebutton;

    @FXML
    private TextField captionlabel;

    private Image postimage;

    @FXML
    void backtofeed(ActionEvent event) {
        main.changeScene("feed-page.fxml");


    }

    @FXML
    void finalpostButtonAction(ActionEvent event) {

        String caption = captionlabel.getText();

        if(!(caption.equals("")) && postimage != null) {
            controller.createPost(caption, postimage);

            main.changeScene("feed-page.fxml");
        }
        else{
            System.out.println("make sure you have an image and a caption");
        }



    }
    @FXML
    void chooseimagebuttonaction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

// Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.jpeg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);


        File file = fileChooser.showOpenDialog(new Stage());

// If a file is selected
        if (file != null) {
// Load the selected image into the ImageView
            postimage = new Image(file.toURI().toString());
            previewpost.setImage(postimage);
        }


    }


}


