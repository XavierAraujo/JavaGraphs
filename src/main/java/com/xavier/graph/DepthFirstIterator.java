package com.xavier.graph;

import java.util.Iterator;

public class DepthFirstIterator implements Iterator {

        private Graph graph;
        private Vertex startingVertex;

        DepthFirstIterator(Graph graph, Vertex startingVertex)
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
