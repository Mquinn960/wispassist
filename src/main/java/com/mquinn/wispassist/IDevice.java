package main.java.com.mquinn.wispassist;

public interface IDevice {

    String getDeviceName ();
    void setDeviceName (String deviceName);

    double getLongitude();
    void setLongitude(double longitude);

    double getLatitude();
    void setLatitude(double latitude);

    boolean getSourceNodeStatus();
    void setSouceNodeStatus(boolean status);

}
