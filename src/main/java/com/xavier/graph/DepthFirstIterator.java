package com.xavier.graph;

import java.util.*;

public class DepthFirstIterator implements Iterator<Vertex> {

        private Graph graph;
        private HashSet<Vertex> visitedVertices;
        private ArrayDeque<Vertex> visitationStack;

        private Vertex next;

        DepthFirstIterator(Graph graph, Vertex startingVertex)
        {
            this.graph = graph;
            this.visitedVertices = new HashSet<>();
            this.visitationStack = new ArrayDeque<>();

            this.next = startingVertex;
            addNeighborsToVisitationStack(startingVertex);
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
            Vertex vertex = popUnvisitedVertexFromStack();
            if (vertex == null) {
                return null;
            }

            visitedVertices.add(vertex);
            addNeighborsToVisitationStack(vertex);
            return vertex;
        }

        private Vertex popUnvisitedVertexFromStack()
        {
            while (visitationStack.size() > 0) {
                Vertex vertex = visitationStack.pop();
                if (visitedVertices.contains(vertex)) {
                    continue; // Node already visited. Ignoring.
                }

                return vertex;
            }
            return null;
        }

        private void addNeighborsToVisitationStack(Vertex vertex)
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
                visitationStack.push(neighbor);
            }
        }
}
