package com.xavier.graphs;

import com.xavier.graphs.adjacent_list_graphs.UndirectedAdjacentListGraph;
import com.xavier.graphs.exceptions.VertexNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepthFirstIteratorTest {

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

        // Undirected graphs :: Edge(v0,v1)=Edge(v1,v0) :: v0 <--> v1
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

        DepthFirstIterator it;

        // Iteration starting on v0
        it = new DepthFirstIterator(graph, v0);
        assertEquals(0, it.next().getId());
        assertEquals(3, it.next().getId());
        assertEquals(7, it.next().getId());
        assertEquals(5, it.next().getId());
        assertEquals(1, it.next().getId());
        assertEquals(6, it.next().getId());
        assertEquals(8, it.next().getId());
        assertEquals(2, it.next().getId());
        assertEquals(9, it.next().getId());
        assertNull(it.next());

        // Iteration starting on v4
        it = new DepthFirstIterator(graph, v4);
        assertEquals(4, it.next().getId());
        assertNull(it.next());

        // Iteration starting on v2
        it = new DepthFirstIterator(graph, v2);
        assertEquals(2, it.next().getId());
        assertEquals(8, it.next().getId());
        assertEquals(3, it.next().getId());
        assertEquals(0, it.next().getId());
        assertEquals(5, it.next().getId());
        assertEquals(1, it.next().getId());
        assertEquals(6, it.next().getId());
        assertEquals(7, it.next().getId());
        assertEquals(9, it.next().getId());
        assertNull(it.next());

        Vertex v999 = new Vertex(999);
        assertThrows(VertexNotFoundException.class, () -> new DepthFirstIterator(graph, v999));
    }
}
