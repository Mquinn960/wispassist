package main.java.com.mquinn.wispassist.planning.networking.network;

public class UndirectedNetworkPrintStrategy extends GeneralNetworkPrintStrategy implements INetworkPrintStrategy {

    @Override
    public void printNetwork(Network network) {
        network.makeUndirected();
        super.printNetwork(network);
    }

}
