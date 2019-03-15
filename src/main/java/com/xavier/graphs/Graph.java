package com.xavier.graphs;

import com.xavier.graphs.exceptions.VertexNotFoundException;

import java.util.*;

public interface Graph {

    /**
     * This method allows to know if a given graphs is directed. In a
     * directed graphs if there is an edge from Vertex A to Vertex B it
     * does not mean that there is an edge from Vertex B to Vertex A.
     * @return Returns true if the graphs is directed and false otherwise.
     */
    default boolean isDirected() {
        return false;
    }

    /**
     * This method allows to know if a given graphs is weighted. In a
     * weighted graphs every edge of the graphs has an weight associated
     * with it. This weight could represent for example the cost of
     * traversing from one Vertex to another.
     * @return Returns true if the graphs is weighted and false otherwise.
     */
    default boolean isWeighted() {
        return false;
    }

    /**
     * This method checks if a given vertex is present in the graphs.
     * @param vertex Vertex to be evaluated.
     * @return Returns true if the given vertex is present in the graphs
     *          and false otherwise.
     */
    boolean containsVertex(Vertex vertex);

    /**
     * Checks if there is a connection between the given vertices.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @return Returns true if there is an edge between the origin
     *         vertex and the destination vertex and false otherwise.
     * @throws VertexNotFoundException Throws this exception if any
     *         of the vertices does not exist in the graphs.
     *
     */
    boolean areVerticesNeighbors(Vertex origin, Vertex destination) throws VertexNotFoundException;

    /**
     * Lists all the neighbours of the given vertex.
     * @param vertex Vertex to evaluate.
     * @return Returns the list of the neighbors.
     * @throws VertexNotFoundException  Throws this exception if the
     *         given vertex does not exist in the graphs.
     */
    List<Vertex> getVertexNeighbors(Vertex vertex) throws VertexNotFoundException;

    /**
     * This method allows to retrieve a list containing all the graphs's
     * vertices.
     * @return Returns a list containing all the graphs's vertices.
     */
    List<Vertex> getVertices();

    /**
     * Adds the specified vertex to the graphs. If the vertex already
     * exists in the graphs nothing is done.
     * @param vertex Vertex to be added.
     */
    void addVertex(Vertex vertex);

    /**
     * Removes the specified vertex from the graphs. If the vertex does
     * not exist in the graphs nothing is done.
     * @param vertex Vertex to be removed.
     */
    void removeVertex(Vertex vertex);

    /**
     * Lists all the edges of the given vertex.
     * @param vertex Vertex to evaluate.
     * @return Returns the list of the edges.
     * @throws VertexNotFoundException  Throws this exception if the
     *         given vertex does not exist in the graphs.
     */
    List<Edge> getEdges(Vertex vertex) throws VertexNotFoundException;

    /**
     * Adds an edge connecting 2 vertices to the graphs. If the edge
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
     * Given an origin and a destination vertex this method finds
     * a path between them.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @return Returns a list containing the vertices of the path
     *         between the 2 vertices. If no path can be found this
     *         function returns a null list.
     * @throws VertexNotFoundException  Throws this exception if the
     *         any of the specified vertices does not exist.
     */
    // TODO: Check best way to create an abstraction
    //List<Vertex> findPath(Vertex origin, Vertex destination) throws VertexNotFoundException;

    /**
     * This method returns a depth first iterator to enable to iterate
     * the graphs.
     * @param startingVertex Initial vertex from where the iteration
     *                       should begin.
     * @return Returns the iterator
     * @throws VertexNotFoundException Throws this exception if the
     *          starting vertex is not found.
     */
    default Iterator<Vertex> depthFirstIterator(Vertex startingVertex) throws VertexNotFoundException {
        return new DepthFirstIterator(this, startingVertex);
    }

    /**
     * This method returns a breadth first iterator to enable to iterate
     * the graphs.
     * @param startingVertex Initial vertex from where the iteration
     *                       should begin.
     * @return Returns the iterator
     * @throws VertexNotFoundException Throws this exception if the
     *          starting vertex is not found.
     */
    default Iterator<Vertex> breadthFirstIterator(Vertex startingVertex) throws VertexNotFoundException {
        return new BreadthFirstIterator(this, startingVertex);
    }

    /**
     * This method iterates the graphs using the Depth First Search
     * algorithm.
     * @param startingVertex Initial vertex from where the iteration
     *                       should begin.
     * @return Returns the list of vertices iterated.
     * @throws VertexNotFoundException Throws this exception if the
     *          starting vertex is not found.
     */
    default List<Vertex> depthFirstIteration(Vertex startingVertex) throws VertexNotFoundException
    {
        List<Vertex> vertices = new ArrayList<>();

        Iterator<Vertex> it = depthFirstIterator(startingVertex);
        while (it.hasNext()) {
            vertices.add(it.next());
        }

        return vertices;
    }

    /**
     * This method iterates the graphs using the Breadth First Search
     * algorithm.
     * @param startingVertex Initial vertex from where the iteration
     *                       should begin.
     * @return Returns the list of vertices iterated.
     * @throws VertexNotFoundException Throws this exception if the
     *          starting vertex is not found.
     */
    default List<Vertex> breadthFirstIteration(Vertex startingVertex) throws VertexNotFoundException
    {
        List<Vertex> vertices = new ArrayList<>();

        Iterator<Vertex> it = breadthFirstIterator(startingVertex);
        while (it.hasNext()) {
            vertices.add(it.next());
        }

        return vertices;
    }
}
