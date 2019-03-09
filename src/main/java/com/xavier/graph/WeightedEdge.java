package com.xavier.graph;

class WeightedEdge extends Edge {

    private int weight = 0;

    WeightedEdge (Vertex origin, Vertex destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    int getWeight()
    {
        return this.weight;
    }


}

