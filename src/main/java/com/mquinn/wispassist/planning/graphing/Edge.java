package main.java.com.mquinn.wispassist.planning.graphing;

import main.java.com.mquinn.wispassist.planning.networking.link.IWeightStrategy;

/**
 * Abstract class for modelling graph edges
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public abstract class Edge implements IEdge {

    protected Vertex startVertex;
    protected Vertex endVertex;
    protected IWeightStrategy IWeightStrategy;
    protected double weight;

    /**
     * Constructor sets the start and endpoints of the edge
     * as well as the weighting strategy
     *
     * @param startVertex
     * @param endVertex
     * @param IWeightStrategy
     */
    public Edge(Vertex startVertex, Vertex endVertex, IWeightStrategy IWeightStrategy){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.IWeightStrategy = IWeightStrategy;
        this.weight = IWeightStrategy.calculateEdgeWeight(this);
    }

    /**
     * Sets start vertex of this edge
     *
     * @param vertex
     */
    @Override
    public void setStartVertex(Vertex vertex) {
        this.startVertex = vertex;
    }

    /**
     * Gets start vertex of this edge
     *
     * @return
     */
    @Override
    public Vertex getStartVertex() {
        return this.startVertex;
    }

    /**
     * Sets end vertex of this edge
     *
     * @param vertex
     */
    @Override
    public void setEndVertex(Vertex vertex) {
        this.endVertex = vertex;
    }

    /**
     * Gets end vertex of this edge
     *
     * @return
     */
    @Override
    public Vertex getEndVertex() {
        return this.endVertex;
    }

    /**
     * Switch out an old vertex with a new replacement on either end
     *
     * @param oldVertex
     * @param newVertex
     */
    @Override
    public void replaceVertex(Vertex oldVertex, Vertex newVertex) {
        if (this.startVertex == oldVertex){
            this.startVertex = newVertex;
        } else if (this.endVertex == oldVertex){
            this.endVertex = newVertex;
        }
    }

    /**
     * Set the weighting strategy for this edge
     *
     * @param IWeightStrategy
     */
    @Override
    public void setIWeightStrategy(IWeightStrategy IWeightStrategy) {
        this.IWeightStrategy = IWeightStrategy;
    }

    /**
     * Override strategy and specify a manual weight
     *
     * @param weight
     */
    @Override
    public void setWeightStatic(double weight) {
        this.weight = weight;
    }

    /**
     * Get manually defined weight
     *
     * @return
     */
    @Override
    public double getWeightStatic() {
        return this.weight;
    }

    /**
     * Get weight using weighting strategy
     *
     * @return
     */
    @Override
    public double getWeight() {
        return this.IWeightStrategy.calculateEdgeWeight(this);
    }

}
