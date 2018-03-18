package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DijkstraPathfindingStrategy implements IPathfindingStrategy {

    @Override
    public LinkedList<Vertex> calculatePath(Network network, Vertex startVertex, Vertex endVertex) {

        Vertex currentVertex;
        HashMap<Vertex, Double> provisionalVertices = new HashMap<>();
        LinkedList<Vertex> shortestPath = new LinkedList<>();
        double distanceTotal = 0;

        // Set all input network weights to "infinite"
        for (Vertex vertex : network.vertices){
            for (Edge edge : vertex.getEdges()){
                edge.setWeight(Integer.MAX_VALUE);
            }
        }

        // Set input destination vertex weight to "infinite"
        for (Edge edge : endVertex.getEdges()){
            edge.setWeight(Integer.MAX_VALUE);
        }
        // Set input start vertex weight to 0
        for (Edge edge : endVertex.getEdges()){
            edge.setWeight(0);
        }

        // Add start vertex as first in the linked list
        provisionalVertices.put(startVertex, 0.0);
        currentVertex = startVertex;

        // Main loop
        // While the provisional list has vertices with valid edges to try
        while (!provisionalVertices.isEmpty() && !currentVertex.equals(endVertex)) {
            for (Map.Entry <Vertex, Double> entry : provisionalVertices.entrySet()){
                if (entry.getKey().getEdgeWithLowestWeight().getWeight() < currentVertex.getEdgeWithLowestWeight().getWeight()){
                    currentVertex = entry.getKey().getEdgeWithLowestWeight().getEndVertex();
                }
            }
            provisionalVertices.remove(currentVertex);
            for (Edge edge : currentVertex.getEdges()){
                provisionalVertices.put(edge.getEndVertex(), edge.getWeight());
                distanceTotal = currentVertex.getEdgeWithLowestWeight().getWeight() + edge.getWeight();
                if (edge.getWeight() == 0 || edge.getWeight() > distanceTotal) {

                }
            }
            // Add shortest path vertex to path
            shortestPath.add(currentVertex);
        }

        return shortestPath;
    }

}
