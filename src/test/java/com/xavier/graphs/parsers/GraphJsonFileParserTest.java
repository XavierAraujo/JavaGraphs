package com.xavier.graphs.parsers;

import com.xavier.graphs.Edge;
import com.xavier.graphs.Graph;
import com.xavier.graphs.Vertex;
import com.xavier.graphs.WeightedEdge;
import com.xavier.graphs.adjacent_list_graphs.WeightedUndirectedAdjacentListGraph;
import com.xavier.graphs.adjacent_list_graphs.UndirectedAdjacentListGraph;
import com.xavier.graphs.adjacent_list_graphs.DirectedAdjacentListGraph;
import com.xavier.graphs.adjacent_list_graphs.WeightedDirectedAdjacentListGraph;
import com.xavier.graphs.exceptions.InvalidGraphFileException;
import com.xavier.graphs.exceptions.VertexNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphJsonFileParserTest {

    private final static String TEST_FILES_DIR = "src/test/resources/com/xavier/graphs";

    @Test
    void weightedDirectedGraph1Test() throws IOException, InvalidGraphFileException, VertexNotFoundException {
        GraphJsonFileParser parser = new GraphJsonFileParser();
        Graph graph = parser.fetchGraphFromFile(String.format("%s/WeightedDirectedTestGraph1.json", TEST_FILES_DIR));

        assertTrue(graph instanceof WeightedDirectedAdjacentListGraph);
        assertEquals(8, graph.getVertices().size());

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);

        assertTrue(graph.containsVertex(v1));
        assertTrue(graph.containsVertex(v2));
        assertTrue(graph.containsVertex(v3));
        assertTrue(graph.containsVertex(v4));
        assertTrue(graph.containsVertex(v5));
        assertTrue(graph.containsVertex(v6));
        assertTrue(graph.containsVertex(v7));
        assertTrue(graph.containsVertex(v8));

        assertTrue(graph.areVerticesNeighbors(v1, v2));
        assertFalse(graph.areVerticesNeighbors(v2, v1));
        assertTrue(graph.areVerticesNeighbors(v1, v8));
        assertFalse(graph.areVerticesNeighbors(v8, v1));
        assertTrue(graph.areVerticesNeighbors(v3, v5));
        assertFalse(graph.areVerticesNeighbors(v5, v3));
        assertTrue(graph.areVerticesNeighbors(v5, v4));
        assertTrue(graph.areVerticesNeighbors(v4, v5));

        List<Edge> edges = graph.getEdgesBetweenVertices(v1,v2);
        assertEquals(1, edges.size());
        WeightedEdge edge = (WeightedEdge) edges.get(0);
        assertEquals(2, edge.getWeight());

        edges = graph.getEdgesBetweenVertices(v1,v8);
        assertEquals(1, edges.size());
        edge = (WeightedEdge) edges.get(0);
        assertEquals(10, edge.getWeight());

        edges = graph.getEdgesBetweenVertices(v3,v5);
        assertEquals(1, edges.size());
        edge = (WeightedEdge) edges.get(0);
        assertEquals(15, edge.getWeight());

        edges = graph.getEdgesBetweenVertices(v5,v4);
        assertEquals(1, edges.size());
        edge = (WeightedEdge) edges.get(0);
        assertEquals(9, edge.getWeight());

        edges = graph.getEdgesBetweenVertices(v4,v5);
        assertEquals(1, edges.size());
        edge = (WeightedEdge) edges.get(0);
        assertEquals(8, edge.getWeight());
    }

    @Test
    void directedGraph1Test() throws IOException, InvalidGraphFileException
    {
        GraphJsonFileParser parser = new GraphJsonFileParser();
        Graph graph = parser.fetchGraphFromFile(String.format("%s/DirectedTestGraph1.json", TEST_FILES_DIR));

        assertTrue(graph instanceof DirectedAdjacentListGraph);
        assertEquals(8, graph.getVertices().size());
    }

    @Test
    void weightedUndirectedGraph1Test() throws IOException, InvalidGraphFileException
    {
        GraphJsonFileParser parser = new GraphJsonFileParser();
        Graph graph = parser.fetchGraphFromFile(String.format("%s/WeightedUndirectedTestGraph1.json", TEST_FILES_DIR));

        assertTrue(graph instanceof WeightedUndirectedAdjacentListGraph);
        assertEquals(8, graph.getVertices().size());
    }

    @Test
    void undirectedGraph1Test() throws IOException, InvalidGraphFileException
    {
        GraphJsonFileParser parser = new GraphJsonFileParser();
        Graph graph = parser.fetchGraphFromFile(String.format("%s/UndirectedTestGraph1.json", TEST_FILES_DIR));

        assertTrue(graph instanceof UndirectedAdjacentListGraph);
        assertEquals(8, graph.getVertices().size());
    }

    @Test
    void testEmptyJson()
    {
        GraphJsonFileParser parser = new GraphJsonFileParser();
        assertThrows(InvalidGraphFileException.class, () -> parser.fetchGraphFromFile(String.format("%s/Empty.json", TEST_FILES_DIR)));
    }

    @Test
    void testInvalidJson()
    {
        GraphJsonFileParser parser = new GraphJsonFileParser();
        assertThrows(InvalidGraphFileException.class, () -> parser.fetchGraphFromFile(String.format("%s/Invalid.json", TEST_FILES_DIR)));
    }

    @Test
    void testUndefinedJson() throws IOException, InvalidGraphFileException
    {
        GraphJsonFileParser parser = new GraphJsonFileParser();
        Graph graph = parser.fetchGraphFromFile(String.format("%s/UndefinedTestGraph.json", TEST_FILES_DIR));

        // By default if no IsDirected and no IsWeighted flag is defined on the
        // JSON file it is assumed that it is a non weighted undirected graph.
        assertTrue(graph instanceof UndirectedAdjacentListGraph);
        assertEquals(8, graph.getVertices().size());
    }
}
