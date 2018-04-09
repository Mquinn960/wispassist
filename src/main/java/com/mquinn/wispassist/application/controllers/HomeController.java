package main.java.com.mquinn.wispassist.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public Button btnExit;

    @FXML
    public void startButtonAction(ActionEvent event){
        try {
            Parent main = FXMLLoader.load(getClass().getResource("../layout/app_main.fxml"));
            Scene mainScene = new Scene(main);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitButtonAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}
