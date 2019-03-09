package com.xavier.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class AdjacentListGraph implements Graph {

    private HashMap<Vertex, List<Edge>> connections;

    AdjacentListGraph() {
        connections = new HashMap<>();
    }

    public boolean isNeighbor(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !connections.containsKey(origin) ||
                !connections.containsKey(destination)) {
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
        if (! connections.containsKey(vertex)) {
            throw new VertexNotFoundException();
        }

        List<Vertex> neighbors = new ArrayList<>();

        List<Edge> vertexEdges = connections.get(vertex);
        for (Edge edge : vertexEdges) {
            neighbors.add(edge.destination);
        }

        return neighbors;
    }

    public void addVertex(Vertex vertex)
    {
        if (connections.containsKey(vertex)) {
            return;
        }

        connections.put(vertex, new ArrayList<Edge>());
    }

    public void removeVertex(Vertex vertex)
    {
        if (! connections.containsKey(vertex)) {
            return;
        }

        connections.remove(vertex);
    }

    public void addEdge(Edge edge) throws VertexNotFoundException
    {
        if ( !connections.containsKey(edge.origin) ||
                !connections.containsKey(edge.destination)) {
            throw new VertexNotFoundException();
        }

        List<Edge> vertexEdges = connections.get(edge.origin);
        vertexEdges.add(edge);
    }

    public void removeEdge(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !connections.containsKey(origin) ||
                !connections.containsKey(destination)) {
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
