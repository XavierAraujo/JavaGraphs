package com.xavier.graphs.adjacent_list_graphs;

import com.xavier.graphs.*;
import com.xavier.graphs.algorithms.DijkstraShortestPathAlgorithm;
import com.xavier.graphs.exceptions.InvalidGraphEdgeException;
import com.xavier.graphs.exceptions.VertexNotFoundException;

public class WeightedDirectedAdjacentListGraph extends DirectedAdjacentListGraph {

    @Override
    public boolean isWeighted() {
        return true;
    }

    /**
     * This method builds a weighted edge between the given origin
     * vertex and destination vertex and adds it to the graphs.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @param weight Weight of the edge
     */
    public void addWeightedEdge(Vertex origin, Vertex destination, int weight) throws VertexNotFoundException
    {
        super.addEdge(new WeightedEdge(origin, destination, weight));
    }

    void addEge(Edge edge) throws InvalidGraphEdgeException, VertexNotFoundException
    {
        if (!(edge instanceof WeightedEdge)) {
            throw new InvalidGraphEdgeException("In a weighted graphs all edges must be weighted");
        }
        super.addEdge(edge);
    }

    /**
     * This method uses the Dijkstra's algorithm to calculate the
     * minimum cost path between 2 graphs nodes.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @return Returns the list of vertices that form the minimum
     *         cost path.
     * @throws VertexNotFoundException Throws this exception if any
     *          of the vertices does not exist in the graphs.
     */
    public WeightedGraphPath findMinimumCostPath(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        DijkstraShortestPathAlgorithm algorithm = new DijkstraShortestPathAlgorithm(this);
        return algorithm.findMinCostPath(origin, destination);
    }
}
