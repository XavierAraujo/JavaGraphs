package com.xavier.graphs.exceptions;

public class VertexNotFoundException extends Exception
{
    public VertexNotFoundException() {}

    public VertexNotFoundException(String message)
    {
        super(message);
    }
}
