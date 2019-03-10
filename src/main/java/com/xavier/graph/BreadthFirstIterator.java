package com.xavier.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BreadthFirstIterator implements Iterator {

    private Graph graph;
    private Vertex startingVertex;

    BreadthFirstIterator(Graph graph, Vertex startingVertex)
    {
        this.graph = graph;
        this.startingVertex = startingVertex;
    }

    public boolean hasNext()
    {
        return false;
    }

    public Vertex next()
    {
        return null;
    }
}
