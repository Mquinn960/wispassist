package main.java.com.mquinn.wispassist.planning.graphing;

import main.java.com.mquinn.wispassist.planning.networking.link.GeolocationWeightStrategy;
import main.java.com.mquinn.wispassist.planning.networking.link.IWeightStrategy;

public interface IEdge {

    IWeightStrategy WEIGHTSTRATEGY = new GeolocationWeightStrategy();

    void setStartVertex(Vertex vertex);
    Vertex getStartVertex();

    void setEndVertex(Vertex vertex);
    Vertex getEndVertex();

    void replaceVertex(Vertex oldVertex, Vertex newVertex);

    void setIWeightStrategy(IWeightStrategy IWeightStrategy);

    double getWeight();
    void setWeightStatic(double weight);
    double getWeightStatic();

}
