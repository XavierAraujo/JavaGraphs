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

    public List<Vertex> depthFirstSearch(Vertex startingVertex) throws VertexNotFoundException
    {
        if ( !isVertexInGraph(startingVertex)) {
            throw new VertexNotFoundException();
        }

        List<Vertex> vertices = new ArrayList<>();
        HashSet<Vertex> visitationMap = new HashSet<>();
        ArrayDeque<Vertex> visitationStack = new ArrayDeque<>();

        visitationStack.push(startingVertex);

        while (visitationStack.size() > 0)
        {
            Vertex vertex = visitationStack.pop();
            if (visitationMap.contains(vertex)) {
                continue;
            }

            visitationMap.add(vertex);
            vertices.add(vertex);

            List<Vertex> neighbors = getNeighbors(vertex);

            // Use an iterator to iterate the vertex list in reverse order
            // to consider the most first neighbors being inserted.
            ListIterator it = neighbors.listIterator(neighbors.size());
            while (it.hasPrevious()) {
                Vertex neighbor = (Vertex) it.previous();
                if (! visitationMap.contains(vertex)) {
                    continue;
                }
                visitationStack.push(neighbor);
            }

        }

        return vertices;
    }

    private List<Vertex> recursiveDepthFirstSearch(Vertex vertex, HashSet<Vertex> visitationMap) throws VertexNotFoundException
    {
        List<Vertex> vertices = new ArrayList<>();

        // Add current vertex and mark it as visited.
        vertices.add(vertex);
        visitationMap.add(vertex);

        List<Vertex> neighbors = getNeighbors(vertex);
        for (Vertex neighbor: neighbors)
        {
            if (visitationMap.contains(neighbor)) {
                // This vertex was already visited so we don't need to visit again
                continue;
            }
            List<Vertex> neighborVertices = recursiveDepthFirstSearch(neighbor, visitationMap);
            vertices.addAll(neighborVertices);
        }

        return vertices;
    }

    public List<Vertex> breadthFirstSearch(Vertex startingVertex) throws VertexNotFoundException
    {
        if ( !isVertexInGraph(startingVertex)) {
            throw new VertexNotFoundException();
        }

        List<Vertex> vertices = new ArrayList<>();
        HashSet<Vertex> visitationMap = new HashSet<>();
        ArrayDeque<Vertex> visitationQueue = new ArrayDeque<>();

        visitationQueue.add(startingVertex);

        while (visitationQueue.size() > 0)
        {
            Vertex vertex = visitationQueue.pollFirst();
            if (visitationMap.contains(vertex)) {
                continue;
            }

            visitationMap.add(vertex);
            vertices.add(vertex);

            List<Vertex> neighbors = getNeighbors(vertex);
            for (Vertex neighbor: neighbors)
            {
                if (! visitationMap.contains(vertex)) {
                    continue;
                }
                visitationQueue.add(neighbor);
            }

        }

        return vertices;
    }
}
