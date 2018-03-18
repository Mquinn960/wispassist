package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

import java.util.LinkedList;

public class DijkstraPathfindingStrategy implements IPathfindingStrategy {

    private LinkedList<Vertex> shortestPath;
    private LinkedList<Vertex> provisionalVertices;
    private Vertex currentVertex;

    @Override
    public LinkedList<Vertex> calculatePath(Network network, Vertex startVertex, Vertex endVertex) {

        // Set input destination vertex weight to "infinite"
        for (Edge edge : endVertex.getEdges()){
            edge.setIWeightStrategy(new MaxWeightStrategy());
        }
        // Set input start vertex weight to 0
        for (Edge edge : endVertex.getEdges()){
            edge.setIWeightStrategy(new MinWeightStrategy());
        }

        // Add start vertex as first in the linked list
        this.shortestPath.add(startVertex);

        // Main loop
        // While the linked list has the initial vertex
        while (!startVertex.getEdges().isEmpty()) {
            currentVertex =
            for (something) {

            }
            shortestPath.add(currentVertex);
        }

        return shortestPath;
    }



}
