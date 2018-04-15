package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.link.GeolocationWeightStrategy;

import java.util.Iterator;
import java.util.LinkedList;

public class DijkstraPathfindingStrategy implements IPathfindingStrategy {

    private LinkedList<Vertex> provisionalVertices = new LinkedList<>();
    private LinkedList<Vertex> dijkstraTree = new LinkedList<>();
    private LinkedList<Vertex> shortestPath = new LinkedList<>();
    private Vertex currentVertex = new Vertex(){};
    private Vertex nextAdjacentVertex = new Vertex(){};
    private Vertex endVertex = new Vertex(){};

    public DijkstraPathfindingStrategy(){
    }

    @Override
    public ShortestPath calculatePath(Network network, Vertex startVertex, Vertex endVertex) {

        this.endVertex = endVertex;

        // Set all input non source vertex weights to "infinite"
        // Set source weight to 0
        for (Vertex vertex : network.vertices){
            this.provisionalVertices.add(vertex);
            if (vertex.equals(startVertex)) {
                vertex.setDistanceFromSource(0);
            } else {
                vertex.setDistanceFromSource(Integer.MAX_VALUE);
            }
        }

        // While there are vertices to analyse and we have not already reached our destination
        while (!this.provisionalVertices.isEmpty() && !this.currentVertex.equals(this.endVertex) && !this.dijkstraTree.contains(this.endVertex)) {

            // Take the first provisional vertex with the lowest distance from source
            this.currentVertex = getClosestProvisionalVertexToSource();

            if (!this.currentVertex.equals(this.endVertex) && !this.nextAdjacentVertex.equals(this.endVertex)){
                // Mark this vertex as traversed by moving from the provisional queue to the final stack
                this.provisionalVertices.remove(this.currentVertex);
                this.dijkstraTree.add(this.currentVertex);

                // For all the adjacent vertices to the current one
                for (Edge edge : this.currentVertex.getEdges()){
                    this.nextAdjacentVertex = edge.getEndVertex();

                    // If the distance up to the current vertex from source plus the distance to the next vertex
                    // is less than the next adjacent vertex's own distance from the source
                    if ((this.currentVertex.getDistanceFromSource() + edge.getWeight()) < this.nextAdjacentVertex.getDistanceFromSource()){
                        // Update the next adjacent vertex's weight
                        this.nextAdjacentVertex.setDistanceFromSource(this.currentVertex.getDistanceFromSource() + edge.getWeight());
                        this.nextAdjacentVertex.setPreviousVertex(this.currentVertex);
                    }
                }
            } else {
                this.provisionalVertices.remove(this.currentVertex);
                this.dijkstraTree.add(this.currentVertex);
            }

        }

        // Return the stack of shortest path nodes to the destination
        return assemblePath(this.dijkstraTree.getLast());

    }

    private ShortestPath assemblePath(Vertex destination){
        this.shortestPath.add(destination);
        addPreviousVertex(destination);
        this.shortestPath = reversePath(this.shortestPath);
        return new ShortestPath(this.shortestPath);
    }

    private void addPreviousVertex(Vertex vertex){
        this.shortestPath.add(vertex.getPreviousVertex());
        if (vertex.getPreviousVertex() != null){
            addPreviousVertex(vertex.getPreviousVertex());
        }
    }

    private LinkedList<Vertex> reversePath(LinkedList<Vertex> shortestPath){
        LinkedList<Vertex> newShortestPath = new LinkedList<Vertex>(){};

        Iterator<Vertex> pathWalker = this.shortestPath.descendingIterator();

        while (pathWalker.hasNext()){
            newShortestPath.add(pathWalker.next());
        }

        return newShortestPath;
    }

    // Helper method to get the lowest distance from source node from the provisional queue
    private Vertex getClosestProvisionalVertexToSource(){

        if (!this.provisionalVertices.isEmpty()){
            // Default to first vertex
            Vertex closest = this.provisionalVertices.getFirst();

            // For each provisionally listed vertex
            for (Vertex vertex : this.provisionalVertices){
                // Update closest vertex to the closest one in the list to the source
                if (!this.dijkstraTree.contains(vertex)) {
                    // Jump out early if the destination is found
                    if (vertex.equals(endVertex) && vertex.getDistanceFromSource() != Integer.MAX_VALUE) {
                        return vertex;
                    } else {
                        // Else incrementally update the closest vertex
                        if (vertex.getDistanceFromSource() < closest.getDistanceFromSource()) {
                            closest = vertex;
                        }
                    }
                }
            }
            return closest;
        } else {
            return null;
        }
    }

}
