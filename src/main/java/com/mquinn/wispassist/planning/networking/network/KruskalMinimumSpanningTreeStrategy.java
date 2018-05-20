package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.link.GeolocationWeightStrategy;

/**
 * DEPRECATED
 *
 * Implementation for Kruskal's algorithm
 * Almost complete but necessity for disjoint set calculations
 * pushed implementation over time and this strategy was abandoned
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class KruskalMinimumSpanningTreeStrategy implements ISpanningTreeStrategy {

    private Network spanningTree;
    private Network inputNetwork;
    private Edge currentEdge;

    /**
     * Calculate a spanning tree of the input graph
     *
     * @param network
     * @param startvertex
     * @return
     */
    @Override
    public Network calculateSpanningTree(Network network, Vertex startvertex) {

        this.inputNetwork = network;
        this.inputNetwork.makeUndirected();

        PlanningService planningService = PlanningService.getInstance();
        this.spanningTree = planningService.getNetworkFactory().createNetwork("undirected");

        // While spanning tree does not contain all vertices from graph
        while (!this.spanningTree.vertices.containsAll(this.inputNetwork.vertices)){

            // Choose the cheapest link from input graph
            this.currentEdge = this.inputNetwork.getCheapestEdge();

            // If this cheapest link doesn't make a loop in the spanning tree
            if (!this.spanningTree.vertices.contains(this.currentEdge.getStartVertex()) && !this.spanningTree.vertices.contains(this.currentEdge.getEndVertex())){

                // Add the link and its vertices to spanning tree
                addLinkAndVertices();
            }
            // Remove link from input network
            removeLinkFromInputNetwork();
        }
        return this.spanningTree;
    }

    /**
     * Helper method to remove the link used up from input network
     */
    private void removeLinkFromInputNetwork(){
        // Remove the link from the input network
        // Avoid concurrent modification threading issue
        for (Vertex vertex: this.inputNetwork.vertices){
            if (vertex == this.currentEdge.getStartVertex()){
                vertex.removeEdgeWithDestination(this.currentEdge.getEndVertex());
            }
            vertex.removeEdge(this.currentEdge);
            if (vertex == this.currentEdge.getEndVertex()){
                vertex.removeEdgeWithDestination(this.currentEdge.getStartVertex());
            }
        }
    }

    /**
     * Add link and it's vertices to spanning tree
     */
    private void addLinkAndVertices(){
        if (!spanningTree.containsVertex(this.currentEdge.getStartVertex())){
            Vertex newStartVertex = this.currentEdge.getStartVertex();
            newStartVertex.purge();
            newStartVertex.addEdge(this.currentEdge);
            this.spanningTree.addVertex(newStartVertex);
        } else {
            for (Vertex vertex: this.spanningTree.vertices){
                if (vertex.equals(this.currentEdge.getStartVertex())){
                    if (!vertex.getEdges().contains(this.currentEdge)){
                        vertex.addEdge(this.currentEdge);
                    }
                }
            }
        }
        if (!spanningTree.containsVertex(this.currentEdge.getEndVertex())) {
            Vertex newEndVertex = this.currentEdge.getEndVertex();
            newEndVertex.purge();
            newEndVertex.addEdge(new Edge(this.currentEdge.getEndVertex(), this.currentEdge.getStartVertex(), new GeolocationWeightStrategy()) {});
            this.spanningTree.addVertex(newEndVertex);
        } else {
            for (Vertex vertex: this.spanningTree.vertices){
                if (vertex.equals(this.currentEdge.getEndVertex())){
                    vertex.addEdge(new Edge(this.currentEdge.getEndVertex(), this.currentEdge.getStartVertex(), new GeolocationWeightStrategy()) {});
                }
            }
        }
    }
}
