package com.xavier.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraShortestPathAlgorithmTest
{

    @Test
    void basicTopologyTest() throws VertexNotFoundException {
        WeightedDirectedAdjacentListGraph graph = new WeightedDirectedAdjacentListGraph();
        assertNotNull(graph);

        Vertex v0 = new Vertex(0);
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);
        Vertex v9 = new Vertex(9);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);

        // TODO: Should not be possible to call addEdge()!!!
        graph.addWeightedEdge(v0, v2, 6);
        graph.addWeightedEdge(v2, v0, 6);
        graph.addWeightedEdge(v0, v3, 3);
        graph.addWeightedEdge(v3, v0, 3);
        graph.addWeightedEdge(v0, v7, 7);
        graph.addWeightedEdge(v7, v0, 7);
        graph.addWeightedEdge(v1, v2, 5);
        graph.addWeightedEdge(v2, v1, 5);
        graph.addWeightedEdge(v1, v4, 12);
        graph.addWeightedEdge(v4, v1, 12);
        graph.addWeightedEdge(v1, v7, 9);
        graph.addWeightedEdge(v7, v1, 9);
        graph.addWeightedEdge(v2, v5, 3);
        graph.addWeightedEdge(v5, v2, 3);
        graph.addWeightedEdge(v2, v6, 6);
        graph.addWeightedEdge(v6, v2, 6);
        graph.addWeightedEdge(v3, v5, 3);
        graph.addWeightedEdge(v5, v3, 3);
        graph.addWeightedEdge(v4, v6, 11);
        graph.addWeightedEdge(v6, v4, 11);
        graph.addWeightedEdge(v5, v8, 2);
        graph.addWeightedEdge(v8, v5, 2);
        graph.addWeightedEdge(v6, v8, 7);
        graph.addWeightedEdge(v8, v6, 7);

        WeightedGraphPath path = graph.findMinimumCostPath(v7, v8);
        assertEquals(15, path.getWeight());
        assertEquals(5, path.getPath().size());
        assertEquals(v8, path.getPath().get(0));
        assertEquals(v5, path.getPath().get(1));
        assertEquals(v3, path.getPath().get(2));
        assertEquals(v0, path.getPath().get(3));
        assertEquals(v7, path.getPath().get(4));

        path = graph.findMinimumCostPath(v1, v8);
        assertEquals(10, path.getWeight());
        assertEquals(4, path.getPath().size());
        assertEquals(v8, path.getPath().get(0));
        assertEquals(v5, path.getPath().get(1));
        assertEquals(v2, path.getPath().get(2));
        assertEquals(v1, path.getPath().get(3));

        path = graph.findMinimumCostPath(v1, v1);
        assertEquals(0, path.getWeight());
        assertEquals(1, path.getPath().size());
        assertEquals(v1, path.getPath().get(0));

        // Vertex 9 is isolated so no path is possible
        path = graph.findMinimumCostPath(v1, v9);
        assertNull(path);

        Vertex v999 = new Vertex(999);
        assertThrows(VertexNotFoundException.class, () -> graph.findMinimumCostPath(v1, v999));
    }
}
