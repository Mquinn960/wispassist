package com.mquinn.wispassist;

import main.java.com.mquinn.wispassist.Device;
import main.java.com.mquinn.wispassist.GeolocationWeight;
import main.java.com.mquinn.wispassist.Link;
import main.java.com.mquinn.wispassist.Network;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Program Start" );

        Device deviceOne = new Device("Start Device",false);
        Device deviceTwo = new Device("End Device",false);

        Link oneToTwo = new Link("One to two", deviceOne, deviceTwo, new GeolocationWeight());

        System.out.println( oneToTwo.getLinkName() );

        System.out.println( oneToTwo.getStartVertex() );

        System.out.println( "Program End" );
    }
}
