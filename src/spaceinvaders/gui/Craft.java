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

    //TODO: Write the accessor method for speed. It will be called "getSpeed" and return a double

    //TODO: Write the mutator method for speed. It will be called "setSpeed" and take a double as an argument. It will not return anything
    public double getDirection(){
        return direction;
    }

    public void setDirection(double direction){
        this.direction = direction;
    }

    //TODO: Write the "move" method that will update the x and y location of the craft based off the current X and the speed in accordance with the instructions

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }
}
