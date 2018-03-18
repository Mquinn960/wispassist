package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Vertex;

import java.util.LinkedList;

public interface IPathfindingStrategy {

    LinkedList<Vertex> calculatePath(Network network, Vertex startVertex, Vertex endVertex);

}
