package spaceinvaders.gui;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class Torpedo extends Line {
    private double direction;
    private static final double speed = 8.0;

    public Torpedo(double startX, double startY, double direction, Paint torpedoColor) {
        this.direction = direction;

        this.setStartX(startX);
        this.setStartY(startY);
        this.setEndX(startX);
        if(direction == 90.0){
            this.setEndY(startY - 15.0);
        }else if(direction == 270.0){
            this.setEndY(startY + 15.0);
        }

        this.setFill(torpedoColor);
        this.setStroke(torpedoColor);
        this.setStrokeWidth(4.0);
    }

    public void setDirection(double direction){
        this.direction = direction;
    }

    public double getDirection(){
        return direction;
    }

    public void move() {
        this.setStartX(this.getStartX() + speed * Math.cos(direction * (2 * Math.PI)/360));
        this.setStartY(this.getStartY() + speed * Math.sin(direction * (2 * Math.PI)/360));
        this.setEndX(this.getEndX() + speed * Math.cos(direction * (2 * Math.PI)/360));
        this.setEndY(this.getEndY() + speed * Math.sin(direction * (2 * Math.PI)/360));
    }
}
