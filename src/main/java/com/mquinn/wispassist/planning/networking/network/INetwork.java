package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

public interface INetwork {

    void printNetwork();

    void setAdjacencyMatrixStrategy(IAdjacencyMatrixStrategy adjMatrixStrategy);
    void printAdjMatrix(boolean printSteps);
    int[][] getAdjacencyMatrix(boolean printSteps);

    void setPathfindingStrategy(IPathfindingStrategy pathfindingStrategy);
    ShortestPath calculatePath(Vertex startVertex, Vertex endVertex);

    void setSpanningTreeStrategy(ISpanningTreeStrategy spanningTreeStrategy);
    Network calculateSpanningTree();

    void makeUndirected();

    boolean containsLink(Link link);
    boolean removeLink(Link link);

}
