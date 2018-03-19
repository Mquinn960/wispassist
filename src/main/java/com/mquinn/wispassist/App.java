package com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Vertex;
import main.java.com.mquinn.wispassist.*;

import java.util.LinkedList;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Program Start" );

        Device kilmarnockDevice = new Device("Kilmarnock",55.63211, -4.4948706,true);
        Device ayrDevice = new Device("Ayr", 55.4589475, -4.6376457,false);
        Device hamiltonDevice = new Device("Hamilton", 55.780579, -4.0503397,false);
        Device glasgowDevice = new Device("Glasgow", 55.8607185, -4.281704, false);
        Device edinburghDevice = new Device("Edinburgh", 55.9411885, -3.2753779, false);
        Device prestwickDevice = new Device("Prestwick", 55.500032, -4.6167643,false);
        Device cumnockDevice = new Device("Cumnock", 55.4521378, -4.2740314, false);

        kilmarnockDevice.addEdge(new Link(kilmarnockDevice, ayrDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        kilmarnockDevice.addEdge(new Link(kilmarnockDevice, glasgowDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        ayrDevice.addEdge(new Link(ayrDevice, hamiltonDevice,  new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        hamiltonDevice.addEdge(new Link(hamiltonDevice, glasgowDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        glasgowDevice.addEdge(new Link(glasgowDevice, edinburghDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        edinburghDevice.addEdge(new Link(edinburghDevice, prestwickDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        prestwickDevice.addEdge(new Link(prestwickDevice, kilmarnockDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        cumnockDevice.addEdge(new Link(cumnockDevice, glasgowDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));
        prestwickDevice.addEdge(new Link(prestwickDevice, cumnockDevice, new AutoLinkNameStrategy(), new GeolocationWeightStrategy()));

        Network scotlandNetwork = new Network(new DigraphAdjacencyMatrixStrategy(), new DijkstraPathfindingStrategy());

        scotlandNetwork.addVertex(kilmarnockDevice);
        scotlandNetwork.addVertex(ayrDevice);
        scotlandNetwork.addVertex(hamiltonDevice);
        scotlandNetwork.addVertex(glasgowDevice);
        scotlandNetwork.addVertex(edinburghDevice);
        scotlandNetwork.addVertex(prestwickDevice);
        scotlandNetwork.addVertex(cumnockDevice);

        scotlandNetwork.printNetwork();
        scotlandNetwork.printAdjMatrix(false);

        LinkedList<Vertex> path = scotlandNetwork.calculatePath(cumnockDevice,prestwickDevice);

        System.out.println("Shortest Path" + "\r");
        for (Vertex vertex : path){
            if (vertex instanceof Device){
                System.out.println(((Device) vertex).getDeviceName() + "\r");
                System.out.println(((Device) vertex).getDistanceFromSource() + "\r");
            }
        }

        System.out.println("Program End");
    }
}
