package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;
import main.java.com.mquinn.wispassist.planning.networking.network.Network;
import main.java.com.mquinn.wispassist.planning.networking.network.ShortestPath;


import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Console manual app test stub for debugging
 *
 */
public class App_test
{
    public static void main( String[] args )
    {
        System.out.println( "Program Start" );

        PlanningService wispPlanner = PlanningService.getInstance();

        Network scotlandNetwork = wispPlanner.getNetworkFactory().createNetwork("undirected");

        ArrayList<Device> devices = new ArrayList<>();


        // Configure test parameters
        int deviceNo = 100;
        int linkNo = 100;

        for (int i=0; i<deviceNo; i++){
            devices.add(wispPlanner.getDeviceFactory().createDeviceRandom());
        }

        for (int i=0; i<linkNo; i++){
            int randomLinkStart = ThreadLocalRandom.current().nextInt(0, devices.size() + 1 - 1);
            int randomLinkEnd = ThreadLocalRandom.current().nextInt(0, devices.size() + 1 - 1);
            devices.get(randomLinkStart).addEdge(wispPlanner.getLinkFactory().createLinkAuto(devices.get(randomLinkStart),devices.get(randomLinkEnd)));
        }

        for (Device device: devices){
            scotlandNetwork.addVertex(device);
        }

        scotlandNetwork.makeUndirected();

        // Print network configuration and adjacency matrix
        //scotlandNetwork.printNetwork();
        //scotlandNetwork.printAdjMatrix(false);



        int randomDevice1 = ThreadLocalRandom.current().nextInt(0, devices.size() + 1 - 1);
        int randomDevice2 = ThreadLocalRandom.current().nextInt(0, devices.size() + 1 - 1);



        long startTime = System.nanoTime();

        // Calculate shortest path between two nodes
        ShortestPath path = scotlandNetwork.calculatePath(devices.get(randomDevice1),devices.get(randomDevice2));

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Dijkstra Time: " + totalTime);




        startTime = System.nanoTime();

        // Calculate and print minimum spanning tree
        Network minimumSpanningTree = scotlandNetwork.calculateSpanningTree(devices.get(1));
        //minimumSpanningTree.printNetwork();

        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("MST Time: " + totalTime);

        System.out.println("Program End");
    }
}
