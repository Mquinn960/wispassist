package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.link.GeolocationWeightStrategy;

public class UndirectedNetworkPrintStrategy extends GeneralNetworkPrintStrategy implements INetworkPrintStrategy {

    @Override
    public void printNetwork(Network network) {
        for (Vertex vertex : network.vertices) {
            for (Edge edge : vertex.getEdges()) {
                edge.getEndVertex().addEdge(new Edge(edge.getEndVertex(), edge.getStartVertex(), new GeolocationWeightStrategy()) {
                });
            }
        }
        super.printNetwork(network);
    }

}
