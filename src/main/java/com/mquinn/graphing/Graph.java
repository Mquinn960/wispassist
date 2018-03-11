package main.java.com.mquinn.graphing;

import java.util.List;

public abstract class Graph implements IGraph {

    protected List<Vertex> vertices;
    protected int numVertices;
    protected int numEdges;

    @Override
    public List<Vertex> getVertices() {
        return this.vertices;
    }

    @Override
    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
        numVertices++;
    }

    @Override
    public void removeVertex(Vertex vertex){
        this.vertices.remove(vertex);
        numVertices--;
    }

    @Override
    public int getNumVertices(){
        return this.numVertices;
    }

    @Override
    public int getNumEdges(){
        return this.numEdges;
    }

}
