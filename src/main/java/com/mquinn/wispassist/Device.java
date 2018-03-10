package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Vertex;

public class Device extends Vertex implements IDevice {

    private String deviceName;
    private double latitude;
    private double longitude;

    public Device () {

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
