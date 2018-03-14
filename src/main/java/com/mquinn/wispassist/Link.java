package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

public class Link extends Edge implements ILink {

    private String linkName;

    public Link(String linkName, Vertex startVertex, Vertex endVertex, WeightStrategy weightStrategy){
        super(startVertex, endVertex, weightStrategy);
        this.linkName = linkName;
    }

    @Override
    public String getLinkName() {
        return linkName;
    }

    @Override
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

}
