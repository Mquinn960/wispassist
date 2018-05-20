package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

/**
 * Strategy interface for pathfinding classes
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public interface IPathfindingStrategy {

    ShortestPath calculatePath(Network network, Vertex startVertex, Vertex endVertex);

}
