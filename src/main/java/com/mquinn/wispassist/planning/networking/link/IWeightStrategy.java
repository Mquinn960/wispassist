package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;

public interface IWeightStrategy {

    double calculateEdgeWeight(Edge edge);

}
