package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

public class Link extends Edge implements ILink {

    private String linkName;
    private double linkWeight;

    public Link(Vertex startVertex, Vertex endVertex, ILinkNameStrategy ILinkNameStrategy, IWeightStrategy IWeightStrategy){
        super(startVertex, endVertex, IWeightStrategy);
        this.linkName = ILinkNameStrategy.generateLinkName(this);
        this.linkWeight = this.getWeight();
    }

    @Override
    public String getLinkName() {
        return linkName;
    }

    @Override
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public double weightProperty(){
        return super.getWeight();
    }

    public String nameProperty(){
        return this.getLinkName();
    }

    public double getLinkWeight() {
        return linkWeight;
    }
}
