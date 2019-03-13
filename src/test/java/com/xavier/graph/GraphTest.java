package com.xavier.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void breadthFirstIteratorTest() throws VertexNotFoundException
    {
        UndirectedAdjacentListGraph graph = new UndirectedAdjacentListGraph();

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

        List<Vertex> vertices = graph.breadthFirstIteration(v0);
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

        vertices = graph.breadthFirstIteration(v4);
        assertEquals(1, vertices.size());
        assertEquals(4, vertices.get(0).getId());

        Vertex v999 = new Vertex(999);
        assertThrows(VertexNotFoundException.class, () -> graph.breadthFirstIteration(v999));
    }

    @Test
    void depthFirstIteratorTest() throws VertexNotFoundException
    {
        UndirectedAdjacentListGraph graph = new UndirectedAdjacentListGraph();

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

        List<Vertex> vertices = graph.depthFirstIteration(v0);
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

        vertices = graph.depthFirstIteration(v4);
        assertEquals(1, vertices.size());
        assertEquals(4, vertices.get(0).getId());

        Vertex v999 = new Vertex(999);
        assertThrows(VertexNotFoundException.class, () -> graph.depthFirstIteration(v999));
    }
}
