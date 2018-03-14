package com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;
import main.java.com.mquinn.wispassist.Device;
import main.java.com.mquinn.wispassist.GeolocationWeight;
import main.java.com.mquinn.wispassist.Link;
import main.java.com.mquinn.wispassist.Network;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Program Start" );

        Device deviceOne = new Device("Start Device 1",false);
        Device deviceTwo = new Device("End Device 1",false);
        Device deviceThree = new Device("Start Device 2",false);
        Device deviceFour = new Device("End Device 2",false);
        Device deviceFive = new Device("Start Device 3",false);
        Device deviceSix = new Device("End Device 3",false);

        Link oneToTwo = new Link("One to two", deviceOne, deviceTwo, new GeolocationWeight());
        Link twoToThree = new Link("Two to three", deviceTwo, deviceThree, new GeolocationWeight());
        Link threeToFour = new Link("Three to four", deviceThree, deviceFour, new GeolocationWeight());
        Link fourToFive = new Link("Four to five", deviceFour, deviceFive, new GeolocationWeight());
        Link fiveToSix = new Link("Five to six", deviceFive, deviceSix, new GeolocationWeight());
        Link sixToOne = new Link("Six to one", deviceSix, deviceOne, new GeolocationWeight());

        System.out.println("Adding 1->2" + deviceTwo.addEdge(oneToTwo));
        System.out.println("Adding 2->3" + deviceTwo.addEdge(twoToThree));

        Network myNetwork = new Network();

        myNetwork.addVertex(deviceOne);
        myNetwork.addVertex(deviceTwo);
        myNetwork.addVertex(deviceThree);
        myNetwork.addVertex(deviceFour);
        myNetwork.addVertex(deviceFive);
        myNetwork.addVertex(deviceSix);

        System.out.println("Number of Vertices: " + myNetwork.getNumVertices());

        System.out.println("Vertices: " + myNetwork.getVertices());

        myNetwork.printNetwork();

        System.out.println( "Program End" );
    }
}
