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
     * @throws VertexNotFoundException Throws this exception if any
     *          of the vertices does not exist in the graph.
     */
    WeightedGraphPath findMinimumCostPath(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if (!contains(origin) || !contains(destination)) {
            throw new VertexNotFoundException();
        }

        // TODO: Try to use Fibonacci Heap to allow efficient min retrieval and weight update
        HashMap<Vertex, DijkstraInfo> verticesMap = new HashMap<>();

        // Initialize unvisited vertices and update weight of origin node to 0
        List<Vertex> vertices = getVertices();
        for(Vertex vertex : vertices) {
            verticesMap.put(vertex, new DijkstraInfo());
        }

        verticesMap.get(origin).setWeight(0);

        int unvisitedNodes = vertices.size();
        while (unvisitedNodes > 0 )
        {
            Vertex currentVertex = getMinWeightUnvisitedVertex(verticesMap);
            int currentVertexWeight = verticesMap.get(currentVertex).getWeight();

            List<Edge> edges = getEdges(currentVertex);
            for (Edge edge : edges) {
                Vertex neighbor = edge.destination;
                DijkstraInfo neighborInfo = verticesMap.get(neighbor);

                int neighborWeight = neighborInfo.getWeight();
                int newWeight = currentVertexWeight + ((WeightedEdge) edge).getWeight();

                if (newWeight < neighborWeight) {
                    verticesMap.get(neighbor).setWeight(newWeight);
                    verticesMap.get(neighbor).setPreviousVertex(currentVertex);
                }

            }

            verticesMap.get(currentVertex).setVisited(true);
            unvisitedNodes -= 1;

            if (currentVertex.equals(destination)) {
                // We found the destination. No need to continue evaluating
                return buildPathToDestination(verticesMap, destination);
            }
        }

        return null;
    }

    private Vertex getMinWeightUnvisitedVertex(HashMap<Vertex, DijkstraInfo> verticesMap)
    {
        // TODO: Very inefficient. Change to Fibonacci Heap
        int minWeight = Integer.MAX_VALUE;
        Vertex vertex = null;

        for (Map.Entry<Vertex, DijkstraInfo> entry : verticesMap.entrySet()) {
            if (entry.getValue().getIsVisited()) {
                continue; // Only consider unvisited nodes
            }

            int weight = entry.getValue().getWeight();
            if (weight <= minWeight) {
                vertex = entry.getKey();
                minWeight = weight;
            }
        }

        return vertex;
    }

    private WeightedGraphPath buildPathToDestination(HashMap<Vertex, DijkstraInfo> verticesMap, Vertex destination)
    {
        if (verticesMap.get(destination).getWeight() == Integer.MAX_VALUE) {
            return null;
        }

        int pathWeight = verticesMap.get(destination).getWeight();

        ArrayList<Vertex> verticesPath = new ArrayList<>();
        Vertex vertex = destination;
        while (vertex != null) {
            verticesPath.add(vertex);
            vertex = verticesMap.get(vertex).getPreviousVertex();
        }

        return new WeightedGraphPath(pathWeight, verticesPath);
    }

    class DijkstraInfo
    {
        private boolean isVisited;
        private int weight;
        private Vertex previousVertex;

        DijkstraInfo()
        {
            isVisited = false;
            weight = Integer.MAX_VALUE;
            previousVertex = null;
        }

        boolean getIsVisited() {
            return isVisited;
        }

        void setVisited(boolean visited) {
            isVisited = visited;
        }

        int getWeight() {
            return weight;
        }

        void setWeight(int weight) {
            this.weight = weight;
        }

        Vertex getPreviousVertex() {
            return previousVertex;
        }

        void setPreviousVertex(Vertex previousVertex) {
            this.previousVertex = previousVertex;
        }
    }
}
