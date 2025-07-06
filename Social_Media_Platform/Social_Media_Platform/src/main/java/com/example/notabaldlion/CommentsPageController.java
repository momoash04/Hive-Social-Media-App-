package com.example.notabaldlion;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CommentsPageController {
        Post thispost = new Post();
        User controller = singleton.getuser();
        @FXML
        private ListView<String> AllComments;

        @FXML
        private Button BackButton;

        @FXML
        private Button addcomment;

        @FXML
        private TextField comment_text;

        void initialize(Post post){
                thispost = post;

                List<Comment> codedcomments = thispost.getComments();
                List<String> comments = new ArrayList<>();
                for(Comment comment : codedcomments){
                        comments.add(comment.toString());
                }
                AllComments.getItems().clear();
                AllComments.getItems().addAll(comments);



        }

        @FXML
        void BackToFeed(ActionEvent event) {
            main.changeScenewithcontroller2("feed-page.fxml");
        }

        @FXML
        void addCommentAction(ActionEvent event) {
                controller.createComment(thispost, comment_text.getText());

                List<Comment> codedcomments = thispost.getComments();
                List<String> comments = new ArrayList<>();
                for(Comment comment : codedcomments){
                        comments.add(comment.toString());
                }
                AllComments.getItems().clear();
                AllComments.getItems().addAll(comments);


        }


    }


