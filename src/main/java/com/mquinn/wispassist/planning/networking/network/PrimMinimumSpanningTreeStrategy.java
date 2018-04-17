package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

public class PrimMinimumSpanningTreeStrategy implements ISpanningTreeStrategy {

    private Network spanningTree;
    private Network inputNetwork;

    private Vertex closestVertex;
    private Vertex startVertex;

    @Override
    public Network calculateSpanningTree(Network network, Vertex initialVertex) {
        inputNetwork = network;
        startVertex = initialVertex;
        spanningTree = PlanningService.getInstance().getNetworkFactory().createNetwork("undirected");

        spanningTree.addVertex(initialVertex);

        Vertex closestVertex = getClosestVertex();



//
//        this.endVertex = endVertex;
//
//        // Set all input non source vertex weights to "infinite"
//        // Set source weight to 0
//        for (Vertex vertex : network.vertices){
//            this.provisionalVertices.add(vertex);
//            if (vertex.equals(startVertex)) {
//                vertex.setDistanceFromSource(0);
//            } else {
//                vertex.setDistanceFromSource(Integer.MAX_VALUE);
//            }
//        }
//
//        // While there are vertices to analyse and we have not already reached our destination
//        while (!this.provisionalVertices.isEmpty() && !this.currentVertex.equals(this.endVertex) && !this.dijkstraTree.contains(this.endVertex)) {
//
//            // Take the first provisional vertex with the lowest distance from source
//            this.currentVertex = getClosestProvisionalVertexToSource();
//
//            if (!this.currentVertex.equals(this.endVertex) && !this.nextAdjacentVertex.equals(this.endVertex)){
//                // Mark this vertex as traversed by moving from the provisional queue to the final stack
//                this.provisionalVertices.remove(this.currentVertex);
//                this.dijkstraTree.add(this.currentVertex);
//
//                // For all the adjacent vertices to the current one
//                for (Edge edge : this.currentVertex.getEdges()){
//                    this.nextAdjacentVertex = edge.getEndVertex();
//
//                    // If the distance up to the current vertex from source plus the distance to the next vertex
//                    // is less than the next adjacent vertex's own distance from the source
//                    if ((this.currentVertex.getDistanceFromSource() + edge.getWeight()) < this.nextAdjacentVertex.getDistanceFromSource()){
//                        // Update the next adjacent vertex's weight
//                        this.nextAdjacentVertex.setDistanceFromSource(this.currentVertex.getDistanceFromSource() + edge.getWeight());
//                        this.nextAdjacentVertex.setPreviousVertex(this.currentVertex);
//                    }
//                }
//            } else {
//                this.provisionalVertices.remove(this.currentVertex);
//                this.dijkstraTree.add(this.currentVertex);
//            }
//
//        }
//
//        // Return the stack of shortest path nodes to the destination
//        return this.spanningTree;

        return spanningTree;

    }

    private Vertex getClosestVertex(){

        for (Vertex vertex: inputNetwork.vertices){
            for (Edge edge: vertex.getEdges()){
                if (!spanningTree.vertices.contains(edge.getEndVertex())){
                    closestVertex = edge.getEndVertex();
                }
            }
        }

        return closestVertex;
    }

}
