package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;

public interface WeightStrategy {

    double getEdgeWeight(Edge edge);

}