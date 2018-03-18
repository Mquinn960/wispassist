package main.java.com.mquinn.wispassist;

public class GraphAdjacencyMatrixStrategy implements IAdjacencyMatrixStrategy {

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
                if (network.vertices.get(i).containsEdgeWithVertex("end", network.vertices.get(j))
                        || network.vertices.get(j).containsEdgeWithVertex("end", network.vertices.get(i))){
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
