package main.java.com.mquinn.graphing;

import java.util.ArrayList;
import java.util.List;

public abstract class Vertex implements IVertex {

    private List<Edge> edgeList;
    private double distanceFromSource;

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
    public Edge getEdgeWithLowestWeight() {
        Edge resultEdge = this.getEdges().get(0);
        for (Edge edge : this.getEdges()){
            if (edge.getWeight() < resultEdge.getWeight() ){
                resultEdge = edge;
            }
        }
        return resultEdge;
    }

    @Override
    public double getDistanceFromSource() {
        return distanceFromSource;
    }

    @Override
    public void setDistanceFromSource(double distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }
}
