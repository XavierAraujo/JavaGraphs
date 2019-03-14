package com.xavier.graph;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GraphFileParser {

    Graph fetchGraphFromFile(String file) throws FileNotFoundException, IOException;

}
