package com.xavier.graph;

import java.util.*;

abstract class AdjacentListGraph implements Graph {

    private HashMap<Vertex, List<Edge>> connections;

    AdjacentListGraph() {
        connections = new HashMap<>();
    }


    public boolean contains(Vertex vertex)
    {
        return connections.containsKey(vertex);
    }

    public boolean isNeighbor(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !contains(origin) ||
                !contains(destination)) {
            throw new VertexNotFoundException();
        }

        List<Edge> originEdges = connections.get(origin);
        for (Edge edge : originEdges)
        {
            if (edge.destination.equals(destination)) {
                return true;
            }
        }

        return false;
    }

    public List<Vertex> getNeighbors(Vertex vertex) throws VertexNotFoundException
    {
        if (! contains(vertex)) {
            throw new VertexNotFoundException();
        }

        List<Vertex> neighbors = new ArrayList<>();

        List<Edge> vertexEdges = connections.get(vertex);
        for (Edge edge : vertexEdges) {
            neighbors.add(edge.destination);
        }

        return neighbors;
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(connections.keySet());
    }

    public void addVertex(Vertex vertex)
    {
        if (contains(vertex)) {
            return;
        }

        connections.put(vertex, new ArrayList<Edge>());
    }

    public void removeVertex(Vertex vertex)
    {
        if (! contains(vertex)) {
            return;
        }

        connections.remove(vertex);
    }

    public List<Edge> getEdges(Vertex vertex) throws VertexNotFoundException
    {
        if (! contains(vertex)) {
            throw new VertexNotFoundException();
        }

        return connections.get(vertex);
    }

    public void addEdge(Edge edge) throws VertexNotFoundException
    {
        if ( !contains(edge.origin) ||
                !contains(edge.destination)) {
            throw new VertexNotFoundException();
        }

        List<Edge> vertexEdges = connections.get(edge.origin);
        vertexEdges.add(edge);
    }

    public void removeEdge(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !contains(origin) ||
                !contains(destination)) {
            throw new VertexNotFoundException();
        }

        List<Edge> originEdges = connections.get(origin);
        for (Edge edge : originEdges) {
            if (edge.destination.equals(destination)) {
                originEdges.remove(edge);
                break;
            }
        }
    }
}
