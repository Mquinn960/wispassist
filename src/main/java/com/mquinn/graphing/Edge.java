package main.java.com.mquinn.graphing;

import main.java.com.mquinn.wispassist.IWeightStrategy;

public abstract class Edge implements IEdge {

    protected Vertex startVertex;
    protected Vertex endVertex;
    protected IWeightStrategy IWeightStrategy;
    protected double weight;

    public Edge(Vertex startVertex, Vertex endVertex, IWeightStrategy IWeightStrategy){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.IWeightStrategy = IWeightStrategy;
        this.weight = IWeightStrategy.calculateEdgeWeight(this);
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
    public void setIWeightStrategy(IWeightStrategy IWeightStrategy) {
        this.IWeightStrategy = IWeightStrategy;
    }

    @Override
    public double getWeight() {
        return this.IWeightStrategy.calculateEdgeWeight(this);
    }

}
