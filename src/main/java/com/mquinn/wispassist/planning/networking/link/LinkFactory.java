package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

public class LinkFactory {

    private PlanningService planningService;

    public LinkFactory(PlanningService planningService){
        this.planningService = planningService;
    }

    // Uses default constructor
    public Link createLinkManual(Vertex startVertex, Vertex endVertex, ILinkNameStrategy ILinkNameStrategy, IWeightStrategy IWeightStrategy){
        return new Link(startVertex, endVertex, ILinkNameStrategy, IWeightStrategy);
    }

    // Auto naming and weighting
    public Link createLinkAuto(Vertex startVertex, Vertex endVertex){
        return new Link(startVertex, endVertex, new AutoLinkNameStrategy(), new GeolocationWeightStrategy());
    }

    // Random link creation
    // TODO: implementation
    public Link createLinkRandom(){
        Device randomStart = this.planningService.getDeviceFactory().createDeviceRandom();
        Device randomEnd = this.planningService.getDeviceFactory().createDeviceRandom();
        return new Link(randomStart, randomEnd, new AutoLinkNameStrategy(), new GeolocationWeightStrategy());
    }

}
