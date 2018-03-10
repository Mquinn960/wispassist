package main.java.com.mquinn.graphing;

import java.util.List;

public interface IGraph {

    List<Vertex> getVertices();

    void addVertex(Vertex vertex);
    void removeVertex(Vertex vertex);

    int getNumVertices();
    int getNumEdges();

}
