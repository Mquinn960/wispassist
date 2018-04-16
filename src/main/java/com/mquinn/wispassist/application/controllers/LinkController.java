package main.java.com.mquinn.wispassist.application.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

import java.io.IOException;

public class LinkController {

    @FXML
    public Button btnExit, btnAdd;
    public ComboBox cboLinkStart, cboLinkEnd;

    @FXML
    public void exitButtonAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void initialize() {

        ObservableList<String> startOptions = FXCollections.observableArrayList();

        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            if (device instanceof Device){
                startOptions.add(((Device) device).getDeviceName());
            }
        }

        cboLinkStart.setItems(startOptions);

        ObservableList<String> endOptions = FXCollections.observableArrayList();

        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            if (device instanceof Device){
                endOptions.add(((Device) device).getDeviceName());
            }
        }

        cboLinkEnd.setItems(endOptions);

    }

    @FXML
    public void addLink(ActionEvent event){

        if (!cboLinkStart.getSelectionModel().getSelectedItem().toString().isEmpty() &&
                !cboLinkEnd.getSelectionModel().getSelectedItem().toString().isEmpty()) {
            try {

                Device startDevice = PlanningService.getInstance().getMainNetwork().getDeviceWithName(cboLinkStart.getSelectionModel().getSelectedItem().toString());
                Device endDevice = PlanningService.getInstance().getMainNetwork().getDeviceWithName(cboLinkEnd.getSelectionModel().getSelectedItem().toString());

                Link forwards = PlanningService.getInstance().getLinkFactory().createLinkAuto(startDevice, endDevice);
                Link backwards = PlanningService.getInstance().getLinkFactory().createLinkAuto(endDevice, startDevice);

                startDevice.addEdge(forwards);
                endDevice.addEdge(backwards);

                Stage stage = (Stage) btnAdd.getScene().getWindow();
                stage.close();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

}
