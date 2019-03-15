package com.xavier.graphs.parsers;

import com.xavier.graphs.Graph;
import com.xavier.graphs.exceptions.InvalidGraphFileException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface GraphFileParser {

    Graph fetchGraphFromFile(String file) throws FileNotFoundException, IOException, InvalidGraphFileException;

}
