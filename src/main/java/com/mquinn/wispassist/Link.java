package main.java.com.mquinn.wispassist;

import main.java.com.mquinn.graphing.Edge;
import main.java.com.mquinn.graphing.Vertex;

public class Link extends Edge implements ILink {

    private String linkName;

    public Link(Vertex startVertex, Vertex endVertex, ILinkNameStrategy ILinkNameStrategy, IWeightStrategy IWeightStrategy){
        super(startVertex, endVertex, IWeightStrategy);
        this.linkName = ILinkNameStrategy.generateLinkName(this);
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
