package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Graph;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.AutoLinkNameStrategy;
import main.java.com.mquinn.wispassist.planning.networking.link.GeolocationWeightStrategy;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

import java.util.Arrays;
import java.util.List;

public class Network extends Graph implements INetwork {

    private IAdjacencyMatrixStrategy adjMatrixStrategy;
    private IPathfindingStrategy pathfindingStrategy;
    private ISpanningTreeStrategy spanningTreeStrategy;
    private INetworkPrintStrategy networkPrintStrategy;
    private Edge cheapestLink;
    private boolean isDirected;

    public Network(IAdjacencyMatrixStrategy adjMatrixStrategy, IPathfindingStrategy pathfindingStrategy, ISpanningTreeStrategy spanningTreeStrategy, INetworkPrintStrategy networkPrintStrategy, boolean isDirected){
        this.adjMatrixStrategy = adjMatrixStrategy;
        this.pathfindingStrategy = pathfindingStrategy;
        this.spanningTreeStrategy = spanningTreeStrategy;
        this.networkPrintStrategy = networkPrintStrategy;
        this.isDirected = isDirected;
    }

    @Override
    public int[][] getAdjacencyMatrix(boolean printSteps) {
        return this.adjMatrixStrategy.calcAdjacencyMatrix(this, printSteps);
    }

    @Override
    public void setAdjacencyMatrixStrategy(IAdjacencyMatrixStrategy adjMatrixStrategy) {
        this.adjMatrixStrategy = adjMatrixStrategy;
    }

    @Override
    public void printAdjMatrix(boolean printSteps) {
        System.out.println("Adjacency Matrix: ");
        for (int[] adjMatrix : this.adjMatrixStrategy.calcAdjacencyMatrix(this, printSteps)) {
            System.out.println(Arrays.toString(adjMatrix));
        }
    }

    public int[][] getAdjMatrix(){
        return this.adjMatrixStrategy.calcAdjacencyMatrix(this, false);
    }

    @Override
    public void setPathfindingStrategy(IPathfindingStrategy pathfindingStrategy) {
        this.pathfindingStrategy = pathfindingStrategy;
    }

    @Override
    public ShortestPath calculatePath(Vertex startVertex, Vertex endVertex) {
        return this.pathfindingStrategy.calculatePath(this, startVertex, endVertex);
    }

    @Override
    public void setSpanningTreeStrategy(ISpanningTreeStrategy spanningTreeStrategy) {
        this.spanningTreeStrategy = spanningTreeStrategy;
    }

    @Override
    public Network calculateSpanningTree() {
        return this.spanningTreeStrategy.calculateSpanningTree(this);
    }

    @Override
    public boolean containsLink(Link link) {
        boolean containsLink = false;
        for (Vertex vertex: this.vertices){
            if (vertex.getEdges().contains(link)){
                containsLink = true;
            }
        }
        return containsLink;
    }

    @Override
    public void makeUndirected() {
        if (!this.isDirected){
            for (Vertex vertex : this.vertices) {
                for (Edge edge : vertex.getEdges()) {
                    edge.getEndVertex().addEdge(new Link(edge.getEndVertex(), edge.getStartVertex(), new AutoLinkNameStrategy(), new GeolocationWeightStrategy()) {
                    });
                }
            }
        }
    }

    @Override
    public boolean removeLink(Link link) {
        return link.getEndVertex().removeEdge(link);
    }

    @Override
    public void printNetwork() {
        this.networkPrintStrategy.printNetwork(this);
    }

    public Device getDeviceWithName(String deviceName){
        for (Vertex vertex: this.vertices){
            if (vertex instanceof Device){
                if (((Device) vertex).getDeviceName().equals(deviceName)){
                    return (Device) vertex;
                }
            }
        }
        return null;
    }

}
