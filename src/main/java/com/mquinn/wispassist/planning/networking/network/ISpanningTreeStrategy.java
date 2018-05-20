package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

/**
 * Interface for spanning tree calculation classes
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public interface ISpanningTreeStrategy {

    Network calculateSpanningTree(Network network, Vertex startVertex);

}
