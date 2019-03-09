package com.xavier.graph;

class DirectedAdjacentListGraph extends AdjacentListGraph {

    /**
     * This method builds an edge between the given origin
     * vertex and destination vertex and adds it to the graph.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     */
    void addEdge(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        addEdge(new Edge(origin, destination));
    }
}
