package main.java.com.mquinn.wispassist.planning.networking.device;

import main.java.com.mquinn.wispassist.planning.PlanningService;

import java.util.Random;

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
    public Device createDeviceRandom(){

        double rangeLatMin = -85;
        double rangeLatMax = +85;

        double rangeLongMin = -180;
        double rangeLongMax = 180;

        Random rand = new Random();
        double randomLat = rangeLatMin + (rangeLatMax - rangeLatMin) * rand.nextDouble();
        double randomLong = rangeLongMin + (rangeLongMax - rangeLongMin) * rand.nextDouble();

        return new Device((String.valueOf(randomLat) + String.valueOf(randomLong)), randomLat, randomLong, false);

    }

}
