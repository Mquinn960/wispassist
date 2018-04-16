package main.java.com.mquinn.wispassist.application.controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MainController implements Initializable, MapComponentInitializedListener {

    @FXML
    public Button btnExit, btnAddDevice, btnAddLink, btnRefresh;
    @FXML
    public TableView tblDevices, tblLinks;
    @FXML
    public TableColumn colDeviceName, colLat, colLong, colLinkName, colLinkWeight;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private GoogleMap map;

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
        refreshData();
    }

    public void refreshData(){
        refreshDeviceTable();
        refreshLinkTable();
        refreshGraphPane();
    }

    private void refreshDeviceTable(){

        final ObservableList<Device> devices = FXCollections.observableArrayList();
        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            devices.add((Device)device);
        }

        colDeviceName.setCellValueFactory(new PropertyValueFactory<Device,String>("deviceName"));
        colLat.setCellValueFactory(new PropertyValueFactory<Device,Double>("latitude"));
        colLong.setCellValueFactory(new PropertyValueFactory<Device,Double>("longitude"));

        tblDevices.setItems(devices);

    }

    private void refreshLinkTable(){

        final ObservableList<Link> links = FXCollections.observableArrayList();
        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            for (Edge edge: device.getEdges()){
                if (edge instanceof Link){
                    links.add((Link)edge);
                }
            }
        }

        colLinkName.setCellValueFactory(new PropertyValueFactory<Link,String>("linkName"));
        colLinkWeight.setCellValueFactory(new PropertyValueFactory<Link,Double>("linkWeight"));

        tblLinks.setItems(links);

    }

    private void refreshGraphPane(){

        LinkedList<Marker> markers = new LinkedList<>();

        for (Vertex vertex: PlanningService.getInstance().getMainNetwork().vertices){
            if (vertex instanceof Device){
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLong(((Device) vertex).getLatitude(), ((Device) vertex).getLongitude()));
                map.addMarker(new Marker(markerOptions));
            }
        }

//        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
//        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
//        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
//        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
//
//        MarkerOptions markerOptions2 = new MarkerOptions();
//        markerOptions2.position(joshAndersonLocation);
//
//        MarkerOptions markerOptions3 = new MarkerOptions();
//        markerOptions3.position(bobUnderwoodLocation);
//
//        MarkerOptions markerOptions4 = new MarkerOptions();
//        markerOptions4.position(tomChoiceLocation);
//
//        MarkerOptions markerOptions5 = new MarkerOptions();
//        markerOptions5.position(fredWilkieLocation);
//

//        Marker joshAndersonMarker = new Marker(markerOptions2);
//        Marker bobUnderwoodMarker = new Marker(markerOptions3);
//        Marker tomChoiceMarker= new Marker(markerOptions4);
//        Marker fredWilkieMarker = new Marker(markerOptions5);
//

//        map.addMarker( joshAndersonMarker );
//        map.addMarker( bobUnderwoodMarker );
//        map.addMarker( tomChoiceMarker );
//        map.addMarker( fredWilkieMarker );

    }

    @Override
    public void mapInitialized() {

        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(51.2833942, -0.0184062))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(4.25);

        map = mapView.createMap(mapOptions);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       mapView.addMapInializedListener(this);
    }
}
