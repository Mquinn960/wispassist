package main.java.com.mquinn.graphing;

import main.java.com.mquinn.wispassist.GeolocationWeightStrategy;
import main.java.com.mquinn.wispassist.IWeightStrategy;

public interface IEdge {

    IWeightStrategy WEIGHTSTRATEGY = new GeolocationWeightStrategy();

    void setStartVertex(Vertex vertex);
    Vertex getStartVertex();

    void setEndVertex(Vertex vertex);
    Vertex getEndVertex();

    void replaceVertex(Vertex oldVertex, Vertex newVertex);

    void setIWeightStrategy(IWeightStrategy IWeightStrategy);

    double getWeight();
    void setWeight(double weight);

}
