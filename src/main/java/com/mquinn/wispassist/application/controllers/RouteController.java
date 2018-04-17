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
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.network.ShortestPath;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class RouteController implements Initializable, MapComponentInitializedListener {

    @FXML
    public Button btnExit, btnConfirm;
    @FXML
    public ComboBox cboDeviceStart, cboDeviceEnd;
    @FXML
    private GoogleMapView mapViewPath;
    @FXML
    private GoogleMap map;

    private LinkedList<Vertex> path;

    private void addRouteLinks(Vertex vertex){

        try {
            if (vertex != null && vertex.getPreviousVertex() != null){
                LatLong startDevice = new LatLong(((Device) vertex).getLatitude(), ((Device) vertex).getLongitude());
                LatLong endDevice = new LatLong(((Device) vertex.getPreviousVertex()).getLatitude(), ((Device) vertex.getPreviousVertex()).getLongitude());
                LatLong[] coordArray = new LatLong[]{startDevice, endDevice};
                MVCArray mvcArray = new MVCArray(coordArray);
                PolylineOptions polylineOptions = new PolylineOptions()
                        .path(mvcArray)
                        .strokeColor("blue")
                        .strokeWeight(2);
                Polyline link = new Polyline(polylineOptions);
                map.addMapShape((MapShape) link);
                addRouteLinks(vertex.getPreviousVertex());
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }

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

        map = mapViewPath.createMap(mapOptions);

        map.clearMarkers();

        ArrayList<LatLong> markers = new ArrayList<>();

        for (Object device: path){
            if (device != null){
                MarkerOptions markerOptions = new MarkerOptions();
                LatLong latLong = new LatLong(((Device) device).getLatitude(), ((Device) device).getLongitude());
                markerOptions.position(latLong);
                map.addMarker(new Marker(markerOptions));
                markers.add(latLong);
            }
        }

        addRouteLinks(path.getLast());

        LatLongBounds latLongBounds = new LatLongBounds();
        for (LatLong latLong : markers) {
            latLongBounds.extend(latLong);
        }
        map.fitBounds(latLongBounds);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> startOptions = FXCollections.observableArrayList();

        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            if (device instanceof Device){
                startOptions.add(((Device) device).getDeviceName());
            }
        }

        cboDeviceStart.setItems(startOptions);

        ObservableList<String> endOptions = FXCollections.observableArrayList();

        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            if (device instanceof Device){
                endOptions.add(((Device) device).getDeviceName());
            }
        }

        cboDeviceEnd.setItems(endOptions);

        mapViewPath.addMapInializedListener(this);
        path = new LinkedList<>();

    }

    @FXML
    public void exitButtonAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void confirmRoute(ActionEvent event){

        if (!cboDeviceStart.getSelectionModel().getSelectedItem().toString().isEmpty() &&
                !cboDeviceEnd.getSelectionModel().getSelectedItem().toString().isEmpty()) {
            try {

                Device startDevice = PlanningService.getInstance().getMainNetwork().getDeviceWithName(cboDeviceStart.getSelectionModel().getSelectedItem().toString());
                Device endDevice = PlanningService.getInstance().getMainNetwork().getDeviceWithName(cboDeviceEnd.getSelectionModel().getSelectedItem().toString());

                path = PlanningService.getInstance().getMainNetwork().calculatePath(startDevice,endDevice).getPath();

                mapInitialized();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

}
