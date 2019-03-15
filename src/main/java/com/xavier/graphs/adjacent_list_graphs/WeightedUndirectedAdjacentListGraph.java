package com.xavier.graphs.adjacent_list_graphs;

import com.xavier.graphs.Edge;
import com.xavier.graphs.Vertex;
import com.xavier.graphs.WeightedEdge;
import com.xavier.graphs.exceptions.InvalidGraphEdgeException;
import com.xavier.graphs.exceptions.VertexNotFoundException;

public class WeightedUndirectedAdjacentListGraph extends AdjacentListGraph {

    @Override
    public boolean isWeighted() {
        return true;
    }

    int addWeightedEdge(Vertex vertex1, Vertex vertex2, int weight) throws VertexNotFoundException
    {
        WeightedEdge edge1 = new WeightedEdge(vertex1, vertex2, weight);
        WeightedEdge edge2 = new WeightedEdge(vertex2, vertex1, weight);

        addEdge(edge1);
        addEdge(edge2);

        return 0;
    }

    void addEge(Edge edge) throws InvalidGraphEdgeException, VertexNotFoundException
    {
        if (!(edge instanceof WeightedEdge)) {
            throw new InvalidGraphEdgeException("In a weighted graphs all edges must be weighted");
        }
        super.addEdge(edge);
    }
}
