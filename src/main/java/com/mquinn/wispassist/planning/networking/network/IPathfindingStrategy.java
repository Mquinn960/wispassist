package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

public interface IPathfindingStrategy {

    ShortestPath calculatePath(Network network, Vertex startVertex, Vertex endVertex);

}
