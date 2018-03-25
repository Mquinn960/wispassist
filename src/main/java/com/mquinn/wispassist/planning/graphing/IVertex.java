package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.List;

public interface IVertex {

    List<Edge> getEdges();

    boolean addEdge(Edge edge);
    boolean removeEdge(Edge edge);

    void purge();

    boolean containsEdgeWithVertex(String position, Vertex vertex);

    double getDistanceFromSource();
    void setDistanceFromSource(double distanceFromSource);

}
