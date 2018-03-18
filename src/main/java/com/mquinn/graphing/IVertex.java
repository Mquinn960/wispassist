package main.java.com.mquinn.graphing;

import java.util.List;

public interface IVertex {

    List<Edge> getEdges();

    boolean addEdge(Edge edge);
    boolean removeEdge(Edge edge);
    boolean containsEdgeWithVertex(String position, Vertex vertex);
    Edge getEdgeWithLowestWeight();

}
