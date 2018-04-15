package main.java.com.mquinn.wispassist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("application/layout/app_home.fxml"));
        primaryStage.setTitle("Wisp Assist");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/images/favi.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}