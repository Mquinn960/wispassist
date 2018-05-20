package main.java.com.mquinn.wispassist.planning.networking.link;

/**
 * Main interface for link subtypes
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public interface ILink {

    String linkName = "";

    String getLinkName();
    void setLinkName(String linkName);

}
