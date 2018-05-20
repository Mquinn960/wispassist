package main.java.com.mquinn.wispassist.planning.networking.link;

import main.java.com.mquinn.wispassist.planning.graphing.Edge;
import main.java.com.mquinn.wispassist.planning.graphing.Vertex;

/**
 * Link class inherits from Edge superclass
 * Represents network links between physical devices
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class Link extends Edge implements ILink {

    private String linkName;
    private double linkWeight;

    /**
     * Constructor to set link start, end, name and weighting strategies
     *
     * @param startVertex
     * @param endVertex
     * @param ILinkNameStrategy
     * @param IWeightStrategy
     */
    public Link(Vertex startVertex, Vertex endVertex, ILinkNameStrategy ILinkNameStrategy, IWeightStrategy IWeightStrategy){
        super(startVertex, endVertex, IWeightStrategy);
        this.linkName = ILinkNameStrategy.generateLinkName(this);
        this.linkWeight = this.getWeight();
    }

    /**
     * Get the name of this link
     *
     * @return
     */
    @Override
    public String getLinkName() {
        return linkName;
    }

    /**
     * Set the name of this link
     *
     * @param linkName
     */
    @Override
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /**
     * Helper method for JavaFX tableCellValueFactories
     * Get the link weight value
     *
     * @return
     */
    public double weightProperty(){
        return super.getWeight();
    }

    /**
     * Helper method for JavaFX tableCellValueFactories
     * Get the link name
     *
     * @return
     */
    public String nameProperty(){
        return this.getLinkName();
    }

    /**
     * Helper method for JavaFX tableCellValueFactories
     * Get the link weight
     *
     * @return
     */
    public double getLinkWeight() {
        return linkWeight;
    }
}
