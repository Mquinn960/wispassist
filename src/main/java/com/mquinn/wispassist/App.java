package main.java.com.mquinn.wispassist;

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

        Network scotlandNetwork = wispPlanner.getNetworkFactory().createNetwork("undirected");

        // Create Devices
        Device kilmarnockDevice = wispPlanner.getDeviceFactory().createDeviceManual("Ayr", 55.4589475, -4.6376457,
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
        kilmarnockDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(kilmarnockDevice,
                                                                               ayrDevice,
                                                                               new AutoLinkNameStrategy(),
                                                                               new GeolocationWeightStrategy()));

        kilmarnockDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(kilmarnockDevice,
                                                                               glasgowDevice,
                                                                               new AutoLinkNameStrategy(),
                                                                               new GeolocationWeightStrategy()));

        ayrDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(ayrDevice,
                                                                        hamiltonDevice,
                                                                        new AutoLinkNameStrategy(),
                                                                        new GeolocationWeightStrategy()));

        hamiltonDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(hamiltonDevice,
                                                                             glasgowDevice,
                                                                             new AutoLinkNameStrategy(),
                                                                             new GeolocationWeightStrategy()));

        glasgowDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(glasgowDevice,
                                                                            edinburghDevice,
                                                                            new AutoLinkNameStrategy(),
                                                                            new GeolocationWeightStrategy()));

        edinburghDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(edinburghDevice,
                                                                              prestwickDevice,
                                                                              new AutoLinkNameStrategy(),
                                                                              new GeolocationWeightStrategy()));

        prestwickDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(prestwickDevice,
                                                                              kilmarnockDevice,
                                                                              new AutoLinkNameStrategy(),
                                                                              new GeolocationWeightStrategy()));

        cumnockDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(cumnockDevice,
                                                                            glasgowDevice,
                                                                            new AutoLinkNameStrategy(),
                                                                            new GeolocationWeightStrategy()));

        prestwickDevice.addEdge(wispPlanner.getLinkFactory().createLinkManual(prestwickDevice,
                                                                              cumnockDevice,
                                                                              new AutoLinkNameStrategy(),
                                                                              new GeolocationWeightStrategy()));

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
        Network minimumSpanningTree = scotlandNetwork.calculateSpanningTree();
        minimumSpanningTree.printNetwork();

        System.out.println("Program End");
    }
}
