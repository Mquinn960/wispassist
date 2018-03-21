package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

public class KruskalMinimumSpanningTreeStrategy implements ISpanningTreeStrategy {

    private Network spanningTree;
    private Network inputNetwork;
    private Edge currentEdge;

    @Override
    public Network calculateSpanningTree(Network network) {

        this.inputNetwork = network;
        this.spanningTree = new Network(new DigraphAdjacencyMatrixStrategy(), new DijkstraPathfindingStrategy(), new KruskalMinimumSpanningTreeStrategy());

        while (!this.spanningTree.vertices.containsAll(this.inputNetwork.vertices)){
            // TODO: fix nullpointer here
            this.currentEdge = this.inputNetwork.getCheapestLink();
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
