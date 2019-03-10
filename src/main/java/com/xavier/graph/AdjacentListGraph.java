package com.xavier.graph;

import java.util.*;

abstract class AdjacentListGraph implements Graph {

    private HashMap<Vertex, List<Edge>> connections;

    AdjacentListGraph() {
        connections = new HashMap<>();
    }


    public boolean isVertexInGraph(Vertex vertex)
    {
        return connections.containsKey(vertex);
    }

    public boolean isNeighbor(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !isVertexInGraph(origin) ||
                !isVertexInGraph(destination)) {
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
        if (! isVertexInGraph(vertex)) {
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
        if (isVertexInGraph(vertex)) {
            return;
        }

        connections.put(vertex, new ArrayList<Edge>());
    }

    public void removeVertex(Vertex vertex)
    {
        if (! isVertexInGraph(vertex)) {
            return;
        }

        connections.remove(vertex);
    }

    public void addEdge(Edge edge) throws VertexNotFoundException
    {
        if ( !isVertexInGraph(edge.origin) ||
                !isVertexInGraph(edge.destination)) {
            throw new VertexNotFoundException();
        }

        List<Edge> vertexEdges = connections.get(edge.origin);
        vertexEdges.add(edge);
    }

    public void removeEdge(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !isVertexInGraph(origin) ||
                !isVertexInGraph(destination)) {
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
