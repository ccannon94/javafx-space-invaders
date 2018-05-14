package spaceinvaders.gui;

import javafx.scene.image.ImageView;

public abstract class Craft extends ImageView {
    private final int PARENT_X_BOUND = 500;
    private final int PARENT_Y_BOUND = 500;
    private final int DEFAULT_CRAFT_WIDTH = 20;
    private final int DEFAULT_CRAFT_HEIGHT = 20;

    private double direction;
    private double speed;
    private int hitPoints;

    public Craft(String imageFilePath, double startingX, double startingY){
        super(imageFilePath);
        this.setX(startingX);
        this.setY(startingY);
        this.setFitHeight(DEFAULT_CRAFT_HEIGHT);
        this.setFitWidth(DEFAULT_CRAFT_WIDTH);
        hitPoints = 50;
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getDirection(){
        return direction;
    }

    public void setDirection(double direction){
        this.direction = direction;
    }

    public void move() {
        this.setX(this.getX() + speed * Math.cos(direction * (2 * Math.PI)/360));
        this.setY(this.getY() + speed * Math.sin(direction * (2 * Math.PI)/360));
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }
}
