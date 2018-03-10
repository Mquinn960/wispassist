package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;

public class GeolocationWeight implements WeightStrategy {

    @Override
    public double getEdgeWeight(Edge edge) {
        // TODO: Take lat-long of start vertex and workout distance to lat-long of end vertex
        return 0;
        // this.startVertex - this.endVertex;
    }
}
