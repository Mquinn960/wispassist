package main.java.com.mquinn.wispassist.planning.graphing;

import java.util.ArrayList;
import java.util.List;

public abstract class Vertex implements IVertex {

    private List<Edge> edgeList;
    private double distanceFromSource;
    private Vertex previousVertex;
    private Edge edgeToRemove;

    public Vertex(){
        this.edgeList = new ArrayList<>();
        this.distanceFromSource = 0;
    }

    @Override
    public void purge() {
        this.edgeList.clear();
    }

    @Override
    public boolean addEdge(Edge newEdge){
        if (newEdge.startVertex == this && !edgeList.contains(newEdge)){
            this.edgeList.add(newEdge);
            return true;
        } else {
            return false;
        }
    }

    //TODO: Fix method, doesn't work
    @Override
    public void removeEdgeWithDestination(Vertex vertex) {

        ArrayList<Edge> edgesToDelete = new ArrayList<>();

        for (Edge edge: this.edgeList){
            if (edge.getEndVertex() == vertex){
                edgesToDelete.add(edge);
            }
        }

        for (Edge edge: edgesToDelete){
            if (this.edgeList.contains(edge)){
                this.edgeList.remove(edge);
            }
        }

    }

    @Override
    public boolean removeEdge(Edge oldEdge){
        if (oldEdge.startVertex == this && edgeList.contains(oldEdge)){
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

    @Override
    public boolean containsEdgeWithVertex(String position, Vertex vertex) {
        boolean foundVertex = false;
        if (position.equals("start")){
            for (Edge edge : this.edgeList){
                if (edge.getStartVertex() == vertex){
                    foundVertex = true;
                }
            }
        } else if (position.equals("end")){
            for (Edge edge : this.edgeList){
                if (edge.getEndVertex() == vertex){
                    foundVertex = true;
                }
            }
        }
        return foundVertex;
    }

    @Override
    public double getDistanceFromSource() {
        return distanceFromSource;
    }

    @Override
    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }
}
