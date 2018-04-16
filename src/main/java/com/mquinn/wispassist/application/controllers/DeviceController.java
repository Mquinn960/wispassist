package main.java.com.mquinn.wispassist.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

public class DeviceController {

    @FXML
    public Button btnExit, btnAdd;
    public TextField txtDeviceName, txtLatitude, txtLongitude;

    @FXML
    public void exitButtonAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addDevice(ActionEvent event){

        if (!txtDeviceName.getText().isEmpty() &&
            !txtLatitude.getText().isEmpty() &&
            !txtLongitude.getText().isEmpty()){
            try {
                Device newDevice = PlanningService.getInstance().getDeviceFactory().createDeviceManual(txtDeviceName.getText(),
                        Double.parseDouble(txtLatitude.getText()),
                        Double.parseDouble(txtLongitude.getText()),
                        false);

                PlanningService.getInstance().getMainNetwork().addVertex(newDevice);

                Stage stage = (Stage) btnAdd.getScene().getWindow();
                stage.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

    }

}
