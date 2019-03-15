package com.xavier.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DijkstraShortestPathAlgorithm {

    private Graph graph;

    DijkstraShortestPathAlgorithm(Graph graph) {
        this.graph = graph;
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
    WeightedGraphPath findMinCostPath(Vertex origin, Vertex destination) throws VertexNotFoundException
    {
        if (!graph.containsVertex(origin) || !graph.containsVertex(destination)) {
            throw new VertexNotFoundException();
        }

        // TODO: Try to use Fibonacci Heap or Pairing Heap to allow efficient min retrieval and weight update
        HashMap<Vertex, DijkstraVertexInfo> verticesMap = new HashMap<>();

        // Initialize unvisited vertices and update weight of origin node to 0
        List<Vertex> vertices = graph.getVertices();
        for(Vertex vertex : vertices) {
            verticesMap.put(vertex, new DijkstraVertexInfo());
        }

        verticesMap.get(origin).setWeight(0);

        int unvisitedNodes = vertices.size();
        while (unvisitedNodes > 0 )
        {
            Vertex currentVertex = getMinWeightUnvisitedVertex(verticesMap);
            int currentVertexWeight = verticesMap.get(currentVertex).getWeight();

            List<Edge> edges = graph.getEdges(currentVertex);
            for (Edge edge : edges) {
                Vertex neighbor = edge.destination;
                DijkstraVertexInfo neighborInfo = verticesMap.get(neighbor);

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


    private Vertex getMinWeightUnvisitedVertex(HashMap<Vertex, DijkstraVertexInfo> verticesMap)
    {
        // TODO: Very inefficient. Change to Fibonacci Heap
        int minWeight = Integer.MAX_VALUE;
        Vertex vertex = null;

        for (Map.Entry<Vertex, DijkstraVertexInfo> entry : verticesMap.entrySet()) {
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

    private WeightedGraphPath buildPathToDestination(HashMap<Vertex, DijkstraVertexInfo> verticesMap, Vertex destination)
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

    class DijkstraVertexInfo
    {
        private boolean isVisited;
        private int weight;
        private Vertex previousVertex;

        DijkstraVertexInfo()
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
