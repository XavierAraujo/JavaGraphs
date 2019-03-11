package com.xavier.graph;

import java.util.*;

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

    /**
     * This method uses the Dijkstra's algorithm to calculate the
     * minimum cost path between 2 graph nodes.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @return Returns the list of vertices that form the minimum
     *         cost path.
     * @throws VertexNotFoundException Throws this exception if any
     *          of the vertices does not exist in the graph.
     */
    WeightedGraphPath findMinimumCostPath(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        DijkstraShortestPathAlgorithm algorithm = new DijkstraShortestPathAlgorithm(this);
        return algorithm.findMinCostPath(origin, destination);
    }
}
