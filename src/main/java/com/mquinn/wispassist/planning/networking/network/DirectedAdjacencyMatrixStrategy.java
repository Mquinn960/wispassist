package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.networking.device.Device;

/**
 * Adjacency Matrix calculation/printing strategy
 * For directed graphs
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class DirectedAdjacencyMatrixStrategy implements IAdjacencyMatrixStrategy {

    /**
     * Calculate adjacency matrix and optionally print steps for a
     * directed graph
     *
     * @param network
     * @param printSteps
     * @return
     */
    @Override
    public int[][] calcAdjacencyMatrix(Network network, boolean printSteps) {
        int[][] adjacencyMatrix = new int[network.vertices.size()][network.vertices.size()];
        for (int i = 0; i < network.vertices.size(); i++){
            if(printSteps){
                if(network.vertices.get(i) instanceof Device){
                    System.out.println("Device: \r");
                    System.out.println(((Device) network.vertices.get(i)).getDeviceName());
                }
            }
            for (int j = 0; j < network.vertices.size(); j++){
                if (printSteps){
                    if(network.vertices.get(i) instanceof Device){
                        System.out.println("To: " + ((Device) network.vertices.get(j)).getDeviceName() + "\r");
                    }
                }
                if (network.vertices.get(i).containsEdgeWithVertex("end", network.vertices.get(j))){
                    if (printSteps){
                        System.out.println("--> Connected");
                    }
                    adjacencyMatrix[i][j] = 1;
                } else {
                    if (printSteps){
                        System.out.println("--> Not Connected");
                    }
                    adjacencyMatrix[i][j] = 0;
                }
            }
        }
        return adjacencyMatrix;
    }

}
