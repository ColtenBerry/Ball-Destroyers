package base_destroyer;

public class Point {
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
//    public Position getPosition() {
//        long newX = Math.round(getX());
//        long newY = Math.round(getY());
//        int positionX = (int)newX;
//        int positionY = (int)newY;
//        positionX = positionX / 40;
//        positionY = positionY / 40;
//        Position position = new Position(positionX, positionY);
//        return position;
//    }
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
