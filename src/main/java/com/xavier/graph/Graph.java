package com.xavier.graph;

import java.util.*;

public interface Graph {

    /**
     * This method checks if a given vertex is present in the graph.
     * @param vertex Vertex to be evaluated.
     * @return Returns true if the given vertex is present in the graph
     *          and false otherwise.
     */
    boolean isVertexInGraph(Vertex vertex);

    /**
     * Checks if there is a connection between the given vertices.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @return Returns true if there is an edge between the origin
     *         vertex and the destination vertex and false otherwise.
     * @throws VertexNotFoundException  Throws this exception if any
     *         of the vertices does not exist in the graph.
     *
     */
    boolean isNeighbor(Vertex origin, Vertex destination) throws VertexNotFoundException;

    /**
     * Lists all the neighbours of the given vertex.
     * @param vertex Vertex to evaluate.
     * @return Returns the list of the neighbors. If the given node
     *         does not exist on the graph this method returns null.
     * @throws VertexNotFoundException  Throws this exception if the
     *         given vertex does not exist in the graph.
     */
    List<Vertex> getNeighbors(Vertex vertex) throws VertexNotFoundException;

    /**
     * Adds the specified vertex to the graph. If the vertex already
     * exists in the graph nothing is done.
     * @param vertex Vertex to be added.
     */
    void addVertex(Vertex vertex);

    /**
     * Removes the specified vertex from the graph. If the vertex does
     * not exist in the graph nothing is done.
     * @param vertex Vertex to be removed.
     */
    void removeVertex(Vertex vertex);

    /**
     * Adds an edge connecting 2 vertices to the graph. If the edge
     * already exists nothing is done.
     * @param edge Edge to be added
     * @throws VertexNotFoundException  Throws this exception if the
     *         any of the edge's vertices does not exist.
     */
    void addEdge(Edge edge) throws VertexNotFoundException;

    /**
     * Removes the edge between the origin vertex and the destination
     * vertex y. If the edge does not exist nothing is done.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @throws VertexNotFoundException  Throws this exception if the
     *         any of the specified vertices does not exist.
     */
    void removeEdge(Vertex origin, Vertex destination) throws VertexNotFoundException;

    /**
     * This method iterates the graph using the Depth First Search
     * algorithm.
     * @param startingVertex Initial vertex from where the iteration
     *                       should begin.
     * @return Returns the list of vertices iterated.
     * @throws VertexNotFoundException Throws this exception if the
     *          starting vertex is not found.
     */
    default List<Vertex> depthFirstSearch(Vertex startingVertex) throws VertexNotFoundException
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

    /**
     * This method iterates the graph using the Breadth First Search
     * algorithm.
     * @param startingVertex Initial vertex from where the iteration
     *                       should begin.
     * @return Returns the list of vertices iterated.
     * @throws VertexNotFoundException Throws this exception if the
     *          starting vertex is not found.
     */
    default List<Vertex> breadthFirstSearch(Vertex startingVertex) throws VertexNotFoundException
    {
        if ( !isVertexInGraph(startingVertex)) {
            throw new VertexNotFoundException();
        }

        List<Vertex> vertices = new ArrayList<>();
        HashSet<Vertex> visitationMap = new HashSet<>();
        ArrayDeque<Vertex> visitationQueue = new ArrayDeque<>();

        visitationQueue.add(startingVertex);
        visitationMap.add(startingVertex);

        while (visitationQueue.size() > 0)
        {
            Vertex vertex = visitationQueue.pollFirst();
            vertices.add(vertex);

            List<Vertex> neighbors = getNeighbors(vertex);
            for(Vertex neighbor : neighbors)
            {
                if (!visitationMap.contains(neighbor)) {
                    // Only add vertices which were not yet added to the queue
                    visitationQueue.add(neighbor);
                    visitationMap.add(neighbor);
                }
            }
        }

        return vertices;
    }
}
