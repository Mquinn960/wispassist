package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;

public class Link extends Edge implements ILink {

    private String linkName;

    public Link(final WeightStrategy weightStrategy){
        this.weightStrategy = weightStrategy;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

}
