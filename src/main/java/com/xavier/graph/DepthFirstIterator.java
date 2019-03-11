package com.xavier.graph;

import java.util.*;

/**
 * This class implements a depth first graph iterator. It uses
 * an auxiliary stack (FILO: First-In-Last-Out) to manage the order
 * in which the nodes are iterated.
 */
public class DepthFirstIterator implements Iterator<Vertex> {

    private Graph graph;
    private HashSet<Vertex> iteratedVertices;
    private ArrayDeque<Vertex> iterationStack;

    private Vertex next;

    /**
     * Creates a depth first iterator . It assumes that the specified
     * starting vertex belongs to the given graph and so it does not
     * do any kind of validation.
     * @param graph Graph to be iterated.
     * @param startingVertex Vertex from which the iteration should be
     *                       started.
     */
    DepthFirstIterator(Graph graph, Vertex startingVertex)
    {
        this.graph = graph;
        this.iteratedVertices = new HashSet<>();
        this.iterationStack = new ArrayDeque<>();

        this.next = startingVertex;
        addNeighborsToIterationStack(startingVertex);
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
     * stack
     * @return Returns the next vertex to be iterated.
     */
    private Vertex getNextVertex()
    {
        Vertex vertex = popNonIteratedVertexFromStack();
        if (vertex == null) {
            return null;
        }

        iteratedVertices.add(vertex);
        addNeighborsToIterationStack(vertex);
        return vertex;
    }

    /**
     * This method fetches the next vertex to be iterated
     * from the auxiliary stack and sets it as iterated. It
     * discards vertices which were already iterated. A vertex
     * can be added multiple times to the iteration stack if it
     * is neighbor from multiple vertices.
     * @return Returns the next non iterated vertex from the stack.
     */
    private Vertex popNonIteratedVertexFromStack()
    {
        while (iterationStack.size() > 0) {
            Vertex vertex = iterationStack.pop();
            if (iteratedVertices.contains(vertex)) {
                continue; // Node already iterated. Ignoring.
            }

            return vertex;
        }
        return null;
    }

    /**
     * Adds the neighbors of the specified vertex to the iteration
     * stack
     * @param vertex Vertex to be considered.
     */
    private void addNeighborsToIterationStack(Vertex vertex)
    {
        List<Vertex> neighbors = null;
        try {
            neighbors = graph.getNeighbors(vertex);
        } catch (VertexNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Use an iterator to iterate the vertex list in reverse order
        // to begin by analyze the first neighbors being inserted.
        ListIterator it = neighbors.listIterator(neighbors.size());
        while (it.hasPrevious()) {
            Vertex neighbor = (Vertex) it.previous();
            iterationStack.push(neighbor);
        }
    }
}