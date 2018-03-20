package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Vertex;

public interface IPathfindingStrategy {

    ShortestPath calculatePath(Network network, Vertex startVertex, Vertex endVertex);

}
