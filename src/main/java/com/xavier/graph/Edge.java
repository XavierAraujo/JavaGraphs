package com.xavier.graph;

class Edge  {

    Vertex origin;
    Vertex destination;

    Edge (Vertex origin, Vertex destination) {
        this.origin = origin;
        this.destination = destination;
    }

    Edge() {
    }
}
