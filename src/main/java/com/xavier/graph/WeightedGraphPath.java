package com.xavier.graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraphPath {

    private int weight;
    private List<Vertex> path;

    WeightedGraphPath(int weight, List<Vertex> path) {
        this.weight = weight;
        this.path = path;
    }

    int getWeight() {
        return weight;
    }

    List<Vertex> getPath() {
        return path;
    }

    @Override
    public String toString()
    {
        if (path != null || path.size() == 0) {
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
