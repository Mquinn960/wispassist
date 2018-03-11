package main.java.com.mquinn.graphing;

import java.util.List;

public interface IGraph {

    int numVertices = 0;
    int numEdges = 0;

    List<Vertex> getVertices();

    void addVertex(Vertex vertex);
    void removeVertex(Vertex vertex);
    Vertex getVertex(int listedVertex);

    int getNumVertices();
    int getNumEdges();

}
