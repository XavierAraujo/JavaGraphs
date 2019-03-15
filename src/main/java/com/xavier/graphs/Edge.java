package com.xavier.graphs;

public class Edge  {

    public Vertex origin;
    public Vertex destination;

    public Edge(Vertex origin, Vertex destination) {
        this.origin = origin;
        this.destination = destination;
    }

    Edge() {
    }
}
