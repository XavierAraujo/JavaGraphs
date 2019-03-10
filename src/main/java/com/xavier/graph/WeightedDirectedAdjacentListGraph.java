package com.xavier.graph;

import java.util.*;

public class WeightedDirectedAdjacentListGraph extends DirectedAdjacentListGraph {

    /**
     * This method builds a weighted edge between the given origin
     * vertex and destination vertex and adds it to the graph.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @param weight Weight of the edge
     */
    void addWeightedEdge(Vertex origin, Vertex destination, int weight) throws VertexNotFoundException
    {
        addEdge(new WeightedEdge(origin, destination, weight));
    }

    /**
     * This method uses the Dijkstra's algorithm to calculate the
     * minimum cost path between 2 graph nodes.
     * @param origin Origin vertex.
     * @param destination Destination vertex.
     * @return Returns the list of vertices that form the minimum
     *         cost path.
     * @throws VertexNotFoundException T
     */
    public WeightedGraphPath findMinimumCostPath(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if (!contains(origin) ||
                !contains(destination)) {
            throw new VertexNotFoundException();
        }

        // TODO: Try to use Fibonacci Heap to allow efficient min retrieval and weight update
        HashSet<Vertex> vertices = new HashSet<>();
        HashMap<Vertex, Integer> weights = new HashMap<>();
        HashMap<Vertex, Vertex> previousVertices = new HashMap<>();

        // Initialize unvisited vertices and update weight of origin node to 0
        List<Vertex> graphVertices = getVertices();
        for(Vertex vertex : graphVertices) {
            vertices.add(vertex);
            weights.put(vertex, Integer.MAX_VALUE);
            previousVertices.put(vertex, null);
        }

        weights.put(origin, 0);

        // Iterate while there still exist non-visited vertices.
        while (vertices.size() > 0 )
        {
            // Set the non-visited vertex with the smallest current weight as the current vertex C.
            Vertex currentVertex = getMinWeightUnvisitedVertex(vertices, weights);
            int currentVertexWeight = weights.get(currentVertex);

            // For each neighbour N of your current node C: add the current weight of C with the
            // weight of the edge connecting C-N. If it's smaller than the current weight of N, set
            // it as the new current weight of N.
            List<Edge> edges = getEdges(currentVertex);
            for (Edge edge : edges) {
                Vertex neighbor = edge.destination;
                int neighborWeight = weights.get(neighbor);
                int newWeight = currentVertexWeight + ((WeightedEdge) edge).getWeight();

                if (newWeight < neighborWeight) {
                    weights.put(neighbor, newWeight);
                    previousVertices.put(neighbor, currentVertex);
                }

            }

            // Mark the current vertex C as visited.
            vertices.remove(currentVertex);

            if (currentVertex.equals(destination)
                && weights.get(destination) < Integer.MAX_VALUE) {
                // We found the destination. No need to continue evaluating
                WeightedGraphPath path = new WeightedGraphPath();

                path.setWeight(weights.get(destination));

                Vertex vertex = destination;
                while (vertex != null) {
                    path.addVertex(vertex);
                    vertex = previousVertices.get(vertex);
                }

                return path;
            }
        }

        return null;
    }

    private Vertex getMinWeightUnvisitedVertex(HashSet<Vertex> vertices, HashMap<Vertex, Integer> weights)
    {
        // TODO: Very inefficient. Change to Fibonacci Heap
        int minDistance = Integer.MAX_VALUE;
        Vertex vertex = null;
        for (Vertex v : vertices) {
            int distance = weights.get(v);
            if (distance <= minDistance) {
                vertex = v;
                minDistance = distance;
            }
        }
        return vertex;
    }
}
