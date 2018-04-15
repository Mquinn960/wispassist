package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

import java.util.LinkedList;

public class ShortestPath extends LinkedList {

    private LinkedList<Vertex> shortestPath;
    private boolean pathFound = false;

    public ShortestPath(LinkedList<Vertex> path){
        this.shortestPath = path;
    }

    public void printPath(){
        System.out.println("Shortest Path" + "\r");
        for (Vertex vertex : this.shortestPath){
            if (vertex instanceof Device){
                System.out.println(((Device) vertex).getDeviceName() + "\r");
                System.out.println(vertex.getDistanceFromSource() + "\r");
            }
        }
    }

    public LinkedList<Vertex> getPath(){
        return this.shortestPath;
    }

}
