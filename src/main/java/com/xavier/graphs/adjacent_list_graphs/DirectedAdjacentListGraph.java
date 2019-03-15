package com.xavier.graphs.adjacent_list_graphs;

import com.xavier.graphs.Edge;
import com.xavier.graphs.Vertex;
import com.xavier.graphs.exceptions.VertexNotFoundException;

class DirectedAdjacentListGraph extends AdjacentListGraph {

    @Override
    public boolean isDirected() {
        return true;
    }

    /**
     * This method builds an edge between the given origin
     * vertex and destination vertex and adds it to the graphs.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     */
    void addEdge(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        super.addEdge(new Edge(origin, destination));
    }
}
