package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.link.AutoLinkNameStrategy;
import main.java.com.mquinn.wispassist.planning.networking.link.GeolocationWeightStrategy;
import main.java.com.mquinn.wispassist.planning.networking.network.Network;
import main.java.com.mquinn.wispassist.planning.networking.network.ShortestPath;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Program Start" );

        PlanningService wispPlanner = new PlanningService();

        Network scotlandNetwork = wispPlanner.getNetworkFactory().createNetwork("directed");

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

        // Print network configuration and adjacency matrix
        scotlandNetwork.printNetwork();
        scotlandNetwork.printAdjMatrix(false);

        // Calculate and print shortest path between two nodes
        ShortestPath path = scotlandNetwork.calculatePath(glasgowDevice,cumnockDevice);
        path.printPath();

        // Calculate and print minimum spanning tree
        //Network minimumSpanningTree = scotlandNetwork.calculateSpanningTree();
        //minimumSpanningTree.printNetwork();

        System.out.println("Program End");
    }
}
