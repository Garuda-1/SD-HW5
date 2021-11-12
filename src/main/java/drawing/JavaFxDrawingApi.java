package drawing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFxDrawingApi implements DrawingApi {

    private final int width;
    private final int height;
    private final List<Circle> circles = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();

    private static final Gson gson = new Gson();


    public JavaFxDrawingApi(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getDrawingAreaWidth() {
        return width;
    }

    @Override
    public int getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(float x, float y, float d) {
        circles.add(new Circle(x - d / 2, y - d / 2, d));
    }

    @Override
    public void drawLine(float x1, float y1, float x2, float y2) {
        lines.add(new Line(x1, y1, x2, y2));
    }

    @Override
    public void showResult() throws RuntimeException {
        try {
            Application.launch(DrawingApplication.class, Integer.toString(width), Integer.toString(height),
                    gson.toJson(circles), gson.toJson(lines));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static class DrawingApplication extends Application {

        @Override
        public void start(Stage stage) {
            List<String> parameters = getParameters().getRaw();
            if (parameters.size() != 4) {
                throw new RuntimeException();
            }

            int width = Integer.parseInt(parameters.get(0));
            int height = Integer.parseInt(parameters.get(1));
            List<Circle> circles = gson.fromJson(parameters.get(2), new TypeToken<List<Circle>>(){}.getType());
            List<Line> lines = gson.fromJson(parameters.get(3), new TypeToken<List<Line>>(){}.getType());


            Group root = new Group();
            Canvas canvas = new Canvas(width, height);
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            circles.forEach(circle -> graphicsContext.strokeOval(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter()));
            lines.forEach(line -> graphicsContext.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2()));
            root.getChildren().add(canvas);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
