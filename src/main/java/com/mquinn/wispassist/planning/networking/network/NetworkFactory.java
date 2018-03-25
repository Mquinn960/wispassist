package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;

public class NetworkFactory {

    private PlanningService planningService;

    public NetworkFactory(PlanningService planningService){
        this.planningService = planningService;
    }

    public Network createNetwork(String networkType){
        if (networkType.equals("directed")){
            return new Network(new DirectedAdjacencyMatrixStrategy(), new DijkstraPathfindingStrategy(true), new MinimumCostArboresenceMinimumSpanningTreeStrategy());
        } else if (networkType.equals("undirected")) {
            return new Network(new UndirectedAdjacencyMatrixStrategy(), new DijkstraPathfindingStrategy(false), new KruskalMinimumSpanningTreeStrategy());
        }
        return null;
    }

}
