package com.xavier.graph;

public class WeightedDirectedAdjacentListGraph extends DirectedAdjacentListGraph {

    /**
     * This method builds a weighted edge between the given origin
     * vertex and destination vertex and adds it to the graph.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @param weight Weight of the edge
     */
    void addWeightedEdge(Vertex origin, Vertex destination, int weight) throws VertexNotFoundException
    {
        addEdge(new WeightedEdge(origin, destination, weight));
    }

}
