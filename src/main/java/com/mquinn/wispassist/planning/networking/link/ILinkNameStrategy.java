package main.java.com.mquinn.wispassist.planning.networking.link;

/**
 * Main interface for link naming strategies
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public interface ILinkNameStrategy {

    String generateLinkName(Link link);

}
