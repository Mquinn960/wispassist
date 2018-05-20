package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

import java.util.List;

/**
 * General network printing strategy superclass for network printers
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class GeneralNetworkPrintStrategy implements INetworkPrintStrategy {

    /**
     * Print the network and devices/links
     *
     * @param network
     */
    @Override
    public void printNetwork(Network network) {

        System.out.println("Number of Vertices: " + network.getNumVertices() + "\r");
        for (Vertex vertex : network.vertices){
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
