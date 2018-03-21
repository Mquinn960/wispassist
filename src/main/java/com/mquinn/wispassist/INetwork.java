package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

public interface INetwork {

    void printNetwork();

    void setAdjacencyMatrixStrategy(IAdjacencyMatrixStrategy adjMatrixStrategy);
    void printAdjMatrix(boolean printSteps);
    int[][] getAdjacencyMatrix(boolean printSteps);

    void setPathfindingStrategy(IPathfindingStrategy pathfindingStrategy);
    ShortestPath calculatePath(Vertex startVertex, Vertex endVertex);

    void setSpanningTreeStrategy(ISpanningTreeStrategy spanningTreeStrategy);
    Network calculateSpanningTree();

    boolean containsLink(Link link);
    boolean removeLink(Link link);

    Edge getCheapestLink();

}
