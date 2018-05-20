package main.java.com.mquinn.wispassist.planning;

import main.java.com.mquinn.wispassist.planning.networking.device.DeviceFactory;
import main.java.com.mquinn.wispassist.planning.networking.link.LinkFactory;
import main.java.com.mquinn.wispassist.planning.networking.network.Network;
import main.java.com.mquinn.wispassist.planning.networking.network.NetworkFactory;

/**
 * Singleton class used in absence of caching or database layer
 * for main application
 *
 * Stores main network used in the app main method
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class PlanningService {

    private NetworkFactory networkFactory;
    private DeviceFactory deviceFactory;
    private LinkFactory linkFactory;

    private Network mainNetwork = null;

    private static PlanningService instance = null;

    /**
     * Sets factory objects for network components
     */
    protected PlanningService(){
        this.networkFactory = new NetworkFactory(this);
        this.deviceFactory = new DeviceFactory(this);
        this.linkFactory = new LinkFactory(this);
    }

    /**
     * Static method to call the protected constructor for the singleton
     * Get the only planningservice instance or start a new one
     *
     * @return
     */
    public static PlanningService getInstance(){
        if (instance == null){
            instance = new PlanningService();
        }
        return instance;
    }

    /**
     * Return the network factory
     *
     * @return
     */
    public NetworkFactory getNetworkFactory(){
        return this.networkFactory;
    }

    /**
     * Return the device factory
     *
     * @return
     */
    public DeviceFactory getDeviceFactory(){
        return this.deviceFactory;
    }

    /**
     * Return the link factory
     *
     * @return
     */
    public LinkFactory getLinkFactory(){
        return this.linkFactory;
    }

    /**
     * Return the main network object
     *
     * @return
     */
    public Network getMainNetwork() {
        if (this.mainNetwork == null){
            this.mainNetwork = this.getNetworkFactory().createNetwork("undirected");
        }
        return this.mainNetwork;
    }

    /**
     * Set the main network object
     *
     * @param mainNetwork
     */
    public void setMainNetwork(Network mainNetwork) {
        this.mainNetwork = mainNetwork;
    }
}
