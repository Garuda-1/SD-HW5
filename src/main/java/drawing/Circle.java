package drawing;

class Circle {
    private final float x, y, diameter;

    public Circle(float x, float y, float diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDiameter() {
        return diameter;
    }
}
