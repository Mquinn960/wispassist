package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

public class MinimumCostArboresenceMinimumSpanningTreeStrategy implements ISpanningTreeStrategy {

    // Edmonds' algorithm
    @Override
    public Network calculateSpanningTree(Network network, Vertex startVertex) {
        // Pick an entry node

        // Go through nodes and modify incoming weights
        // Maybe store modified weight against an edge private
        // Take smallest incoming edge and reduce to 0
        // reduce other incoming edges by same amount

        // While spanning tree does not contain all nodes from network
        // If it does not create a loop
        // i.e if the destination node is not already in the spanning tree
        // Add the node and the link to spanning tree

        // Test arboresence to ensure it contains all nodes
        // If it doesn't, pick another source node
        // Repeat until arboresence is found

        return null;
    }
}
