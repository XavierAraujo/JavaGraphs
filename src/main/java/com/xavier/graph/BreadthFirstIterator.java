package com.xavier.graph;

import java.util.*;

public class BreadthFirstIterator implements Iterator<Vertex> {

    private Graph graph;
    private HashSet<Vertex> visitedVertices;
    private ArrayDeque<Vertex> visitationQueue;

    private Vertex next;

    BreadthFirstIterator(Graph graph, Vertex startingVertex)
    {
        this.graph = graph;
        this.visitedVertices = new HashSet<>();
        this.visitationQueue = new ArrayDeque<>();

        this.next = startingVertex;
        addNeighborsToVisitationQueue(startingVertex);
        visitedVertices.add(startingVertex);
    }

    public boolean hasNext() {
        return (next != null);
    }

    public Vertex next()
    {
        Vertex nextVertex = next;
        next = getNextVertex();
        return nextVertex;
    }

    private Vertex getNextVertex()
    {
        Vertex vertex = visitationQueue.pollFirst();
        if (vertex == null) {
            return null;
        }

        addNeighborsToVisitationQueue(vertex);
        visitedVertices.add(vertex);
        return vertex;
    }

    private void addNeighborsToVisitationQueue(Vertex vertex)
    {
        List<Vertex> neighbors = null;
        try {
            neighbors = graph.getNeighbors(vertex);
        } catch (VertexNotFoundException e) {
            e.printStackTrace();
            return;
        }

        for(Vertex neighbor : neighbors)
        {
            if (!visitedVertices.contains(neighbor)) {
                // Only add vertices which were not yet added to the queue
                visitationQueue.add(neighbor);
                visitedVertices.add(neighbor);
            }
        }
    }
}
