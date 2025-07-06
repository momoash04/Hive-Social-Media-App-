package com.example.notabaldlion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FriendsPageController {

    User controller = singleton.getuser();

    @FXML
    private Button Search;

    @FXML
    private Button addfriendbutton;

    @FXML
    private TextField friendusername;

    @FXML
    private ListView<String> listview;

    @FXML
    private Button removefriendbutton;

    @FXML
    private TextField removefriendusername;

    @FXML
    private TextField username;

    @FXML
    void addfriendbuttonAction(ActionEvent event) {
        String username = friendusername.getText();
        controller.friend(username);


    }

    @FXML
    void removefriendAction(ActionEvent event) {
        String username = friendusername.getText();
        controller.unfriend(username);


    }

    @FXML
    void removefriendbuttonAction(ActionEvent event) {

    }

    @FXML
    void searchbuttonAction(ActionEvent event) {
        ArrayList<String> people = new ArrayList<>(); ;
        for(User user: singleton.getusers()){
            people.add(user.getUsername());
        }

        listview.getItems().clear();
        listview.getItems().addAll(searchList(username.getText(),people));



    }
    public void initialize() {

        ArrayList<String> people = new ArrayList<>();
        for(User user: singleton.getusers()){
            people.add(user.getUsername());
        }
        listview.getItems().addAll(people);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
}

    }

