package main.java.com.mquinn.wispassist.planning.networking.device;

public interface IDevice {

    int id = 0;
    String deviceName = "";
    boolean isSourceNode = false;
    double latitude = 0;
    double longitude = 0;

    int getID();

    String getDeviceName ();
    void setDeviceName (String deviceName);

    double getLongitude();
    void setLongitude(double longitude);

    double getLatitude();
    void setLatitude(double latitude);

    boolean getSourceNodeStatus();
    void setSouceNodeStatus(boolean status);

}
