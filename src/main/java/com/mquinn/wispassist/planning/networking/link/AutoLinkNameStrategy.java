package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.networking.device.Device;

/**
 * Strategy pattern concretion for auto naming of links generated
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class AutoLinkNameStrategy implements ILinkNameStrategy {

    /**
     * Main method concatenates link start and end points to create a name
     *
     * @param link
     * @return
     */
    @Override
    public String generateLinkName(Link link) {
        if (link.getStartVertex() instanceof Device && link.getEndVertex() instanceof Device){
            return ((Device) link.getStartVertex()).getDeviceName() +
                    " to " +
                    ((Device) link.getEndVertex()).getDeviceName();
        } else {
            return "No name generated";
        }
    }

}
