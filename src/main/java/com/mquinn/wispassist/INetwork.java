package main.java.com.mquinn.wispassist;

public interface INetwork {

    void printNetwork();
    void printAdjMatrix(boolean printSteps);
    int[][] getAdjacencyMatrix(boolean printSteps);
    void setAdjacencyMatrixStrategy(AdjacencyMatrixStrategy adjMatrixStrategy);

}
