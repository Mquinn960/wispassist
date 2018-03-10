package main.java.com.mquinn.graphing;

import java.util.List;

public abstract class Vertex implements IVertex {

    private int id;
    protected List<Edge> edgeList;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int newId) {
        this.id = newId;
    }

    @Override
    public void addEdge(Edge newEdge){
        this.edgeList.add(newEdge);
    }

    @Override
    public void removeEdge(Edge oldEdge){
        this.edgeList.remove(oldEdge);
    }

    @Override
    public List<Edge> getEdges() {
        return edgeList;
    }
}
