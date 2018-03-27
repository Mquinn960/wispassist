package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Graph;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

import java.util.Arrays;
import java.util.List;

public class Network extends Graph implements INetwork {

    private IAdjacencyMatrixStrategy adjMatrixStrategy;
    private IPathfindingStrategy pathfindingStrategy;
    private ISpanningTreeStrategy spanningTreeStrategy;
    private INetworkPrintStrategy networkPrintStrategy;
    private Edge cheapestLink;

    public Network(IAdjacencyMatrixStrategy adjMatrixStrategy, IPathfindingStrategy pathfindingStrategy, ISpanningTreeStrategy spanningTreeStrategy, INetworkPrintStrategy networkPrintStrategy){
        this.adjMatrixStrategy = adjMatrixStrategy;
        this.pathfindingStrategy = pathfindingStrategy;
        this.spanningTreeStrategy = spanningTreeStrategy;
        this.networkPrintStrategy = networkPrintStrategy;
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
    public boolean removeLink(Link link) {
        return link.getEndVertex().removeEdge(link);
    }

    @Override
    public void printNetwork() {
        this.networkPrintStrategy.printNetwork(this);
    }

}
