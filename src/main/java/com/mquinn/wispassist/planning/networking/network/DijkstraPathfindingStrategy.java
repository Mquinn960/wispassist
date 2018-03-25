package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

import java.util.LinkedList;

public class DijkstraPathfindingStrategy implements IPathfindingStrategy {

    private LinkedList<Vertex> provisionalVertices = new LinkedList<>();
    private LinkedList<Vertex> shortestPath = new LinkedList<>();
    private Vertex currentVertex = new Vertex(){};
    private Vertex nextAdjacentVertex = new Vertex(){};
    private Vertex endVertex = new Vertex(){};
    private boolean isDirected;

    public DijkstraPathfindingStrategy(boolean isDirected){
        this.isDirected = isDirected;
    }

    @Override
    public ShortestPath calculatePath(Network network, Vertex startVertex, Vertex endVertex) {

        this.endVertex = endVertex;

        // Set all input non source vertex weights to "infinite"
        // Set source weight to 0
        for (Vertex vertex : network.vertices){
            if (!this.isDirected) {
                for (Edge edge : vertex.getEdges()) {
                    Edge newEdge = edge;
                    newEdge.replaceVertex(newEdge.getStartVertex(), edge.getEndVertex());
                    newEdge.replaceVertex(newEdge.getEndVertex(), edge.getStartVertex());
                    edge.getStartVertex().addEdge(newEdge);
                }
            }
            this.provisionalVertices.add(vertex);
            if (vertex.equals(startVertex)) {
                vertex.setDistanceFromSource(0);
            } else {
                vertex.setDistanceFromSource(Integer.MAX_VALUE);
            }
        }

        // While there are vertices to analyse and we have not already reached our destination
        while (!this.provisionalVertices.isEmpty() && !this.currentVertex.equals(endVertex)) {

            // Take the first provisional vertex with the lowest distance from source
            this.currentVertex = getClosestProvisionalVertexToSource();

            if (!this.currentVertex.equals(endVertex) && !this.nextAdjacentVertex.equals(endVertex)){
                // Mark this vertex as traversed by moving from the provisional queue to the final stack
                this.provisionalVertices.remove(this.currentVertex);
                this.shortestPath.add(this.currentVertex);

                // For all the adjacent vertices to the current one
                for (Edge edge : this.currentVertex.getEdges()){
                    this.nextAdjacentVertex = edge.getEndVertex();

                    // If the distance up to the current vertex from source plus the distance to the next vertex
                    // is less than the next adjacent vertex's own distance from the source
                    if ((this.currentVertex.getDistanceFromSource() + edge.getWeight()) < this.nextAdjacentVertex.getDistanceFromSource()){
                        // Update the next adjacent vertex's weight
                        this.nextAdjacentVertex.setDistanceFromSource(this.currentVertex.getDistanceFromSource() + edge.getWeight());
                    }
                }
            } else {
                this.provisionalVertices.remove(this.currentVertex);
                this.shortestPath.add(this.currentVertex);
            }

        }

        // Return the stack of shortest path nodes to the destination
        return new ShortestPath(this.shortestPath, startVertex, endVertex);

    }

    // Helper method to get the lowest distance from source node from the provisional queue
    private Vertex getClosestProvisionalVertexToSource(){

        if (!this.provisionalVertices.isEmpty()){
            // Default to first vertex
            Vertex closest = this.provisionalVertices.getFirst();

            // For each provisionally listed vertex
            for (Vertex vertex : this.provisionalVertices){
                // Update closest vertex to the closest one in the list to the source
                if (!this.shortestPath.contains(vertex)){
                    if (vertex.getDistanceFromSource() < closest.getDistanceFromSource()){
                        closest = vertex;
                    }
                }
                if (vertex.equals(this.endVertex)){
                    closest = this.endVertex;
                }
            }
            return closest;
        } else {
            return null;
        }
    }

}
