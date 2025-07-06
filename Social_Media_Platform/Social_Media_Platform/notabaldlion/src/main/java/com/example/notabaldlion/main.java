package com.example.notabaldlion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {


    private static Stage primaryStage;
    public User controller; // da el controller bta3 kol 7aga

    Scene beginScene,registerScene,loginPage, feedPage, profilePage,PostPage;


    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        main.primaryStage = primaryStage;
        User.loadAllUsers();
        Post.loadPosts();
        User.signup("Omar", "omarahmed", "omarahmed123", "omardardir" );
        User.signup("Mark", "mark.edward205", "mark2005", "Mark_Ed" );
        User.signup("Mohamed", "mohamedashraf", "mohamed123", "mohamedashraf" );


        singleton.setuser(User.login("Mark_Ed", "mark2005"));

        //controller = singleton.getuser();



        FXMLLoader fxmlLoaderFeedPage = new FXMLLoader(main.class.getResource("feed-page.fxml"));
        FXMLLoader fxmlLoaderPostPage = new FXMLLoader(main.class.getResource("post-page.fxml"));
        FXMLLoader fxmlLoaderPost = new FXMLLoader(main.class.getResource("post.fxml"));



        // start begin page (awl page 5ales)
        GridPane beginPane = new GridPane();
        beginPane.setHgap(30);
        beginPane.setVgap(30);
        beginPane.setAlignment(Pos.CENTER);
        Button loginbeginButton = new Button("Login");
        loginbeginButton.setOnAction(e -> {

            primaryStage.setScene(loginPage);
        });

        loginbeginButton.setPrefWidth(60);
        Button registerbeginButton = new Button("register");
        registerbeginButton.setOnAction(e -> {
            primaryStage.setScene(registerScene);
        });

        registerbeginButton.setPrefWidth(60);
        beginPane.add(loginbeginButton, 0, 0);
        beginPane.add(registerbeginButton, 0, 1);

        beginScene = new Scene(beginPane, 320, 240);

        //login page
        GridPane loginPane = new GridPane();
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        loginPane.setHgap(30);
        loginPane.setVgap(30);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.add(new Label("username:"), 0, 0);
        loginPane.add(usernameField, 1, 0);
        loginPane.add(new Label("password:"), 0, 1);
        loginPane.add(passwordField, 1, 1);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            controller = User.login(username, password);

            if(controller != null) {
                singleton.setuser(controller);

                feedPageController feedPageController = fxmlLoaderFeedPage.getController();

                try {
                    feedPageController.initialize();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                changeScenewithcontroller2("feed-page.fxml");

            }
            else{
                System.out.println("Wrong username or password");
            }
        });
        loginPane.add(loginButton, 0, 2);
        GridPane.setHalignment(loginButton, HPos.CENTER);

        loginPage = new Scene(loginPane, 320, 240);



        VBox vbox = new VBox(10); // Set spacing between elements

        vbox.setPadding(new Insets(5));
        vbox.getStyleClass().add("bg-1");

        //Image image = new Image();        // Load image from file


        //ImageView imageView = new ImageView(image);  // 2nd IV forces image to
        //imageView.setFitHeight(100);                 // fit into 100-x-100
        //imageView.setFitWidth(100);
        //imageView.setPreserveRatio(true);
        //vbox.getChildren().add(imageView);
//        ImageView imageView3 = new ImageView(image);  // 3rd IV leaves size as-is,
//        imageView3.setRotate(90);                     // but rotates (CW) 90 deg,
//        pane.getChildren().add(imageView3);

        // Create a scene and place it in the stage
        TextField createusernameField = new TextField();
        TextField createpasswordField = new TextField();
        TextField createfirstname = new TextField();
        TextField createlastname = new TextField();
        TextField createemail = new TextField();


        // Username
        HBox usernameBox = new HBox(10);
        usernameBox.getChildren().addAll(new Label("Username"), createusernameField);
        vbox.getChildren().add(usernameBox);

        // Passwords
        HBox passwordBox = new HBox(10);
        passwordBox.getChildren().addAll(new Label("Password"), createpasswordField);
        vbox.getChildren().add(passwordBox);



        // Names
        HBox nameBox = new HBox(10);
        nameBox.getChildren().addAll(new Label("First Name"), createfirstname,new Label("Last Name"),createlastname);
        vbox.getChildren().add(nameBox);

        HBox EmailBox = new HBox(10);
        EmailBox.getChildren().addAll(new Label("Email        "), createemail);
        vbox.getChildren().add(EmailBox);

        // Age
        HBox ageBox = new HBox(10);
        ageBox.getChildren().addAll(new Label("Date of birth"), new TextField());

        Button registerButton = new Button("signup");
        registerButton.setOnAction(e -> {
            String username = createusernameField.getText();
            String password = createpasswordField.getText();
            String firstname = createfirstname.getText();
            String lastname = createlastname.getText();
            String email = createemail.getText();

            try {
                controller =User.signup(firstname, email, password, username);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            if(controller != null) {
                singleton.setuser(controller);

                changeScene("after-registration.fxml");

            }
            else {
                System.out.println("yarabbbbbbbbb");
            }
        });
        vbox.getChildren().add(ageBox);
        vbox.getChildren().add(registerButton);

        feedPage = new Scene(fxmlLoaderFeedPage.load(), 430, 650);
        PostPage = new Scene(fxmlLoaderPostPage.load(), 430, 650);
        registerScene = new Scene(vbox, 500, 300);
        PostPage = new Scene(fxmlLoaderPost.load(), 430, 650);


        primaryStage.setTitle("The Hive");
        Image icon = new Image("com/example/notaBaldLion/img.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(beginScene);
        primaryStage.show();
    }

    public static void changeScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(main.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void changeScenewithcontroller(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(main.class.getResource(fxmlPath));
            Parent root = loader.load();
            profilepageController controller = loader.getController();
            controller.initialize();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void changeScenewithcontroller2(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(main.class.getResource(fxmlPath));
            Parent root = loader.load();
            feedPageController controller = loader.getController();
            controller.initialize();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void changeScenewithcontroller3(String fxmlPath, Post thispost) {
        try {
            if(thispost != null) {
                System.out.println("yarabbbbbbbbb");
            }
            FXMLLoader loader = new FXMLLoader(main.class.getResource(fxmlPath));
            Parent root = loader.load();
            CommentsPageController controller = loader.getController();
            controller.initialize(thispost);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.print("showing comments");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}