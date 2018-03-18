package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Graph;
import main.java.com.mquinn.graphing.Vertex;

import java.util.Arrays;
import java.util.List;

public class Network extends Graph implements INetwork {

    private int[][] adjacencyList;

    public Network(){

    }

    @Override
    public void printNetwork() {
        System.out.println("Number of Vertices: " + this.getNumVertices() + "\r");
        for (Vertex vertex : this.vertices){
            if (vertex instanceof Device){
                System.out.println("Device Name: " + ((Device) vertex).getDeviceName());
                List<Edge> edgeList = vertex.getEdges();
                for (Edge edge : edgeList){
                    if (edge instanceof Link){
                        System.out.println("Link Name -> " + ((Link) edge).getLinkName());
                        System.out.println("Destination -> " + ((Device)edge.getEndVertex()).getDeviceName());
                        System.out.println("Link Weight -> " + edge.getWeight());
                    }
                }
                System.out.println("\r");
            }
        }
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        calcAdjacencyMatrix();
        return this.adjacencyList;
    }

    @Override
    public void printAdjMatrix() {
        calcAdjacencyMatrix();
        System.out.println("Adjacency Matrix: ");
        for (int[] adjList : this.adjacencyList) {
            System.out.println(Arrays.toString(adjList));
        }
    }

    private void calcAdjacencyMatrix() {
        this.adjacencyList = new int[this.vertices.size()][this.vertices.size()];
        for (int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i) instanceof Device){
                System.out.println("Device: \r");
                System.out.println(((Device) this.vertices.get(i)).getDeviceName());
            }
            for (int j = 0; j < this.vertices.size(); j++){
                if(this.vertices.get(i) instanceof Device){
                    System.out.println("To: " + ((Device) this.vertices.get(j)).getDeviceName());
                }
                if (this.vertices.get(i).containsEdgeVertex("end", this.vertices.get(j))){
                    this.adjacencyList[i][j] = 1;
                } else {
                    this.adjacencyList[i][j] = 0;
                }
            }
        }
    }

}
