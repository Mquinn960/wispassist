package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

import java.util.LinkedList;

/**
 * Shortest path LinkedList subclass
 * Contains helper methods for printing and returning a
 * sequential path
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class ShortestPath extends LinkedList {

    private LinkedList<Vertex> shortestPath;
    private boolean pathFound = false;

    /**
     * Constructor sets the shortest path input
     *
     * @param path
     */
    public ShortestPath(LinkedList<Vertex> path){
        this.shortestPath = path;
    }

    /**
     * Prints the path members and route to console
     */
    public void printPath(){
        System.out.println("Shortest Path" + "\r");
        for (Vertex vertex : this.shortestPath){
            if (vertex instanceof Device){
                System.out.println(((Device) vertex).getDeviceName() + "\r");
                System.out.println(vertex.getDistanceFromSource() + "\r");
            }
        }
    }

    /**
     * Encapsulation method to get the shortest path held
     *
     * @return
     */
    public LinkedList<Vertex> getPath(){
        return this.shortestPath;
    }

}
