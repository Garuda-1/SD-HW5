import drawing.AwtDrawingApi;
import drawing.DrawingApi;
import drawing.JavaFxDrawingApi;
import graph.ConjectureMatrixGraph;
import graph.EdgeListGraph;
import graph.Graph;

public class Main {

    private static final String USAGE = "Usage: Main {AWT, JavaFX} {ConjectureMatrix, EdgeList}";
    private static final int DIMENSIONS = 800;

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsageAndTerminate();
        }
        DrawingApi drawingApi = null;
        switch (args[0]) {
            case "AWT": {
                drawingApi = new AwtDrawingApi(DIMENSIONS, DIMENSIONS);
                break;
            }
            case "JavaFX": {
                drawingApi = new JavaFxDrawingApi(DIMENSIONS, DIMENSIONS);
                break;
            }
            default: {
                System.err.format("Invalid drawing API name: %s%n", args[0]);
                printUsageAndTerminate();
            }
        }
        Graph graph = null;
        switch (args[1]) {
            case "ConjectureMatrix": {
                graph = new ConjectureMatrixGraph(drawingApi);
                break;
            }
            case "EdgeList": {
                graph = new EdgeListGraph(drawingApi);
                break;
            }
            default: {
                System.err.format("Invalid graph format name: %s%n", args[1]);
                printUsageAndTerminate();
            }
        }
        assert graph != null;
        graph.drawGraph();
    }

    private static void printUsageAndTerminate() {
        System.err.println(USAGE);
        System.exit(1);
    }
}
