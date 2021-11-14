package graph;

import drawing.DrawingApi;

import java.util.ArrayList;
import java.util.Scanner;

public class ConjectureMatrixGraph extends Graph {

    public ConjectureMatrixGraph(DrawingApi drawingApi) {
        super(drawingApi);
    }

    @Override
    void readGraph() {
        Scanner in = new Scanner(System.in);
        int verticesCount = in.nextInt();
        for (int i = 0; i < verticesCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < verticesCount; i++) {
            for (int j = 0; j < verticesCount; j++) {
                int x = in.nextInt();
                if (i >= j) {
                    continue;
                }
                if (x == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
    }
}
