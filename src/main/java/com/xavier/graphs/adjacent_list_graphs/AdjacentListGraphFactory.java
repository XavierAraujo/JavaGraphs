package com.xavier.graphs.adjacent_list_graphs;

import com.xavier.graphs.Graph;

public class AdjacentListGraphFactory {

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
