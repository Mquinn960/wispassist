package main.java.com.mquinn.wispassist.application.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

import java.io.IOException;

public class MainController {

    @FXML
    public Button btnExit, btnAddDevice, btnAddLink, btnRefresh;
    public ScrollPane mapPane;
    public TableView tblDevices;
    public TableColumn colDeviceName, colLat, colLong;

    @FXML
    public void exitButtonAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addDeviceButtonAction(ActionEvent event){
        try {
            Parent add = FXMLLoader.load(getClass().getResource("../layout/app_add_device.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Device");
            stage.setScene(new Scene(add, 450, 450));
            stage.show();
            // Hide current window if necessary
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addLinkButtonAction(ActionEvent event){
        try {
            Parent add = FXMLLoader.load(getClass().getResource("../layout/app_add_link.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Link");
            stage.setScene(new Scene(add, 450, 450));
            stage.show();
            // Hide current window if necessary
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRefreshData(ActionEvent event){
        refreshDeviceTable();
        refreshGraphPane();
    }

    public void refreshData(){
        refreshDeviceTable();
        refreshGraphPane();
    }

    private void refreshDeviceTable(){

        final ObservableList<Device> devices = FXCollections.observableArrayList();
        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            devices.add((Device)device);
        }

        colDeviceName.setCellValueFactory(
                new PropertyValueFactory<Device,String>("deviceName")
        );
        colLat.setCellValueFactory(
                new PropertyValueFactory<Device,Double>("latitude")
        );
        colLong.setCellValueFactory(
                new PropertyValueFactory<Device,Double>("longitude")
        );

        tblDevices.setItems(devices);

    }

    private void refreshGraphPane(){

    }

}
