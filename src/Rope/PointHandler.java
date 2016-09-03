package Rope;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by LoganLe on 8/31/16.
 */
public class PointHandler {
    public ArrayList<Point> points = new ArrayList<Point>();
    public ArrayList<Point> copiedPoints;
    private static PointHandler pointHandler = new PointHandler();

    private PointHandler(){

    }
    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public ArrayList<Point> getCopiedPoints() {
        return copiedPoints;
    }
    public void tick(){
        copiedPoints = new ArrayList<Point>(points);
        for(Point point: copiedPoints){
            point.tick();
        }
    }
    public void renderPoints(GraphicsContext gc){
        copiedPoints = new ArrayList<Point>(points);
        for(Point point: copiedPoints){
            point.render(gc);
        }
    }
    public void emptyPoints(){
        copiedPoints = new ArrayList<Point>(points);
        for(Point point: copiedPoints){
            points.remove(point);
        }
    }
    public void setCopiedPoints(ArrayList<Point> copiedPoints) {
        this.copiedPoints = copiedPoints;
    }
    public void addPoints(Point point){
        points.add(point);
    }
    public void removePoint(Point point){
        points.remove(point);
    }
    public static PointHandler getInstance(){
        return pointHandler;
    }
}
