package test.java.com.mquinn.wispassist;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.network.Network;

import java.util.Arrays;

/**
 * Adjacency Matrix algorithm test
 */
public class AdjacencyMatrixTest extends TestCase {

    public AdjacencyMatrixTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AdjacencyMatrixTest.class );
    }

    public void testAdjacencyMatrix() {

        PlanningService wispPlanner = new PlanningService();

        Network scotlandNetwork = wispPlanner.getNetworkFactory().createNetwork("undirected");

        // Create Devices
        Device kilmarnockDevice = wispPlanner.getDeviceFactory().createDeviceManual("Kilmarnock", 55.63211, -4.4948706,
                false);
        Device ayrDevice = wispPlanner.getDeviceFactory().createDeviceManual("Ayr", 55.4589475, -4.6376457,
                false);
        Device hamiltonDevice = wispPlanner.getDeviceFactory().createDeviceManual("Hamilton", 55.780579, -4.0503397,
                false);
        Device glasgowDevice = wispPlanner.getDeviceFactory().createDeviceManual("Glasgow", 55.8607185, -4.281704,
                false);
        Device edinburghDevice = wispPlanner.getDeviceFactory().createDeviceManual("Edinburgh", 55.9411885, -3.2753779,
                false);
        Device prestwickDevice = wispPlanner.getDeviceFactory().createDeviceManual("Prestwick", 55.500032, -4.6167643,
                false);
        Device cumnockDevice = wispPlanner.getDeviceFactory().createDeviceManual("Cumnock", 55.4521378, -4.2740314,
                false);

        // Add links between devices
        kilmarnockDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(kilmarnockDevice, ayrDevice));
        kilmarnockDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(kilmarnockDevice, glasgowDevice));
        ayrDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(ayrDevice, hamiltonDevice));
        hamiltonDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(hamiltonDevice, glasgowDevice));
        glasgowDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(glasgowDevice, edinburghDevice));
        edinburghDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(edinburghDevice, prestwickDevice));
        prestwickDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(prestwickDevice, kilmarnockDevice));
        cumnockDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(cumnockDevice, glasgowDevice));
        prestwickDevice.addEdge(wispPlanner.getLinkFactory().createLinkAuto(prestwickDevice, cumnockDevice));

        // Add devices to network graph
        scotlandNetwork.addVertex(kilmarnockDevice);
        scotlandNetwork.addVertex(ayrDevice);
        scotlandNetwork.addVertex(hamiltonDevice);
        scotlandNetwork.addVertex(glasgowDevice);
        scotlandNetwork.addVertex(edinburghDevice);
        scotlandNetwork.addVertex(prestwickDevice);
        scotlandNetwork.addVertex(cumnockDevice);

        scotlandNetwork.makeUndirected();

        int[] row0 = {0,1,0,1,0,1,0};
        int[] row1 = {1,0,1,0,0,0,0};
        int[] row2 = {0,1,0,1,0,0,0};
        int[] row3 = {1,0,1,0,1,0,1};
        int[] row4 = {0,0,0,1,0,1,0};
        int[] row5 = {1,0,0,0,1,0,1};
        int[] row6 = {0,0,0,1,0,1,0};

        int[][] adjMatrix = scotlandNetwork.getAdjMatrix();

        assertTrue(Arrays.equals(row0,adjMatrix[0]));
        assertTrue(Arrays.equals(row1,adjMatrix[1]));
        assertTrue(Arrays.equals(row2,adjMatrix[2]));
        assertTrue(Arrays.equals(row3,adjMatrix[3]));
        assertTrue(Arrays.equals(row4,adjMatrix[4]));
        assertTrue(Arrays.equals(row5,adjMatrix[5]));
        assertTrue(Arrays.equals(row6,adjMatrix[6]));

    }

}
