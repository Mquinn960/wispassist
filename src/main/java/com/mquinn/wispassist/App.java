package com.mquinn.wispassist;

import main.java.com.mquinn.wispassist.*;


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

        Link kmkToAyr = new Link(kilmarnockDevice, ayrDevice, new AutoLinkNameStrategy(), new GeolocationWeight());
        Link ayrToHam = new Link(ayrDevice, hamiltonDevice,  new AutoLinkNameStrategy(), new GeolocationWeight());
        Link hamToGlas = new Link(hamiltonDevice, glasgowDevice, new AutoLinkNameStrategy(), new GeolocationWeight());
        Link glasToEdin = new Link(glasgowDevice, edinburghDevice, new AutoLinkNameStrategy(), new GeolocationWeight());
        Link edinToPwk = new Link(edinburghDevice, prestwickDevice, new AutoLinkNameStrategy(), new GeolocationWeight());
        Link pwkToKmk = new Link(prestwickDevice, kilmarnockDevice, new AutoLinkNameStrategy(), new GeolocationWeight());

        Link kmkToGlas = new Link(kilmarnockDevice, glasgowDevice, new AutoLinkNameStrategy(), new GeolocationWeight());

        kilmarnockDevice.addEdge(kmkToAyr);
        ayrDevice.addEdge(ayrToHam);
        hamiltonDevice.addEdge(hamToGlas);
        glasgowDevice.addEdge(glasToEdin);
        edinburghDevice.addEdge(edinToPwk);
        prestwickDevice.addEdge(pwkToKmk);

        kilmarnockDevice.addEdge(kmkToGlas);

        Network scotlandNetwork = new Network();

        scotlandNetwork.addVertex(kilmarnockDevice);
        scotlandNetwork.addVertex(ayrDevice);
        scotlandNetwork.addVertex(hamiltonDevice);
        scotlandNetwork.addVertex(glasgowDevice);
        scotlandNetwork.addVertex(edinburghDevice);
        scotlandNetwork.addVertex(prestwickDevice);

        System.out.println("Number of Vertices: " + scotlandNetwork.getNumVertices() + "\r");

        scotlandNetwork.printNetwork();

        System.out.println( "Program End" );
    }
}
