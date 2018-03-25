package main.java.com.mquinn.wispassist;

public class PlanningService {

    private NetworkFactory networkFactory;
    private DeviceFactory deviceFactory;
    private LinkFactory linkFactory;

    public PlanningService(){
        this.networkFactory = new NetworkFactory(this);
        this.deviceFactory = new DeviceFactory(this);
        this.linkFactory = new LinkFactory(this);
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

}
