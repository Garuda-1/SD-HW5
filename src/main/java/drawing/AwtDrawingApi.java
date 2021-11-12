package drawing;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class AwtDrawingApi extends Frame implements DrawingApi {

    private final int width;
    private final int height;
    private final List<Circle> circles = new ArrayList<>();
    private final List<Line> lines = new ArrayList<>();

    public AwtDrawingApi(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        circles.forEach(c -> g.drawOval((int) c.getX(), (int) c.getY(), (int) c.getDiameter(), (int) c.getDiameter()));
        lines.forEach(l -> g.drawLine((int) l.getX1(), (int) l.getY1(), (int) l.getX2(), (int) l.getY2()));
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
    public void showResult() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(width, height);
        setVisible(true);
    }
}
