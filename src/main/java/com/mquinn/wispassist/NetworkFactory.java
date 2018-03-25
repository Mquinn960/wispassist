package main.java.com.mquinn.wispassist;

public class NetworkFactory {

    private PlanningService planningService;

    public NetworkFactory(PlanningService planningService){
        this.planningService = planningService;
    }

    public Network createNetwork(String networkType){
        if (networkType.equals("directed")){
            return new Network(new DigraphAdjacencyMatrixStrategy(), new DijkstraPathfindingStrategy(), new MinimumCostArboresenceMinimumSpanningTreeStrategy());
        } else if (networkType.equals("undirected")) {
            return new Network(new GraphAdjacencyMatrixStrategy(), new DijkstraPathfindingStrategy(), new KruskalMinimumSpanningTreeStrategy());
        }
        return null;
    }

}
