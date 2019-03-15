package com.xavier.graphs.adjacent_list_graphs;

import com.xavier.graphs.Graph;

public class AdjacentListGraphFactory {

    /**
     * This method returns the appropriate graph given 2 attributes
     * indicating whether the graph is directed and whether it is
     * weighted.
     * If the graph is weighted and directed a
     * {@link WeightedDirectedAdjacentListGraph} graph is returned.
     * If the graph is not weighted and directed a
     * {@link DirectedAdjacentListGraph} graph is returned.
     * If the graph is weighted and undirected a
     * {@link WeightedUndirectedAdjacentListGraph} graph is returned.
     * If the graph is not weighted and undirected a
     * {@link UndirectedAdjacentListGraph} graph is returned.
     * @param isDirected Attribute indicating whether the graph is
     *                   directed or not.
     * @param isWeighted Attribute indicating whether the graph is
     *                   weighted or not.
     * @return Returns a graph object according the specified attributes.
     */
    public Graph getGraph(boolean isDirected, boolean isWeighted){
        if(isDirected) {
            if(isWeighted) {
                return new WeightedDirectedAdjacentListGraph();
            }
            else {
                return new DirectedAdjacentListGraph();
            }
        }
        else{
            if(isWeighted) {
                return new WeightedUndirectedAdjacentListGraph();
            }
            else {
                return new UndirectedAdjacentListGraph();
            }
        }
    }
}
