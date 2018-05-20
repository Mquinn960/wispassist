package main.java.com.mquinn.wispassist.planning.networking.network;

import main.java.com.mquinn.wispassist.planning.PlanningService;

/**
 * Factory pattern concretion to create networks using
 * differing parameters
 *
 * @author  Matthew Quinn
 * @since   1.0
 */
public class NetworkFactory {

    private PlanningService planningService;

    /**
     * Constructor sets the main planning service in place
     *
     * @param planningService
     */
    public NetworkFactory(PlanningService planningService){
        this.planningService = planningService;
    }

    /**
     * Creates a network based on the input string, directed or undirected
     * This sets the strategies accordingly
     *
     * @param networkType
     * @return
     */
    public Network createNetwork(String networkType){
        if (networkType.equals("directed")){
            return new Network(new DirectedAdjacencyMatrixStrategy(),
                               new DijkstraPathfindingStrategy(),
                               new MinimumCostArboresenceMinimumSpanningTreeStrategy(),
                               new DirectedNetworkPrintStrategy(),
                               true);
        } else if (networkType.equals("undirected")) {
            return new Network(new UndirectedAdjacencyMatrixStrategy(),
                               new DijkstraPathfindingStrategy(),
                               new PrimMinimumSpanningTreeStrategy(),
                               new UndirectedNetworkPrintStrategy(),
                               false);
        }
        return null;
    }

}
