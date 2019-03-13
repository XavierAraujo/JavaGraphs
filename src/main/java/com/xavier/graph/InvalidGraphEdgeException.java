package com.xavier.graph;

public class InvalidGraphEdgeException extends Exception
{
    public InvalidGraphEdgeException() {}

    public InvalidGraphEdgeException(String message)
    {
        super(message);
    }
}
