package com.xavier.graph;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.HashSet;
import java.util.List;

public class GraphJsonFileParser implements GraphFileParser {

    GraphJsonFileParser() {

    }

    @Override
    public Graph fetchGraphFromFile(String filename) throws FileNotFoundException, IOException {
        try (JsonReader reader = new JsonReader(new FileReader(filename));) {
            Gson gson = new Gson();
            JsonGraph jsonGraph = gson.fromJson(reader, JsonGraph.class);
            AdjacentListGraphFactory graphFactory = new AdjacentListGraphFactory();
            Graph graph = graphFactory.getGraph(jsonGraph.getIsDirected(), jsonGraph.getIsWeighted());

            for (JsonGraphVertex graphVertex: jsonGraph.vertices) {
                graph.addVertex(new Vertex(graphVertex.id));
            }

            for (JsonGraphEdge graphEdge: jsonGraph.edges) {
                Vertex origin  = new Vertex(graphEdge.origin);
                Vertex destination  = new Vertex(graphEdge.destination);

                if(!graph.contains(new Vertex(graphEdge.origin)) ||
                    !graph.contains(new Vertex(graphEdge.destination))){
                        continue; // TODO: Create specific exception
                }

                try {
                    graph.addEdge(new Edge(origin, destination));
                } catch (VertexNotFoundException e) {
                    e.printStackTrace();
                }
            }


            return graph;
        }
    }

    static class JsonGraph {

        boolean isDirected;
        boolean isWeighted;
        List<JsonGraphVertex> vertices;
        List<JsonGraphEdge> edges;

        JsonGraph(){

        }

        public boolean getIsDirected() {
            return isDirected;
        }

        public void setIsDirected(boolean isDirected) {
            this.isDirected = isDirected;
        }

        public boolean getIsWeighted() {
            return isWeighted;
        }

        public void setWeighted(boolean isWeighted) {
            this.isWeighted = isWeighted;
        }

        public List<JsonGraphVertex> getVertices() {
            return vertices;
        }

        public void setVertices(List<JsonGraphVertex> vertices) {
            this.vertices = vertices;
        }

        public List<JsonGraphEdge> getEdges() {
            return edges;
        }

        public void setEdges(List<JsonGraphEdge> edges) {
            this.edges = edges;
        }
    }

    static class JsonGraphVertex {

        int id;

        JsonGraphVertex() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    static class JsonGraphEdge {

        int origin;
        int destination;
        int weight;

        JsonGraphEdge() {

        }

        public int getOrigin() {
            return origin;
        }

        public void setOrigin(int origin) {
            this.origin = origin;
        }

        public int getDestination() {
            return destination;
        }

        public void setDestination(int destination) {
            this.destination = destination;
        }

    }
}
