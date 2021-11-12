package graph;

import drawing.DrawingApi;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph {

    private final DrawingApi drawingApi;
    protected final List<List<Integer>> graph;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
        this.graph = new ArrayList<>();
    }

    public void drawGraph() {
        readGraph();
        renderGraph();
    }

    abstract void readGraph();

    private void renderGraph() {
        int verticesCount = graph.size();
        float xCenter = drawingApi.getDrawingAreaWidth() / 2F;
        float yCenter = drawingApi.getDrawingAreaHeight() / 2F;
        float verticesCircleRadius = 0.9F * Math.min(xCenter, yCenter) / 2;
        float verticesDiameter = 2 * (float) Math.PI * verticesCircleRadius * 0.5F / verticesCount;

        for (int i = 0; i < verticesCount; i++) {
            Coordinates vertexCoordinates = getVertexCoordinates(i, verticesCount, xCenter, yCenter,
                    verticesCircleRadius);
            drawingApi.drawCircle(vertexCoordinates.x, vertexCoordinates.y, verticesDiameter);
        }

        for (int i = 0; i < verticesCount; i++) {
            Coordinates from = getVertexCoordinates(i, verticesCount, xCenter, yCenter, verticesCircleRadius);
            for (int j : graph.get(i)) {
                Coordinates to = getVertexCoordinates(j, verticesCount, xCenter, yCenter, verticesCircleRadius);
                drawingApi.drawLine(from.x, from.y, to.x, to.y);
            }
        }
        drawingApi.showResult();
    }

    private Coordinates getVertexCoordinates(int i, int verticesCount, float xCenter, float yCenter, float verticesCircleRadius) {
        float angle = i * 2 * (float) Math.PI / verticesCount;
        return new Coordinates(xCenter + verticesCircleRadius * (float) Math.cos(angle),
                yCenter + verticesCircleRadius * (float) Math.sin(angle));
    }

    static class Coordinates {
        float x, y;

        public Coordinates(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
