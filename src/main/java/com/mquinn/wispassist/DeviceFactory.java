package main.java.com.mquinn.wispassist;

public class DeviceFactory {

    private PlanningService planningService;

    public DeviceFactory(PlanningService planningService){
        this.planningService = planningService;
    }


    // Uses default constructor
    public Device createDeviceManual(String deviceName, double latitude, double longitude, boolean sourceNodeStatus){
        return new Device(deviceName, latitude, longitude, sourceNodeStatus);
    }

    // default implementation,
    public Device createDeviceAuto(String deviceName, boolean sourceNodeStatus){
        // TODO: take name of device and source node status and use GMAPs service to get lat/long
        return new Device(deviceName, 0.0, 0.0, sourceNodeStatus);
    }

    // random implementation,
    // TODO: implementation
    public Device createDeviceRandom(){
        return new Device("random", 0.0, 0.0, false);
    }

}
