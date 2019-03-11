package com.xavier.graph;

import java.util.*;

/**
 * This class implements a breadth first graph iterator. It uses
 * an auxiliary queue (FIFO: First-In-First-Out) to manage the order
 * in which the nodes are iterated.
 */
public class BreadthFirstIterator implements Iterator<Vertex> {

    private Graph graph;
    private HashSet<Vertex> iteratedVertices;
    private ArrayDeque<Vertex> iterationQueue;

    private Vertex next;

    BreadthFirstIterator(Graph graph, Vertex startingVertex)
    {
        this.graph = graph;
        this.iteratedVertices = new HashSet<>();
        this.iterationQueue = new ArrayDeque<>();

        this.next = startingVertex;
        addNeighborsToIterationQueue(startingVertex);
        iteratedVertices.add(startingVertex);
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
        Vertex vertex = iterationQueue.pollFirst();
        if (vertex == null) {
            return null;
        }

        addNeighborsToIterationQueue(vertex);
        iteratedVertices.add(vertex);
        return vertex;
    }

    private void addNeighborsToIterationQueue(Vertex vertex)
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
            if (!iteratedVertices.contains(neighbor)) {
                // Only add vertices which were not yet added to the queue
                iterationQueue.add(neighbor);
                iteratedVertices.add(neighbor);
            }
        }
    }
}
