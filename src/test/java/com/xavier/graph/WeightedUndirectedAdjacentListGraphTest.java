package com.xavier.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedUndirectedAdjacentListGraphTest {

    @Test
    void basicTopologyTest() throws VertexNotFoundException, InvalidGraphEdgeException {
        WeightedUndirectedAdjacentListGraph graph = new WeightedUndirectedAdjacentListGraph();
        assertNotNull(graph);

        assertFalse(graph.isDirected());
        assertTrue(graph.isWeighted());

        // TODO: Complete test
    }
}
