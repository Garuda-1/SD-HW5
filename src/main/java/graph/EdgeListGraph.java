package graph;

import drawing.DrawingApi;

import java.util.ArrayList;
import java.util.Scanner;

public class EdgeListGraph extends Graph {

    public EdgeListGraph(DrawingApi drawingApi) {
        super(drawingApi);
    }

    @Override
    public void readGraph() {
        Scanner in = new Scanner(System.in);
        int verticesCount = in.nextInt();
        int edgesCount = in.nextInt();
        for (int i = 0; i < verticesCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edgesCount; i++) {
            int f = in.nextInt() - 1;
            int t = in.nextInt() - 1;
            graph.get(f).add(t);
            graph.get(t).add(f);
        }
    }
}
