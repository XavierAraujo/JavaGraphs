package com.xavier.graph;

public class WeightedUndirectedAdjacentListGraph extends AdjacentListGraph {


    int addWeightedEdge(Vertex vertex1, Vertex vertex2, int weight) throws VertexNotFoundException
    {
        WeightedEdge edge1 = new WeightedEdge(vertex1, vertex2, weight);
        WeightedEdge edge2 = new WeightedEdge(vertex2, vertex1, weight);

        addEdge(edge1);
        addEdge(edge2);

        return 0;
    }
}
