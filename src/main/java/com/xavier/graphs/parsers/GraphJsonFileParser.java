package com.xavier.graphs.parsers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.xavier.graphs.Edge;
import com.xavier.graphs.Graph;
import com.xavier.graphs.Vertex;
import com.xavier.graphs.WeightedEdge;
import com.xavier.graphs.adjacent_list_graphs.AdjacentListGraphFactory;
import com.xavier.graphs.exceptions.InvalidGraphFileException;
import com.xavier.graphs.exceptions.VertexNotFoundException;

import java.io.*;
import java.util.HashSet;
import java.util.List;

public class GraphJsonFileParser implements GraphFileParser {

    GraphJsonFileParser() {

    }

    @Override
    public Graph fetchGraphFromFile(String filename) throws IOException, InvalidGraphFileException {
        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            Gson gson = new Gson();
            JsonGraph jsonGraph = gson.fromJson(reader, JsonGraph.class);
            return jsonGraph.toGraph();
        }
    }

    /**
     * This class is used by GSON when parsing a graph from a JSON file.
     */
    static class JsonGraph {

        private boolean isDirected;
        private boolean isWeighted;
        private List<JsonGraphVertex> vertices;
        private List<JsonGraphEdge> edges;


        Graph toGraph() throws InvalidGraphFileException
        {
            AdjacentListGraphFactory graphFactory = new AdjacentListGraphFactory();
            Graph graph = graphFactory.getGraph(isDirected, isWeighted);

            addVerticesToGraph(graph);
            addEdgesToGraph(graph);

            return graph;
        }

        private void addVerticesToGraph(Graph graph)
        {
            HashSet<Integer> verticesSet = new HashSet<>();
            for (JsonGraphVertex jsonGraphVertex : vertices) {
                if (verticesSet.contains(jsonGraphVertex.id)) {
                    // Avoid adding repeated vertices to the graph.
                    continue;
                }
                verticesSet.add(jsonGraphVertex.id);
                graph.addVertex(jsonGraphVertex.toVertex());
            }
        }

        private void addEdgesToGraph(Graph graph) throws InvalidGraphFileException {
            for (JsonGraphEdge graphEdge : edges) {
                Edge edge = graphEdge.toEdge(isWeighted);

                if (!graph.containsVertex(edge.origin)
                        || !graph.containsVertex(edge.destination)) {
                    throw new InvalidGraphFileException("Edge connecting non existent vertices");
                }

                try {
                    graph.addEdge(edge);
                } catch (VertexNotFoundException e) {
                    // Not supposed to happen due to if statement above
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This class is used by GSON when parsing a vertex from a JSON file.
     */
    static class JsonGraphVertex {

        private int id;

        Vertex toVertex() {
            return new Vertex(id);
        }
    }

    /**
     * This class is used by GSON when parsing an edge from a JSON file.
     */
    static class JsonGraphEdge {

        private JsonGraphVertex origin;
        private JsonGraphVertex destination;
        private int weight;

        Edge toEdge(boolean isWeighted)
        {
            if (isWeighted) {
                return new WeightedEdge(origin.toVertex(), destination.toVertex(), weight);
            }
            else{
                return new Edge(origin.toVertex(), destination.toVertex());
            }
        }
    }
}
