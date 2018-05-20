package main.java.com.mquinn.wispassist.planning.networking.device;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Concretion of Vertex class, inherits graph properties from abstract superclass Vertex
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class Device extends Vertex implements IDevice {

    private static AtomicInteger newId = new AtomicInteger();

    private final int id;

    private String deviceName;
    private boolean isSourceNode;

    private double latitude;
    private double longitude;

    /**
     * Constructor sets input variables for coordinates lat/long, source node status and
     * the name of the device
     *
     * @param deviceName
     * @param latitude
     * @param longitude
     * @param sourceNodeStatus
     */
    public Device (String deviceName, double latitude, double longitude, boolean sourceNodeStatus) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.isSourceNode = sourceNodeStatus;
        this.deviceName = deviceName;
        this.id = newId.incrementAndGet();
    }

    /**
     * Return int device ID
     *
     * @return
     */
    @Override
    public int getID() {
        return this.id;
    }

    /**
     * Get device name
     *
     * @return
     */
    @Override
    public String getDeviceName() {
        return this.deviceName;
    }

    /**
     * Set device name
     *
     * @param deviceName
     */
    @Override
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * Get device longitude
     *
     * @return
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set device longitude
     *
     * @param longitude
     */
    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get device latitude
     *
     * @return
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set device latitude
     *
     * @param latitude
     */
    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Get source node status of the device
     *
     * @return
     */
    @Override
    public boolean getSourceNodeStatus() {
        return isSourceNode;
    }

    /**
     * Set source node status of the device
     *
     * @param status
     */
    @Override
    public void setSouceNodeStatus(boolean status) {
        this.isSourceNode = status;
    }

}
