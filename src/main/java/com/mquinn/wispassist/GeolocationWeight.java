package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

/**
 * Uses the Haversine formula (great circles method) to calculate
 * the distance between the vertices of an input edge.
 *
 * More info on this formula here:
 * https://www.movable-type.co.uk/scripts/latlong.html
 *
 * @author  Matthew Quinn
 * @version %I%, %G%
 * @since   1.0
 */

public class GeolocationWeight implements WeightStrategy {

    private final static double EARTH_RADIUS_AVG_KM = 6371;

    /**
     * This method takes an edge as argument, and then uses Haversine formula
     * (great circles method) to calculate the distance between the edge's
     * vertices.
     *
     * @param   edge    the edge to have its weight calculated
     * @return  <code>double</code> the distance between edge vertices in KM; otherwise returns 0
     * @see     Edge
     * @see     Vertex
     * @see     WeightStrategy
     */

    @Override
    public double calculateEdgeWeight(Edge edge) {
        if (edge.getStartVertex() instanceof Device && edge.getEndVertex() instanceof Device){

            double startLat = ((Device) edge.getStartVertex()).getLatitude();
            double startLong = ((Device) edge.getStartVertex()).getLongitude();
            double endLat = ((Device) edge.getEndVertex()).getLatitude();
            double endLong = ((Device) edge.getEndVertex()).getLongitude();

            double latDistance = Math.toRadians(startLat - endLat);
            double longDistance = Math.toRadians(startLong - endLong);

            double haversine = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                    Math.cos(Math.toRadians(startLat)) * Math.cos(Math.toRadians(endLat)) *
                    Math.sin(longDistance / 2) * Math.sin(longDistance / 2);

            double angularDistRads = 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));

            return (int) Math.round(EARTH_RADIUS_AVG_KM * angularDistRads);
        } else {
            return 0;
        }
    }

}
