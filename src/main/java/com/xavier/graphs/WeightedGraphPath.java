package com.xavier.graphs;

import java.util.List;

public class WeightedGraphPath {

    private int weight;
    private List<Vertex> path;

    public WeightedGraphPath(int weight, List<Vertex> path) {
        this.weight = weight;
        this.path = path;
    }

    public int getWeight() {
        return weight;
    }

    public List<Vertex> getPath() {
        return path;
    }

    @Override
    public String toString()
    {
        if (path == null || path.size() == 0) {
            return "Empty Path";
        }

        StringBuilder pathStr = new StringBuilder();
        for (Vertex vertex: path) {
            pathStr.append(String.format("Vertex %d ", vertex.getId()));
        }
        pathStr.append(String.format("(Weight: %d)", weight));

        return pathStr.toString();
    }
}
