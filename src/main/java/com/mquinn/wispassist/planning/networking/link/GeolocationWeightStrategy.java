package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Uses the Haversine formula (great circles method) to calculate
 * the distance between the vertices of an input edge.
 *
 * More info on this formula here:
 * https://www.movable-type.co.uk/scripts/latlong.html
 *
 * @author  Matthew Quinn
 * @since   1.0
 */

public class GeolocationWeightStrategy implements IWeightStrategy {

    private final static double EARTH_RADIUS_AVG_KM = 6371;

    /**
     * This method takes an edge as argument, and then uses Haversine formula
     * (great circles method) to calculate the distance between the edge's
     * vertices.
     *
     * @param   edge    the edge to have its weight calculated
     * @return  double  the distance between edge vertices in KM; otherwise returns 0
     * @see     Edge
     * @see     Vertex
     * @see     IWeightStrategy
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

            return roundEdgeWeight(EARTH_RADIUS_AVG_KM * angularDistRads,3);
        } else {
            return 0;
        }
    }

    /**
     * Method of rounding the edge weight given by haversine calculation.
     * Input distance is in KM, so rounding of 3 will give meters as a value after the
     * decimal place.
     *
     * @param   distance        input distance to round
     * @param   roundDigits     number of digits to round to after the decimal place
     * @return  distance        distance in KM rounded to specified decimal places
     */
    private double roundEdgeWeight(double distance, int roundDigits){
        BigDecimal roundedDistance = new BigDecimal(distance).setScale(roundDigits, RoundingMode.HALF_EVEN);
        return roundedDistance.doubleValue();
    }

}
