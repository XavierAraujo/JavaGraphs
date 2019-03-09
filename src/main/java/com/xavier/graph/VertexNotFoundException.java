package com.xavier.graph;

public class VertexNotFoundException extends Exception
{
    public VertexNotFoundException() {}

    public VertexNotFoundException(String message)
    {
        super(message);
    }
}
