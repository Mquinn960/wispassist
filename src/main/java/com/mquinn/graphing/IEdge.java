package main.java.com.mquinn.graphing;

import main.java.com.mquinn.wispassist.WeightStrategy;

public interface IEdge {

    void setStartVertex(Vertex vertex);
    Vertex getStartVertex();

    void setEndVertex(Vertex vertex);
    Vertex getEndVertex();

    void replaceVertex(Vertex oldVertex, Vertex newVertex);

    void setWeightStrategy(WeightStrategy weightStrategy);

    double getWeight();

}
