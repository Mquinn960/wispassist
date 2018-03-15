package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Graph;
import main.java.com.mquinn.graphing.Vertex;

import java.util.List;

public class Network extends Graph implements INetwork {

    public Network(){

    }

    @Override
    public void printNetwork() {
        for (Vertex vertex : this.vertices){
            if (vertex instanceof Device){
                System.out.println("Device Name: " + ((Device) vertex).getDeviceName());
                List<Edge> edgeList = vertex.getEdges();
                for (Edge edge : edgeList){
                    if (edge instanceof Link){
                        System.out.println("Link Name -> " + ((Link) edge).getLinkName());
                        System.out.println("Destination -> " + ((Device)edge.getEndVertex()).getDeviceName());
                        System.out.println("Link Weight -> " + edge.getWeight());
                    }
                }
                System.out.println("\r");
            }
        }
    }

}
