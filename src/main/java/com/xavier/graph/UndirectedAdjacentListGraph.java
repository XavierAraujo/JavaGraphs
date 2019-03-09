package com.xavier.graph;

public class UndirectedAdjacentListGraph extends AdjacentListGraph {

    /**
     * This method builds 2 edges (vertex1->vertex2 and
     * vertex2->vertex1) between the given origin vertex
     * and destination vertex and adds it to the graph.
     * @param vertex1 Vertex 1.
     * @param vertex2 Vertex 2.
     */
    void addEdge(Vertex vertex1, Vertex vertex2) throws VertexNotFoundException
    {
        addEdge(new Edge(vertex1, vertex2));
        addEdge(new Edge(vertex2, vertex1));
    }
}
