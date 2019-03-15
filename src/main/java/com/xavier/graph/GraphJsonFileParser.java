package com.xavier.graph;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.List;

public class GraphJsonFileParser implements GraphFileParser {

    GraphJsonFileParser() {

    }

    @Override
    public Graph fetchGraphFromFile(String filename) throws FileNotFoundException, IOException, InvalidGraphFileException {
        try (JsonReader reader = new JsonReader(new FileReader(filename));) {
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

        JsonGraph(){

        }

        boolean getIsDirected() {
            return isDirected;
        }

        boolean getIsWeighted() {
            return isWeighted;
        }

        List<JsonGraphVertex> getVertices() {
            return vertices;
        }

        List<JsonGraphEdge> getEdges() {
            return edges;
        }

        Graph toGraph() throws InvalidGraphFileException
        {
            AdjacentListGraphFactory graphFactory = new AdjacentListGraphFactory();
            Graph graph = graphFactory.getGraph(this.getIsDirected(), this.getIsWeighted());

            for (JsonGraphVertex graphVertex : this.vertices) {
                graph.addVertex(graphVertex.toVertex());
            }

            for (JsonGraphEdge graphEdge : this.edges) {
                Edge edge = graphEdge.toEdge(this.isWeighted);

                if (!graph.contains(edge.origin)
                        || !graph.contains(edge.destination)) {
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

        JsonGraphVertex() {

        }

        int getId() {
            return id;
        }

        Vertex toVertex() {
            return new Vertex(this.id);
        }
    }

    static class JsonGraphEdge {

        JsonGraphVertex origin;
        JsonGraphVertex destination;
        int weight;

        JsonGraphEdge() {

        }

        JsonGraphVertex getOrigin() {
            return origin;
        }

        JsonGraphVertex getDestination() {
            return destination;
        }

        Edge toEdge(boolean isWeighted)
        {
            if (isWeighted) {
                return new WeightedEdge(this.origin.toVertex(), this.destination.toVertex(), this.weight);
            }
            else{
                return new Edge(this.origin.toVertex(), this.destination.toVertex());
            }
        }
    }
}
