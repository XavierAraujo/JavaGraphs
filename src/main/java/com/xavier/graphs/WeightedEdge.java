package com.xavier.graphs;

public class WeightedEdge extends Edge {

    private int weight = 0;

    public WeightedEdge(Vertex origin, Vertex destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public int getWeight()
    {
        return this.weight;
    }


}

