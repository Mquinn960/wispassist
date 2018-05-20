package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;

/**
 * Main interface for link weighting strategies
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public interface IWeightStrategy {

    double calculateEdgeWeight(Edge edge);

}
