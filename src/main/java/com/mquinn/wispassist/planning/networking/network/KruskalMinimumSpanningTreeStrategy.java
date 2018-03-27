package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.link.GeolocationWeightStrategy;

public class KruskalMinimumSpanningTreeStrategy implements ISpanningTreeStrategy {

    private Network spanningTree;
    private Network inputNetwork;
    private Edge currentEdge;

    @Override
    public Network calculateSpanningTree(Network network) {

        this.inputNetwork = network;
        this.inputNetwork.makeUndirected();

        PlanningService planningService = new PlanningService();
        this.spanningTree = planningService.getNetworkFactory().createNetwork("undirected");

        // While spanning tree does not contain all vertices from graph
        while (!this.spanningTree.vertices.containsAll(this.inputNetwork.vertices)){

            // TODO: fix graph method for this, not working
            // Choose the cheapest link from input graph
            this.currentEdge = this.inputNetwork.getCheapestEdge();

            // If this cheapest link doesn't make a loop in the spanning tree
            if (!this.spanningTree.vertices.contains(this.currentEdge.getStartVertex()) && !this.spanningTree.vertices.contains(this.currentEdge.getEndVertex())){

                // Add the link and its vertices to spanning tree
                addLinkAndVertices();
            }
            removeLinkFromInputNetwork();
        }
        return this.spanningTree;
    }

    private void removeLinkFromInputNetwork(){
        Vertex tempStartVertex = this.currentEdge.getStartVertex();
        Vertex tempEndVertex = this.currentEdge.getEndVertex();

        // Remove the link from the input network
        // Avoid concurrent modification threading issue
        for (Vertex vertex: this.inputNetwork.vertices){
            if (vertex == tempStartVertex){
                vertex.removeEdgeWithDestination(tempStartVertex);
            }
            if (vertex == tempEndVertex){
                vertex.removeEdgeWithDestination(tempEndVertex);
            }
        }
    }

    private void addLinkAndVertices(){
        Vertex newStartVertex = this.currentEdge.getStartVertex();
        Vertex newEndVertex = this.currentEdge.getEndVertex();

        newStartVertex.purge();
        newStartVertex.addEdge(this.currentEdge);

        newEndVertex.purge();
        newEndVertex.addEdge(new Edge(this.currentEdge.getEndVertex(), this.currentEdge.getStartVertex(), new GeolocationWeightStrategy()) {});

        this.spanningTree.addVertex(newStartVertex);
        this.spanningTree.addVertex(newEndVertex);
    }
}
