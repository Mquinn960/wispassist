package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Prim's algorithm for finding
 * minimum spanning trees of graphs
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class PrimMinimumSpanningTreeStrategy implements ISpanningTreeStrategy {

    private Network spanningTree;

    private Edge closestEdge;

    /**
     * Calculate the minimum network configuration and return the
     * spanning tree as a network object
     *
     * @param network
     * @param initialVertex
     * @return
     */
    @Override
    public Network calculateSpanningTree(Network network, Vertex initialVertex) {
        spanningTree = PlanningService.getInstance().getNetworkFactory().createNetwork("undirected");

        // Add the first edge's vertex first
        Edge firstEdge = getClosestEdge(initialVertex.getEdges(), true);
        addEdgeToSpanningTree(firstEdge, true);

        // Compile list of all graph edges
        List<Edge> edgeSet = new ArrayList<>();
        for (Vertex vertex: network.vertices){
            edgeSet.addAll(vertex.getEdges());
        }

        // Loop while graph incomplete
        while (!spanningTree.vertices.containsAll(network.vertices)){
            // Get the nearest external vertex
            Edge closestEdge = getClosestEdge(edgeSet, false);

            // Add it to the spanning tree
            addEdgeToSpanningTree(closestEdge, false);
        }

        // Make undirected graph and return this
        spanningTree.makeUndirected();
        return spanningTree;

    }

    /**
     * Return the first edge with the lowest weight which
     * has an external endpoint and an internal origin
     *
     * @param edges
     * @param first
     * @return
     */
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

    /**
     * Add the edge found to the minimum spanning tree
     * if it meets the criteria
     *
     * @param edge
     * @param first
     */
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
