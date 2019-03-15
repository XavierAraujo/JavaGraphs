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

    /**
     * Creates a breadth first graph iterator that starts iterating
     * from the specified starting vertex.
     * @param graph Graph to be iterated.
     * @param startingVertex Vertex from which the iteration should be
     *                       started.
     * @throws VertexNotFoundException Throws this exception if the
     *          starting vertex is not found.
     */
    BreadthFirstIterator(Graph graph, Vertex startingVertex) throws VertexNotFoundException
    {
        if ( !graph.containsVertex(startingVertex)) {
            throw new VertexNotFoundException();
        }

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

    /**
     * This method fetches the next vertex to be iterated, marks it
     * as already iterated and adds its neighbors to the iteration
     * queue. The queue is a FIFO data structure (First-In-First-Out)
     * @return Returns the next vertex to be iterated.
     */
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
    /**
     * Adds the neighbors of the specified vertex to the iteration
     * queue. The queue is a FIFO data structure (First-In-First-Out)
     * @param vertex Vertex to be considered.
     */
    private void addNeighborsToIterationQueue(Vertex vertex)
    {
        List<Vertex> neighbors = null;
        try {
            neighbors = graph.getVertexNeighbors(vertex);
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
