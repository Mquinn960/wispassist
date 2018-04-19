package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

import java.util.ArrayList;
import java.util.List;

public class PrimMinimumSpanningTreeStrategy implements ISpanningTreeStrategy {

    private Network spanningTree;

    private Edge closestEdge;

    @Override
    public Network calculateSpanningTree(Network network, Vertex initialVertex) {
        spanningTree = PlanningService.getInstance().getNetworkFactory().createNetwork("undirected");

        Edge firstEdge = getClosestEdge(initialVertex.getEdges(), true);
        addEdgeToSpanningTree(firstEdge, true);

        List<Edge> edgeSet = new ArrayList<>();
        for (Vertex vertex: network.vertices){
            edgeSet.addAll(vertex.getEdges());
        }

        while (!spanningTree.vertices.containsAll(network.vertices)){
            Edge closestEdge = getClosestEdge(edgeSet, false);

            addEdgeToSpanningTree(closestEdge, false);
            //edgeSet.remove(closestEdge);
        }

        spanningTree.makeUndirected();
        return spanningTree;

    }

    private Edge getClosestEdge(List<Edge> edges, boolean first){

        if (first){
            double testWeight = Integer.MAX_VALUE;

            for (Edge edge: edges){
                if (!spanningTree.vertices.contains(edge.getEndVertex())){
                    if (edge.getWeight() < testWeight){
                        testWeight = edge.getWeight();
                        closestEdge = edge;
                    }
                }
            }
        } else {
            double testWeight = Integer.MAX_VALUE;

            for (Edge edge: edges){
                if (!spanningTree.vertices.contains(edge.getEndVertex()) && spanningTree.vertices.contains(edge.getStartVertex())){
                    if (edge.getWeight() < testWeight){
                        testWeight = edge.getWeight();
                        closestEdge = edge;
                    }
                }
            }
        }

        return closestEdge;
    }

    private void addEdgeToSpanningTree(Edge edge, boolean first){
        if (first){
            if (spanningTree.containsVertex(edge.getStartVertex())){
                for (Vertex vertex: spanningTree.vertices){
                    if (vertex == edge.getStartVertex()){
                        vertex.addEdge(edge);
                    }
                }
            } else {
                spanningTree.addVertex(edge.getStartVertex());
                edge.getStartVertex().addEdge(edge);
            }
            edge.getEndVertex().purge();
            spanningTree.addVertex(edge.getEndVertex());
        } else {
            if (spanningTree.containsVertex(edge.getStartVertex())){
                for (Vertex vertex: spanningTree.vertices){
                    if (vertex == edge.getStartVertex()){
                        vertex.addEdge(edge);
                    }
                }
            } else {
                edge.getStartVertex().purge();
                spanningTree.addVertex(edge.getStartVertex());
                edge.getStartVertex().addEdge(edge);
            }
            edge.getEndVertex().purge();
            spanningTree.addVertex(edge.getEndVertex());
        }
    }

}
