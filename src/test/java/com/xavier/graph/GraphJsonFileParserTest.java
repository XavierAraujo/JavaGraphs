package com.xavier.graph;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GraphJsonFileParserTest {


    @Test
    void basicFileTest() throws IOException, InvalidGraphFileException {
        GraphJsonFileParser parser = new GraphJsonFileParser();

        Graph graph = parser.fetchGraphFromFile("src/test/resources/com/xavier/graph/test_graph1.json");

        graph.getVertices();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);

        assertTrue(graph.contains(v1));
        assertTrue(graph.contains(v2));
        assertTrue(graph.contains(v3));
        assertTrue(graph.contains(v4));
        assertTrue(graph.contains(v5));
        assertTrue(graph.contains(v6));
        assertTrue(graph.contains(v7));
        assertTrue(graph.contains(v8));

    }
}
