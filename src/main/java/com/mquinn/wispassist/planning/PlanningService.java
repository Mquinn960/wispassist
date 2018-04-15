package main.java.com.mquinn.wispassist.planning;

import main.java.com.mquinn.wispassist.planning.networking.device.DeviceFactory;
import main.java.com.mquinn.wispassist.planning.networking.link.LinkFactory;
import main.java.com.mquinn.wispassist.planning.networking.network.Network;
import main.java.com.mquinn.wispassist.planning.networking.network.NetworkFactory;

public class PlanningService {

    private NetworkFactory networkFactory;
    private DeviceFactory deviceFactory;
    private LinkFactory linkFactory;

    private Network mainNetwork = null;

    private static PlanningService instance = null;

    protected PlanningService(){
        this.networkFactory = new NetworkFactory(this);
        this.deviceFactory = new DeviceFactory(this);
        this.linkFactory = new LinkFactory(this);
    }

    public static PlanningService getInstance(){
        if (instance == null){
            instance = new PlanningService();
        }
        return instance;
    }

    public NetworkFactory getNetworkFactory(){
        return this.networkFactory;
    }

    public DeviceFactory getDeviceFactory(){
        return this.deviceFactory;
    }

    public LinkFactory getLinkFactory(){
        return this.linkFactory;
    }

    public Network getMainNetwork() {
        if (this.mainNetwork == null){
            this.mainNetwork = this.getNetworkFactory().createNetwork("undirected");
        }
        return this.mainNetwork;
    }

    public void setMainNetwork(Network mainNetwork) {
        this.mainNetwork = mainNetwork;
    }
}
