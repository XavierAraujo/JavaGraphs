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

    static class JsonGraph {

        boolean isDirected;
        boolean isWeighted;
        List<JsonGraphVertex> vertices;
        List<JsonGraphEdge> edges;

        JsonGraph(){ }

        Graph toGraph() throws InvalidGraphFileException
        {
            AdjacentListGraphFactory graphFactory = new AdjacentListGraphFactory();
            Graph graph = graphFactory.getGraph(isDirected, isWeighted);

            for (JsonGraphVertex graphVertex : vertices) {
                graph.addVertex(graphVertex.toVertex());
            }

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

            return graph;
        }
    }

    static class JsonGraphVertex {

        int id;

        JsonGraphVertex() { }

        Vertex toVertex() {
            return new Vertex(id);
        }
    }

    static class JsonGraphEdge {

        JsonGraphVertex origin;
        JsonGraphVertex destination;
        int weight;

        JsonGraphEdge() { }

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
