package main.java.com.mquinn.wispassist.application.controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MainController implements Initializable, MapComponentInitializedListener {

    @FXML
    public Button btnExit, btnAddDevice, btnAddLink, btnRefresh, btnShortestPath, btnMinimumNetwork;
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
    public void shortestPathButtonAction(ActionEvent event){
        try {
            Parent add = FXMLLoader.load(getClass().getResource("../layout/app_shortest_route.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Calculate Route");
            stage.setScene(new Scene(add, 1000, 800));
            stage.show();
            // Hide current window if necessary
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void minimumNetworkButtonAction(ActionEvent event){
        try {
            Parent add = FXMLLoader.load(getClass().getResource("../layout/app_optimise_network.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Optimise Network");
            stage.setScene(new Scene(add, 450, 450));
            stage.show();
            // Hide current window if necessary
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        mapInitialized();

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

        ArrayList<LatLong> markers = new ArrayList<>();

        for (Vertex vertex: PlanningService.getInstance().getMainNetwork().vertices){
            if (vertex instanceof Device){
                MarkerOptions markerOptions = new MarkerOptions();
                LatLong latLong = new LatLong(((Device) vertex).getLatitude(), ((Device) vertex).getLongitude());
                markerOptions.position(latLong);
                map.addMarker(new Marker(markerOptions));
                markers.add(latLong);
            }
        }

        for (Vertex vertex: PlanningService.getInstance().getMainNetwork().vertices){
            for (Edge edge: vertex.getEdges()){
                if (edge instanceof Link){
                    if (edge.getStartVertex() instanceof Device && edge.getEndVertex() instanceof Device){
                        LatLong startDevice = new LatLong(((Device) edge.getStartVertex()).getLatitude(), ((Device) edge.getStartVertex()).getLongitude());
                        LatLong endDevice = new LatLong(((Device) edge.getEndVertex()).getLatitude(), ((Device) edge.getEndVertex()).getLongitude());
                        LatLong[] coordArray = new LatLong[]{startDevice,endDevice};
                        MVCArray mvcArray = new MVCArray(coordArray);
                        PolylineOptions polylineOptions = new PolylineOptions()
                                .path(mvcArray)
                                .strokeColor("red")
                                .strokeWeight(2);
                        Polyline link = new Polyline(polylineOptions);
                        map.addMapShape((MapShape) link);
                    }
                }
            }
        }

        LatLongBounds latLongBounds = new LatLongBounds();
        for (LatLong latLong : markers) {
            latLongBounds.extend(latLong);
        }
        map.fitBounds(latLongBounds);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       mapView.addMapInializedListener(this);
    }
}
