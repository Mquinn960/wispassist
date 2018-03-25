package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.networking.device.Device;

public class AutoLinkNameStrategy implements ILinkNameStrategy {

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
