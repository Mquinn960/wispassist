package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex abstract class defines graphing behaviour for network nodes
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public abstract class Vertex implements IVertex {

    private List<Edge> edgeList;
    private double distanceFromSource;
    private Vertex previousVertex;
    private boolean edgefound = false;

    /**
     * Constructor initialises list of edges and sets up helper parameters for
     * Dijkstra's algorithm (weighting from source)
     */
    public Vertex(){
        this.edgeList = new ArrayList<>();
        this.distanceFromSource = 0;
    }

    /**
     * Remove all edges from this vertex
     */
    @Override
    public void purge() {
        this.edgeList.clear();
    }

    /**
     * Add an input edge to this vertex
     *
     * @param newEdge
     * @return
     */
    @Override
    public boolean addEdge(Edge newEdge){
        this.edgefound = false;
        if (newEdge.getStartVertex() == this && !this.edgeList.contains(newEdge)){
            // if start and end vertices arent in edgelist
            for (Edge edge: this.edgeList){
                if (edge.getStartVertex() == newEdge.getStartVertex() && edge.getEndVertex() == newEdge.getEndVertex()) {
                    this.edgefound = true;
                }
            }
            if (!this.edgefound){
                this.edgeList.add(newEdge);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Remove any edges on this graph which end at the input vertex destination
     *
     * @param vertex
     */
    @Override
    public void removeEdgeWithDestination(Vertex vertex) {

        ArrayList<Edge> edgesToDelete = new ArrayList<>();

        for (Edge edge: this.edgeList){
            if (edge.getEndVertex() == vertex){
                edgesToDelete.add(edge);
            }
        }

        for (Edge edge: edgesToDelete){
            if (this.edgeList.contains(edge)){
                this.edgeList.remove(edge);
            }
        }

    }

    /**
     * Matches an input edge against the list of edges held and removes matching entries
     *
     * @param oldEdge
     * @return
     */
    @Override
    public boolean removeEdge(Edge oldEdge){
        if (oldEdge.startVertex == this && edgeList.contains(oldEdge)){
            this.edgeList.remove(oldEdge);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get list of edges against this vertex
     *
     * @return
     */
    @Override
    public List<Edge> getEdges() {
        return edgeList;
    }

    /**
     * Returns true if this vertex has an edge that starts or ends in an input vertex
     * depending on first parameter value
     *
     * @param position
     * @param vertex
     * @return
     */
    @Override
    public boolean containsEdgeWithVertex(String position, Vertex vertex) {
        boolean foundVertex = false;
        if (position.equals("start")){
            for (Edge edge : this.edgeList){
                if (edge.getStartVertex() == vertex){
                    foundVertex = true;
                }
            }
        } else if (position.equals("end")){
            for (Edge edge : this.edgeList){
                if (edge.getEndVertex() == vertex){
                    foundVertex = true;
                }
            }
        }
        return foundVertex;
    }

    /**
     * Helper method for Dijkstra's algorithm, get distance from network source
     *
     * @return
     */
    @Override
    public double getDistanceFromSource() {
        return distanceFromSource;
    }

    /**
     * Helper method for Dijkstra's algorithm, set distance from network source
     *
     * @param distanceFromSource
     */
    @Override
    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    /**
     * Helper method for Dijkstra's algorithm, get previous vertex in path
     *
     * @return
     */
    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    /**
     * Helper method for Dijkstra's algorithm, set previous vertex in path
     *
     * @param previousVertex
     */
    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }
}
