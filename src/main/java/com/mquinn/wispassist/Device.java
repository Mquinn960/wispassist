package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Vertex;

import java.util.concurrent.atomic.AtomicInteger;

public class Device extends Vertex implements IDevice {

    static AtomicInteger newId = new AtomicInteger();

    private String deviceName;
    private boolean isSourceNode;

    private double latitude;
    private double longitude;

    public Device (String deviceName, boolean sourceNodeStatus) {
        isSourceNode = sourceNodeStatus;
        this.deviceName = deviceName;
        this.id = newId.incrementAndGet();
    }

    @Override
    public String getDeviceName() {
        return this.deviceName;
    }

    @Override
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
