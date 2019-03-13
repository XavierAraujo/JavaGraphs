package com.xavier.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedDirectedAdjacentListGraphTest {

    @Test
    void basicTopologyTest() throws VertexNotFoundException, InvalidGraphEdgeException {
        WeightedDirectedAdjacentListGraph graph = new WeightedDirectedAdjacentListGraph();
        assertNotNull(graph);

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        assertThrows(InvalidGraphEdgeException.class, () -> graph.addEge(new Edge(v0, v1)));

    }
}
