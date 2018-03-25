package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;

public class NetworkFactory {

    private PlanningService planningService;

    public NetworkFactory(PlanningService planningService){
        this.planningService = planningService;
    }

    public Network createNetwork(String networkType){
        if (networkType.equals("directed")){
            return new Network(new DirectedAdjacencyMatrixStrategy(), new DirectedDijkstraPathfindingStrategy(), new MinimumCostArboresenceMinimumSpanningTreeStrategy());
        } else if (networkType.equals("undirected")) {
            return new Network(new UndirectedAdjacencyMatrixStrategy(), new UndirectedDijkstraPathfindingStrategy(), new KruskalMinimumSpanningTreeStrategy());
        }
        return null;
    }

}
