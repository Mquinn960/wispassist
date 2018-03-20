package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Vertex;

import java.util.LinkedList;

public interface INetwork {

    void printNetwork();
    void printAdjMatrix(boolean printSteps);
    int[][] getAdjacencyMatrix(boolean printSteps);
    void setAdjacencyMatrixStrategy(IAdjacencyMatrixStrategy adjMatrixStrategy);
    void setPathfindingStrategy(IPathfindingStrategy pathfindingStrategy);
    ShortestPath calculatePath(Vertex startVertex, Vertex endVertex);

}
