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

/**
 * Main network class which inherits graphing properties from
 * Graph superclass
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class Network extends Graph implements INetwork {

    private IAdjacencyMatrixStrategy adjMatrixStrategy;
    private IPathfindingStrategy pathfindingStrategy;
    private ISpanningTreeStrategy spanningTreeStrategy;
    private INetworkPrintStrategy networkPrintStrategy;
    private Edge cheapestLink;
    private boolean isDirected;

    /**
     * Constructor to define graphing parameters
     * and utility strategy classes
     *
     * @param adjMatrixStrategy
     * @param pathfindingStrategy
     * @param spanningTreeStrategy
     * @param networkPrintStrategy
     * @param isDirected
     */
    public Network(IAdjacencyMatrixStrategy adjMatrixStrategy, IPathfindingStrategy pathfindingStrategy, ISpanningTreeStrategy spanningTreeStrategy, INetworkPrintStrategy networkPrintStrategy, boolean isDirected){
        this.adjMatrixStrategy = adjMatrixStrategy;
        this.pathfindingStrategy = pathfindingStrategy;
        this.spanningTreeStrategy = spanningTreeStrategy;
        this.networkPrintStrategy = networkPrintStrategy;
        this.isDirected = isDirected;
    }

    /**
     * Return the adjacency matrix for this network
     *
     * @param printSteps
     * @return
     */
    @Override
    public int[][] getAdjacencyMatrix(boolean printSteps) {
        return this.adjMatrixStrategy.calcAdjacencyMatrix(this, printSteps);
    }

    /**
     * Set the strategy for calculating adjacency matrix for this class
     *
     * @param adjMatrixStrategy
     */
    @Override
    public void setAdjacencyMatrixStrategy(IAdjacencyMatrixStrategy adjMatrixStrategy) {
        this.adjMatrixStrategy = adjMatrixStrategy;
    }

    /**
     * Prints adjacency matrix for this network
     *
     * @param printSteps
     */
    @Override
    public void printAdjMatrix(boolean printSteps) {
        System.out.println("Adjacency Matrix: ");
        for (int[] adjMatrix : this.adjMatrixStrategy.calcAdjacencyMatrix(this, printSteps)) {
            System.out.println(Arrays.toString(adjMatrix));
        }
    }

    /**
     * Get the adjacency matrix int[][] array from this network
     *
     * @return
     */
    public int[][] getAdjMatrix(){
        return this.adjMatrixStrategy.calcAdjacencyMatrix(this, false);
    }

    /**
     * Set the pathfinding algorithm strategy for the network
     *
     * @param pathfindingStrategy
     */
    @Override
    public void setPathfindingStrategy(IPathfindingStrategy pathfindingStrategy) {
        this.pathfindingStrategy = pathfindingStrategy;
    }

    /**
     * Calculate the path between start and end vertices through this graph
     *
     * @param startVertex
     * @param endVertex
     * @return
     */
    @Override
    public ShortestPath calculatePath(Vertex startVertex, Vertex endVertex) {
        return this.pathfindingStrategy.calculatePath(this, startVertex, endVertex);
    }

    /**
     * Set the spanning tree strategy for this network
     *
     * @param spanningTreeStrategy
     */
    @Override
    public void setSpanningTreeStrategy(ISpanningTreeStrategy spanningTreeStrategy) {
        this.spanningTreeStrategy = spanningTreeStrategy;
    }

    /**
     * Calculate the spanning tree of this network from a starting vertex
     *
     * @param startVertex
     * @return
     */
    @Override
    public Network calculateSpanningTree(Vertex startVertex) {
        return this.spanningTreeStrategy.calculateSpanningTree(this, startVertex);
    }

    /**
     * Returns true if this network contains the input link
     *
     * @param link
     * @return
     */
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

    /**
     * Method doubles up all links in the graph such that a directed graph is
     * now undirected.
     */
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

    /**
     * Remove link from network matching this input link
     *
     * @param link
     * @return
     */
    @Override
    public boolean removeLink(Link link) {
        return link.getEndVertex().removeEdge(link);
    }

    /**
     * Print the network via the printing strategy
     */
    @Override
    public void printNetwork() {
        this.networkPrintStrategy.printNetwork(this);
    }

    /**
     * Return device object with name matching the input string
     *
     * @param deviceName
     * @return
     */
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
