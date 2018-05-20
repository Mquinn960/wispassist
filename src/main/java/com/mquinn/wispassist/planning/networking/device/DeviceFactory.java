package main.java.com.mquinn.wispassist.planning.networking.device;

import main.java.com.mquinn.wispassist.planning.PlanningService;

import java.util.Random;

/**
 * Factory for creating devices from archetypes
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class DeviceFactory {

    private PlanningService planningService;

    /**
     * Constructor for the device factory
     *
     * @param planningService
     */
    public DeviceFactory(PlanningService planningService){
        this.planningService = planningService;
    }


    /**
     * Manual device creation
     * Input name, lat/long/sourcenodestatus as you would manually
     *
     * @param deviceName
     * @param latitude
     * @param longitude
     * @param sourceNodeStatus
     * @return
     */
    public Device createDeviceManual(String deviceName, double latitude, double longitude, boolean sourceNodeStatus){
        return new Device(deviceName, latitude, longitude, sourceNodeStatus);
    }


    /**
     * Auto device creation stub, not in use
     *
     * @param deviceName
     * @param sourceNodeStatus
     * @return
     */
    public Device createDeviceAuto(String deviceName, boolean sourceNodeStatus){
        // TODO: take name of device and source node status and use GMAPs service to get lat/long
        return new Device(deviceName, 0.0, 0.0, sourceNodeStatus);
    }

    /**
     * Random device creation method, used for stress testing
     *
     * @return
     */
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
