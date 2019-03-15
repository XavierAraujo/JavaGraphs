package com.xavier.graphs.parsers;

import com.xavier.graphs.Graph;
import com.xavier.graphs.Vertex;
import com.xavier.graphs.exceptions.InvalidGraphFileException;
import com.xavier.graphs.parsers.GraphJsonFileParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GraphJsonFileParserTest {


    @Test
    void basicFileTest() throws IOException, InvalidGraphFileException {
        GraphJsonFileParser parser = new GraphJsonFileParser();

        Graph graph = parser.fetchGraphFromFile("src/test/resources/com/xavier/graphs/test_graph1.json");

        graph.getVertices();

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

    }
}
