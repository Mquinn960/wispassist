package main.java.com.mquinn.graphing;

import java.util.ArrayList;
import java.util.List;

public abstract class Vertex implements IVertex {

    protected List<Edge> edgeList;

    public Vertex(){
        this.edgeList = new ArrayList<>();
    }

    @Override
    public boolean addEdge(Edge newEdge){
        if (newEdge.startVertex == this){
            this.edgeList.add(newEdge);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeEdge(Edge oldEdge){
        if (oldEdge.startVertex == this){
            this.edgeList.remove(oldEdge);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Edge> getEdges() {
        return edgeList;
    }

}
