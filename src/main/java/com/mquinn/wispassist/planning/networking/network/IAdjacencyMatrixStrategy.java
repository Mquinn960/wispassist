package main.java.com.mquinn.wispassist.planning.networking.network;

public interface IAdjacencyMatrixStrategy {

    /**
     * Interface for adjacency matrix calculator classes
     *
     * @param network
     * @param print
     * @return
     */
    int[][] calcAdjacencyMatrix(Network network, boolean print);

}
