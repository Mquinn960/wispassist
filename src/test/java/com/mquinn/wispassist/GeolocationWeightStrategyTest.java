package test.java.com.mquinn.wispassist;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.Link;

/**
 * Geolocation weighting formula test
 */
public class GeolocationWeightStrategyTest extends TestCase {

    public GeolocationWeightStrategyTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( GeolocationWeightStrategyTest.class );
    }

    public void testSimpleHaversine() {

        PlanningService planningService = new PlanningService();

        Device device1 = planningService.getDeviceFactory().createDeviceManual("Device 1", 55.63211, -4.4948706,
                false);

        Device device2 = planningService.getDeviceFactory().createDeviceManual("Device 2", 55.4589475, -4.6376457,
                false);

        Link testLink  = planningService.getLinkFactory().createLinkAuto(device1,device2);

        assertEquals(21.247,testLink.getWeight(),0);
    }

}
