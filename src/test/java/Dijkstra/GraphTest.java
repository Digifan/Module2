package Dijkstra;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void startTest() throws IOException {
        Graph.start();
        File output = new File("output.txt");
        assertTrue(output.length()!= 0);
    }
}