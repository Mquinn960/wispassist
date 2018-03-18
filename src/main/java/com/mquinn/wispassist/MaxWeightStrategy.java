package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;

public class MaxWeightStrategy implements IWeightStrategy {

    @Override
    public double calculateEdgeWeight(Edge edge) {
        return Integer.MAX_VALUE;
    }
}
