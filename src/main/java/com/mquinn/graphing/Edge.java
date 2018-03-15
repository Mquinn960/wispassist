package main.java.com.mquinn.graphing;

import main.java.com.mquinn.wispassist.WeightStrategy;

public abstract class Edge implements IEdge {

    protected Vertex startVertex;
    protected Vertex endVertex;
    protected WeightStrategy weightStrategy;
    protected double weight;

    public Edge(Vertex startVertex, Vertex endVertex, WeightStrategy weightStrategy){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weightStrategy = weightStrategy;
        this.weight = weightStrategy.calculateEdgeWeight(this);
    }

    @Override
    public void setStartVertex(Vertex vertex) {
        this.startVertex = vertex;
    }

    @Override
    public Vertex getStartVertex() {
        return this.startVertex;
    }

    @Override
    public void setEndVertex(Vertex vertex) {
        this.endVertex = vertex;
    }

    @Override
    public Vertex getEndVertex() {
        return this.endVertex;
    }

    @Override
    public void replaceVertex(Vertex oldVertex, Vertex newVertex) {
        if (this.startVertex == oldVertex){
            this.startVertex = newVertex;
        } else if (this.endVertex == oldVertex){
            this.endVertex = newVertex;
        }
    }

    @Override
    public void setWeightStrategy (WeightStrategy weightStrategy) {
        this.weightStrategy = weightStrategy;
    }

    @Override
    public double getWeight() {
        return this.weightStrategy.calculateEdgeWeight(this);
    }

}
