package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

public class Link extends Edge implements ILink {

    private String linkName;

    public Link(String linkName, Vertex startVertex, Vertex endVertex, WeightStrategy weightStrategy){
        this.linkName = linkName;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weightStrategy = weightStrategy;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

}
