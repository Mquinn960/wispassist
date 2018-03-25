package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

import java.util.LinkedList;

public class ShortestPath extends LinkedList {

    private LinkedList<Vertex> shortestPath;
    private Vertex endVertex;
    private Vertex startVertex;
    private boolean pathFound = false;

    public ShortestPath(LinkedList<Vertex> path, Vertex startVertex, Vertex endVertex){
        this.shortestPath = path;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        updatePathFound();
    }

    public boolean getPathFound(){
        return this.pathFound;
    }

    public void printPath(){
        if (this.pathFound){
            System.out.println("Shortest Path" + "\r");
            for (Vertex vertex : this.shortestPath){
                if (vertex instanceof Device){
                    System.out.println(((Device) vertex).getDeviceName() + "\r");
                    System.out.println(vertex.getDistanceFromSource() + "\r");
                }
            }
        } else {
            System.out.println(this.getPathFound());
        }
    }

    private void updatePathFound(){
        if (this.shortestPath.contains(this.endVertex) && this.endVertex.getDistanceFromSource() != Integer.MAX_VALUE){
            this.pathFound = true;
        }
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

}
