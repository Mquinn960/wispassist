package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

public interface ISpanningTreeStrategy {

    Network calculateSpanningTree(Network network, Vertex startVertex);

}
