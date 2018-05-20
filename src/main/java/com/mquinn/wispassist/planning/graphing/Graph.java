package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for modelling graph implementation
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public abstract class Graph implements IGraph {

    public List<Vertex> vertices;
    protected int numVertices;
    Edge cheapestLink;

    /**
     * Constructor initialises the list of vertices
     */
    public Graph(){
        this.vertices = new ArrayList<>();
    }

    /**
     * Returns true if this graph contains the input vertex
     *
     * @param vertex
     * @return
     */
    @Override
    public boolean containsVertex(Vertex vertex) {
        return this.vertices.contains(vertex);
    }

    /**
     * Receive the list of vertices in this graph
     *
     * @return
     */
    @Override
    public List<Vertex> getVertices() {
        return this.vertices;
    }

    /**
     * Get a single vertex from the graph using an int identifier
     *
     * @param listedVertex
     * @return
     */
    @Override
    public Vertex getVertex(int listedVertex) {
        return vertices.get(listedVertex);
    }

    /**
     * Add an input vertex to this graph
     *
     * @param vertex
     * @return
     */
    @Override
    public boolean addVertex(Vertex vertex) {
        if (!vertices.contains(vertex)){
            this.vertices.add(vertex);
            numVertices++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a vertex from the graph using an input to compare
     *
     * @param vertex
     * @return
     */
    @Override
    public boolean removeVertex(Vertex vertex){
        if (vertices.contains(vertex)){
            this.vertices.remove(vertex);
            numVertices--;
            return true;
        }  else {
            return false;
        }
    }

    /**
     * Get the cheapest edge the graph contains
     *
     * @return
     */
    @Override
    public Edge getCheapestEdge() {
        double weight = Integer.MAX_VALUE;
        for (Vertex vertex : this.vertices){
            if (!vertex.getEdges().isEmpty()){
                for (Edge edge : vertex.getEdges()){
                    if (edge.getWeight() < weight){
                        this.cheapestLink = edge;
                        weight = this.cheapestLink.getWeight();
                    }
                }
            }
        }
        return this.cheapestLink;
    }

    /**
     * Get the number of vertices contained in the graph
     *
     * @return
     */
    @Override
    public int getNumVertices(){
        return this.numVertices;
    }

}
