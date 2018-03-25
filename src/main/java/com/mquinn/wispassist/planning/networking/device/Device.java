package main.java.com.mquinn.wispassist.planning.networking.device;

import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

import java.util.concurrent.atomic.AtomicInteger;

public class Device extends Vertex implements IDevice {

    private static AtomicInteger newId = new AtomicInteger();

    private final int id;

    private String deviceName;
    private boolean isSourceNode;

    private double latitude;
    private double longitude;

    public Device (String deviceName, double latitude, double longitude, boolean sourceNodeStatus) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.isSourceNode = sourceNodeStatus;
        this.deviceName = deviceName;
        this.id = newId.incrementAndGet();
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getDeviceName() {
        return this.deviceName;
    }

    @Override
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean getSourceNodeStatus() {
        return isSourceNode;
    }

    @Override
    public void setSouceNodeStatus(boolean status) {
        this.isSourceNode = status;
    }

}
