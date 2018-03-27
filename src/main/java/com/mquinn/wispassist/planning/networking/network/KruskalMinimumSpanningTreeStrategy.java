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
        PlanningService planningService = new PlanningService();
        this.spanningTree = planningService.getNetworkFactory().createNetwork("undirected");

        for (Vertex vertex : this.inputNetwork.vertices) {
            for (Edge edge : vertex.getEdges()) {
                edge.getEndVertex().addEdge(new Edge(edge.getEndVertex(), edge.getStartVertex(), new GeolocationWeightStrategy()) {
                });
            }
        }

        while (!this.spanningTree.vertices.containsAll(this.inputNetwork.vertices)){
            this.currentEdge = this.inputNetwork.getCheapestEdge();
            if (!this.spanningTree.vertices.contains(this.currentEdge.getStartVertex()) && !this.spanningTree.vertices.contains(this.currentEdge.getEndVertex())){
                if (!this.spanningTree.vertices.contains(this.currentEdge.getStartVertex())){
                    Vertex newVertex = this.currentEdge.getStartVertex();
                    newVertex.purge();
                    newVertex.addEdge(this.currentEdge);
                    this.spanningTree.addVertex(newVertex);
                } else {
                    for (Vertex vertex: this.spanningTree.vertices){
                        if (vertex.equals(this.currentEdge.getStartVertex())){
                            vertex.addEdge(this.currentEdge);
                        }
                    }
                }
                if (!this.spanningTree.vertices.contains(this.currentEdge.getEndVertex())){
                    Vertex newVertex = this.currentEdge.getEndVertex();
                    newVertex.purge();
                    this.spanningTree.addVertex(newVertex);
                }
            }
            this.currentEdge.getStartVertex().removeEdge(this.currentEdge);
        }
        return this.spanningTree;
    }

}
