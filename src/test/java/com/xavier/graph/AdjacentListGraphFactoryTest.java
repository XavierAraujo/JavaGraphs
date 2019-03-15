package com.xavier.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdjacentListGraphFactoryTest {

    @Test
    void adjacentListGraphFactoryTest()
    {
        AdjacentListGraphFactory factory = new AdjacentListGraphFactory();

        assertTrue(factory.getGraph(false, false) instanceof UndirectedAdjacentListGraph);
        assertTrue(factory.getGraph(false, true) instanceof WeightedUndirectedAdjacentListGraph);
        assertTrue(factory.getGraph(true, false) instanceof DirectedAdjacentListGraph);
        assertTrue(factory.getGraph(true, true) instanceof WeightedDirectedAdjacentListGraph);
    }
}
