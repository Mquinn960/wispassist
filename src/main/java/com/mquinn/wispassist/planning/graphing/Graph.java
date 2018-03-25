package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph implements IGraph {

    public List<Vertex> vertices;
    protected int numVertices;
    Edge cheapestLink;

    @Override
    public boolean containsVertex(Vertex vertex) {
        return this.vertices.contains(vertex);
    }

    public Graph(){
        this.vertices = new ArrayList<>();
    }

    @Override
    public List<Vertex> getVertices() {
        return this.vertices;
    }

    @Override
    public Vertex getVertex(int listedVertex) {
        return vertices.get(listedVertex);
    }

    @Override
    public boolean addVertex(Vertex vertex) {
        if (!vertices.contains(vertex)){
            this.vertices.add(vertex);
            numVertices++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeVertex(Vertex vertex){
        if (vertices.contains(vertex)){
            this.vertices.remove(vertex);
            numVertices--;
            return true;
        }  else {
            return false;
        }
    }

    @Override
    public Edge getCheapestEdge() {
        double weight = Integer.MAX_VALUE;
        for (Vertex vertex : this.vertices){
            if (!vertex.getEdges().isEmpty()){
                for (Edge edge : vertex.getEdges()){
                    if (edge.getWeight() < weight){
                        this.cheapestLink = edge;
                        weight = this.cheapestLink.getWeight();
                    }
                }
            }
        }
        return this.cheapestLink;
    }

    @Override
    public int getNumVertices(){
        return this.numVertices;
    }

}
