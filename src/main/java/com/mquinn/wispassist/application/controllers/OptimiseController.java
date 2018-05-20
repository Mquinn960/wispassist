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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;
import main.java.com.mquinn.wispassist.planning.networking.network.Network;
import sun.nio.ch.Net;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Controller for graph optimisation pane
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class OptimiseController implements Initializable, MapComponentInitializedListener {

    @FXML
    public Button btnExit, btnConfirm;
    @FXML
    public ComboBox cboDeviceStart;
    @FXML
    private GoogleMapView mapViewPath;
    @FXML
    private GoogleMap map;

    private LinkedList<Vertex> path;

    private ArrayList<Marker> markerArrayList;
    private ArrayList<Polyline> shapeArrayList;

    private Network minNetwork;

    /**
     * Called when map pane initialises
     * Set map options and add nodes/edges
     */
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

        if (map != null && markerArrayList != null){
            map.removeMarkers(markerArrayList);
            map.clearMarkers();
        }

        if (map != null && shapeArrayList != null){
            for (Polyline shape: shapeArrayList){
                map.removeMapShape(shape);
            }
        }

        if (map != null){
            int currentZoom = map.getZoom();
            map.setZoom( currentZoom - 1 );
            map.setZoom( currentZoom );
        }

        ArrayList<LatLong> markers = new ArrayList<>();
        markerArrayList = new ArrayList<>();

        for (Vertex vertex: minNetwork.vertices){
            if (vertex != null){
                if (vertex instanceof Device){
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLong latLong = new LatLong(((Device) vertex).getLatitude(), ((Device) vertex).getLongitude());
                    markerOptions.position(latLong);
                    Marker newMarker = new Marker(markerOptions);
                    markerArrayList.add(newMarker);
                    map.addMarker(newMarker);
                    markers.add(latLong);
                }
            }
        }

        for (Vertex vertex: minNetwork.vertices){
            for (Edge edge: vertex.getEdges()){
                if (edge instanceof Link){
                    if (edge.getStartVertex() instanceof Device && edge.getEndVertex() instanceof Device){
                        LatLong startDevice = new LatLong(((Device) edge.getStartVertex()).getLatitude(), ((Device) edge.getStartVertex()).getLongitude());
                        LatLong endDevice = new LatLong(((Device) edge.getEndVertex()).getLatitude(), ((Device) edge.getEndVertex()).getLongitude());
                        LatLong[] coordArray = new LatLong[]{startDevice,endDevice};
                        MVCArray mvcArray = new MVCArray(coordArray);
                        PolylineOptions polylineOptions = new PolylineOptions()
                                .path(mvcArray)
                                .strokeColor("blue")
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

    /**
     * Called when window initialises
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> startOptions = FXCollections.observableArrayList();

        for (Vertex device: PlanningService.getInstance().getMainNetwork().getVertices()){
            if (device instanceof Device){
                startOptions.add(((Device) device).getDeviceName());
            }
        }

        cboDeviceStart.setItems(startOptions);

        mapViewPath.addMapInializedListener(this);
        path = new LinkedList<>();

    }

    /**
     * Exits application screen
     *
     * @param event
     */
    @FXML
    public void exitButtonAction(ActionEvent event){
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    /**
     * Calls map rendering sequence on optimised network
     *
     * @param event
     */
    @FXML
    public void confirmOptimise(ActionEvent event){

        if (!cboDeviceStart.getSelectionModel().getSelectedItem().toString().isEmpty()) {
            try {

                Device startDevice = PlanningService.getInstance().getMainNetwork().getDeviceWithName(cboDeviceStart.getSelectionModel().getSelectedItem().toString());

                minNetwork = PlanningService.getInstance().getMainNetwork().calculateSpanningTree(startDevice);

                mapInitialized();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

}
