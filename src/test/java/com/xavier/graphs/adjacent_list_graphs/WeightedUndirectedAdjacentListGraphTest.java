package com.xavier.graphs.adjacent_list_graphs;

import com.xavier.graphs.adjacent_list_graphs.WeightedUndirectedAdjacentListGraph;
import com.xavier.graphs.exceptions.InvalidGraphEdgeException;
import com.xavier.graphs.exceptions.VertexNotFoundException;
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
