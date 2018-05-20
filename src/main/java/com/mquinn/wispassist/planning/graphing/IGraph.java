package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.List;

/**
 * Interface determines which methods a graph implementation must implement
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
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
