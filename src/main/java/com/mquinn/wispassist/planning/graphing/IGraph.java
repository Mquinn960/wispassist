package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.List;

public interface IGraph {

    int numVertices = 0;
    List<Vertex> getVertices();

    boolean addVertex(Vertex vertex);
    boolean removeVertex(Vertex vertex);
    boolean containsVertex(Vertex vertex);
    Vertex getVertex(int listedVertex);

    Edge getCheapestEdge();

    int getNumVertices();

}
