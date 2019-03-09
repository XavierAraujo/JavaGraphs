package com.xavier.graph;

import java.util.List;

public interface Graph {

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
}
