package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.List;

/**
 * Interface determines what methods an implementation of vertex must implement
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public interface IVertex {

    List<Edge> getEdges();

    boolean addEdge(Edge edge);
    boolean removeEdge(Edge edge);
    void removeEdgeWithDestination(Vertex vertex);

    void purge();

    boolean containsEdgeWithVertex(String position, Vertex vertex);

    double getDistanceFromSource();
    void setDistanceFromSource(double distanceFromSource);

}
