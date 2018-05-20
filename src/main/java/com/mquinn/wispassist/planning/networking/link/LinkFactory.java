package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.PlanningService;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;
import main.java.com.mquinn.wispassist.planning.networking.device.Device;

/**
 * Factory pattern concretion for link creation
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class LinkFactory {

    private PlanningService planningService;

    public LinkFactory(PlanningService planningService){
        this.planningService = planningService;
    }

    /**
     * Manual construction of links, feed in start, end naming and weighting strategies
     *
     * @param startVertex
     * @param endVertex
     * @param ILinkNameStrategy
     * @param IWeightStrategy
     * @return
     */
    public Link createLinkManual(Vertex startVertex, Vertex endVertex, ILinkNameStrategy ILinkNameStrategy, IWeightStrategy IWeightStrategy){
        return new Link(startVertex, endVertex, ILinkNameStrategy, IWeightStrategy);
    }


    /**
     * Auto creation of links, just needs start and endpoints
     * Will automatically name and weight links
     *
     * @param startVertex
     * @param endVertex
     * @return
     */
    public Link createLinkAuto(Vertex startVertex, Vertex endVertex){
        return new Link(startVertex, endVertex, new AutoLinkNameStrategy(), new GeolocationWeightStrategy());
    }

    /**
     * Random creation of links, used in stress testing
     *
     * @return
     */
    public Link createLinkRandom(){
        Device randomStart = this.planningService.getDeviceFactory().createDeviceRandom();
        Device randomEnd = this.planningService.getDeviceFactory().createDeviceRandom();
        return new Link(randomStart, randomEnd, new AutoLinkNameStrategy(), new GeolocationWeightStrategy());
    }

}
