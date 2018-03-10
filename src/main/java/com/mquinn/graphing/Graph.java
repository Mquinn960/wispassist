package main.java.com.mquinn.graphing;

import java.util.List;

public abstract class Graph implements IGraph {

    protected List<Vertex> vertices;
    protected int numVertices;
    protected int numEdges;

    public Graph(){

    }

    public List<Vertex> getVertices() {
        return this.vertices;
    }

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
        numVertices++;
    }

    public void removeVertex(Vertex vertex){
        this.vertices.remove(vertex);
        numVertices--;
    }

    public int getNumVertices(){
        return this.numVertices;
    }

    public int getNumEdges(){
        return this.numEdges;
    }

}
