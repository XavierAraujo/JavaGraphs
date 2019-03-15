package com.xavier.graphs.adjacent_list_graphs;

import com.xavier.graphs.Edge;
import com.xavier.graphs.Graph;
import com.xavier.graphs.Vertex;
import com.xavier.graphs.exceptions.VertexNotFoundException;

import java.util.*;

abstract class AdjacentListGraph implements Graph {

    private HashMap<Vertex, List<Edge>> connections;

    AdjacentListGraph() {
        connections = new HashMap<>();
    }


    public boolean containsVertex(Vertex vertex)
    {
        return connections.containsKey(vertex);
    }

    public boolean areVerticesNeighbors(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !containsVertex(origin) ||
                !containsVertex(destination)) {
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

    public List<Vertex> getVertexNeighbors(Vertex vertex) throws VertexNotFoundException
    {
        if (! containsVertex(vertex)) {
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
        if (containsVertex(vertex)) {
            return;
        }

        connections.put(vertex, new ArrayList<>());
    }

    public void removeVertex(Vertex vertex)
    {
        if (! containsVertex(vertex)) {
            return;
        }

        connections.remove(vertex);
    }

    public List<Edge> getEdges(Vertex vertex) throws VertexNotFoundException
    {
        if (! containsVertex(vertex)) {
            throw new VertexNotFoundException();
        }

        return connections.get(vertex);
    }

    public List<Edge> getEdgesBetweenVertices(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !containsVertex(origin) ||
                !containsVertex(destination)) {
            throw new VertexNotFoundException();
        }

        ArrayList<Edge> edges = new ArrayList<>();
        List<Edge> originEdges = connections.get(origin);

        for (Edge edge : originEdges) {
            if(edge.destination.equals(destination)) {
                edges.add(edge);
            }
        }
        return edges;
    }


    public void addEdge(Edge edge) throws VertexNotFoundException
    {
        if ( !containsVertex(edge.origin) ||
                !containsVertex(edge.destination)) {
            throw new VertexNotFoundException();
        }

        if (edge.origin.equals(edge.destination)) {
            // Not accepted self-loops
            return;
        }

        List<Edge> vertexEdges = connections.get(edge.origin);
        if (existEdgeForDestination(vertexEdges, edge.destination)) {
            return;
        }
        vertexEdges.add(edge);
    }

    public void removeEdge(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if ( !containsVertex(origin) ||
                !containsVertex(destination)) {
            throw new VertexNotFoundException();
        }

        List<Edge> originEdges = connections.get(origin);
        Edge edge = getEdgeForDestination(originEdges, destination);
        if (edge != null) {
            originEdges.remove(edge);
        }
    }

    private boolean existEdgeForDestination(List<Edge> edges, Vertex destination)
    {
        return (getEdgeForDestination(edges, destination) != null);
    }

    private Edge getEdgeForDestination(List<Edge> edges, Vertex destination)
    {
        for (Edge edge : edges) {
            if (edge.destination.equals(destination)) {
                return edge;
            }
        }
        return null;
    }
}
