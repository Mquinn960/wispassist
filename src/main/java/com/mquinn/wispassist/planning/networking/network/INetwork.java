package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

/**
 * Interface for network classes inheriting from graph superclasses
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public interface INetwork {

    void printNetwork();

    void setAdjacencyMatrixStrategy(IAdjacencyMatrixStrategy adjMatrixStrategy);
    void printAdjMatrix(boolean printSteps);
    int[][] getAdjacencyMatrix(boolean printSteps);

    void setPathfindingStrategy(IPathfindingStrategy pathfindingStrategy);
    ShortestPath calculatePath(Vertex startVertex, Vertex endVertex);

    void setSpanningTreeStrategy(ISpanningTreeStrategy spanningTreeStrategy);
    Network calculateSpanningTree(Vertex startVertex);

    void makeUndirected();

    boolean containsLink(Link link);
    boolean removeLink(Link link);

}
