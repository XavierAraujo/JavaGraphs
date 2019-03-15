package com.xavier.graph;

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
            throw new InvalidGraphEdgeException("In a weighted graph all edges must be weighted");
        }
        super.addEdge(edge);
    }
}
