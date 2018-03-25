package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Graph;
import main.java.com.mquinn.graphing.Vertex;

import java.util.Arrays;
import java.util.List;

public class Network extends Graph implements INetwork {

    private IAdjacencyMatrixStrategy adjMatrixStrategy;
    private IPathfindingStrategy pathfindingStrategy;
    private ISpanningTreeStrategy spanningTreeStrategy;
    private Edge cheapestLink;

    public Network(IAdjacencyMatrixStrategy adjMatrixStrategy, IPathfindingStrategy pathfindingStrategy, ISpanningTreeStrategy spanningTreeStrategy){
        this.adjMatrixStrategy = adjMatrixStrategy;
        this.pathfindingStrategy = pathfindingStrategy;
        this.spanningTreeStrategy = spanningTreeStrategy;
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
        System.out.println("Number of Vertices: " + this.getNumVertices() + "\r");
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
