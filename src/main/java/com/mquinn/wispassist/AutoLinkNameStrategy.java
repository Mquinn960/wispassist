package main.java.com.mquinn.wispassist;

public class AutoLinkNameStrategy implements LinkNameStrategy {

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
