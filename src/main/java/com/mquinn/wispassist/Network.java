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
            System.out.println("Vertex Name: " + vertex);
            List<Edge> edgeList = vertex.getEdges();
            for (Edge edge : edgeList){
                System.out.println("->" + edge.getEndVertex());
            }
        }
    }

}
