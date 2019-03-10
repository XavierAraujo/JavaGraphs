package com.xavier.graph;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedAdjacentListGraphTest {

    @Test
    void basicTopologyTest() throws VertexNotFoundException {
        UndirectedAdjacentListGraph graph = new UndirectedAdjacentListGraph();
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

        // Undirected graph :: Edge(v0,v1)=Edge(v1,v0) :: v0 <--> v1
        graph.addEdge(v0, v3);
        graph.addEdge(v0, v5);
        graph.addEdge(v0, v6);
        graph.addEdge(v0, v9);
        graph.addEdge(v1, v5);
        graph.addEdge(v1, v6);
        graph.addEdge(v2, v8);
        graph.addEdge(v3, v7);
        graph.addEdge(v3, v8);
        graph.addEdge(v5, v7);

        assertFalse(graph.isNeighbor(v0, v1));
        assertFalse(graph.isNeighbor(v0, v2));
        assertTrue(graph.isNeighbor(v0, v3));
        assertFalse(graph.isNeighbor(v0, v4));
        assertTrue(graph.isNeighbor(v0, v5));
        assertTrue(graph.isNeighbor(v0, v6));
        assertFalse(graph.isNeighbor(v0, v7));
        assertFalse(graph.isNeighbor(v0, v8));
        assertTrue(graph.isNeighbor(v0, v9));

        assertFalse(graph.isNeighbor(v1, v0));
        assertFalse(graph.isNeighbor(v1, v2));
        assertFalse(graph.isNeighbor(v1, v3));
        assertFalse(graph.isNeighbor(v1, v4));
        assertTrue(graph.isNeighbor(v1, v5));
        assertTrue(graph.isNeighbor(v1, v6));
        assertFalse(graph.isNeighbor(v1, v7));
        assertFalse(graph.isNeighbor(v1, v8));
        assertFalse(graph.isNeighbor(v1, v9));

        assertFalse(graph.isNeighbor(v2, v0));
        assertFalse(graph.isNeighbor(v2, v1));
        assertFalse(graph.isNeighbor(v2, v3));
        assertFalse(graph.isNeighbor(v2, v4));
        assertFalse(graph.isNeighbor(v2, v5));
        assertFalse(graph.isNeighbor(v2, v6));
        assertFalse(graph.isNeighbor(v2, v7));
        assertTrue(graph.isNeighbor(v2, v8));
        assertFalse(graph.isNeighbor(v2, v9));

        assertTrue(graph.isNeighbor(v3, v0));
        assertFalse(graph.isNeighbor(v3, v1));
        assertFalse(graph.isNeighbor(v3, v2));
        assertFalse(graph.isNeighbor(v3, v4));
        assertFalse(graph.isNeighbor(v3, v5));
        assertFalse(graph.isNeighbor(v3, v6));
        assertTrue(graph.isNeighbor(v3, v7));
        assertTrue(graph.isNeighbor(v3, v8));
        assertFalse(graph.isNeighbor(v3, v9));

        assertFalse(graph.isNeighbor(v4, v0));
        assertFalse(graph.isNeighbor(v4, v1));
        assertFalse(graph.isNeighbor(v4, v2));
        assertFalse(graph.isNeighbor(v4, v3));
        assertFalse(graph.isNeighbor(v4, v5));
        assertFalse(graph.isNeighbor(v4, v6));
        assertFalse(graph.isNeighbor(v4, v7));
        assertFalse(graph.isNeighbor(v4, v8));
        assertFalse(graph.isNeighbor(v4, v9));

        assertTrue(graph.isNeighbor(v5, v0));
        assertTrue(graph.isNeighbor(v5, v1));
        assertFalse(graph.isNeighbor(v5, v2));
        assertFalse(graph.isNeighbor(v5, v3));
        assertFalse(graph.isNeighbor(v5, v4));
        assertFalse(graph.isNeighbor(v5, v6));
        assertTrue(graph.isNeighbor(v5, v7));
        assertFalse(graph.isNeighbor(v5, v8));
        assertFalse(graph.isNeighbor(v5, v9));

        assertTrue(graph.isNeighbor(v6, v0));
        assertTrue(graph.isNeighbor(v6, v1));
        assertFalse(graph.isNeighbor(v6, v2));
        assertFalse(graph.isNeighbor(v6, v3));
        assertFalse(graph.isNeighbor(v6, v4));
        assertFalse(graph.isNeighbor(v6, v5));
        assertFalse(graph.isNeighbor(v6, v7));
        assertFalse(graph.isNeighbor(v6, v8));
        assertFalse(graph.isNeighbor(v6, v9));

        assertFalse(graph.isNeighbor(v7, v0));
        assertFalse(graph.isNeighbor(v7, v1));
        assertFalse(graph.isNeighbor(v7, v2));
        assertTrue(graph.isNeighbor(v7, v3));
        assertFalse(graph.isNeighbor(v7, v4));
        assertTrue(graph.isNeighbor(v7, v5));
        assertFalse(graph.isNeighbor(v7, v6));
        assertFalse(graph.isNeighbor(v7, v8));
        assertFalse(graph.isNeighbor(v7, v9));

        assertFalse(graph.isNeighbor(v8, v0));
        assertFalse(graph.isNeighbor(v8, v1));
        assertTrue(graph.isNeighbor(v8, v2));
        assertTrue(graph.isNeighbor(v8, v3));
        assertFalse(graph.isNeighbor(v8, v4));
        assertFalse(graph.isNeighbor(v8, v5));
        assertFalse(graph.isNeighbor(v8, v6));
        assertFalse(graph.isNeighbor(v8, v7));
        assertFalse(graph.isNeighbor(v8, v9));

        assertTrue(graph.isNeighbor(v9, v0));
        assertFalse(graph.isNeighbor(v9, v1));
        assertFalse(graph.isNeighbor(v9, v2));
        assertFalse(graph.isNeighbor(v9, v3));
        assertFalse(graph.isNeighbor(v9, v4));
        assertFalse(graph.isNeighbor(v9, v5));
        assertFalse(graph.isNeighbor(v9, v6));
        assertFalse(graph.isNeighbor(v9, v7));
        assertFalse(graph.isNeighbor(v9, v8));

        //
        // Test Depth First Search
        //

        List<Vertex> vertices = graph.depthFirstSearch(v0);
        assertEquals(9, vertices.size());

        assertEquals(0, vertices.get(0).getId());
        assertEquals(3, vertices.get(1).getId());
        assertEquals(7, vertices.get(2).getId());
        assertEquals(5, vertices.get(3).getId());
        assertEquals(1, vertices.get(4).getId());
        assertEquals(6, vertices.get(5).getId());
        assertEquals(8, vertices.get(6).getId());
        assertEquals(2, vertices.get(7).getId());
        assertEquals(9, vertices.get(8).getId());

        // The vertex 4 is disconnected from the rest of graph
        // so it should not appear when the DFS algorithm starts
        // on the vertex 0

        vertices = graph.depthFirstSearch(v4);
        assertEquals(1, vertices.size());
        assertEquals(4, vertices.get(0).getId());

        vertices = graph.depthFirstSearch(v2);
        assertEquals(9, vertices.size());

        assertEquals(2, vertices.get(0).getId());
        assertEquals(8, vertices.get(1).getId());
        assertEquals(3, vertices.get(2).getId());
        assertEquals(0, vertices.get(3).getId());
        assertEquals(5, vertices.get(4).getId());
        assertEquals(1, vertices.get(5).getId());
        assertEquals(6, vertices.get(6).getId());
        assertEquals(7, vertices.get(7).getId());
        assertEquals(9, vertices.get(8).getId());

        //
        // Test Breadth First Search
        //

        vertices = graph.breadthFirstSearch(v0);
        assertEquals(9, vertices.size());

        assertEquals(0, vertices.get(0).getId());
        assertEquals(3, vertices.get(1).getId());
        assertEquals(5, vertices.get(2).getId());
        assertEquals(6, vertices.get(3).getId());
        assertEquals(9, vertices.get(4).getId());
        assertEquals(7, vertices.get(5).getId());
        assertEquals(8, vertices.get(6).getId());
        assertEquals(1, vertices.get(7).getId());
        assertEquals(2, vertices.get(8).getId());

        vertices = graph.breadthFirstSearch(v4);
        assertEquals(1, vertices.size());
        assertEquals(4, vertices.get(0).getId());

        vertices = graph.breadthFirstSearch(v2);
        assertEquals(9, vertices.size());

        assertEquals(2, vertices.get(0).getId());
        assertEquals(8, vertices.get(1).getId());
        assertEquals(3, vertices.get(2).getId());
        assertEquals(0, vertices.get(3).getId());
        assertEquals(7, vertices.get(4).getId());
        assertEquals(5, vertices.get(5).getId());
        assertEquals(6, vertices.get(6).getId());
        assertEquals(9, vertices.get(7).getId());
        assertEquals(1, vertices.get(8).getId());

    }
}
