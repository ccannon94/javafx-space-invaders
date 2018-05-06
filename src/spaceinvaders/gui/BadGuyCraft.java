package spaceinvaders.gui;

public class BadGuyCraft extends Craft {
    public BadGuyCraft(String imageFilePath, double startingX, double startingY){
        super(imageFilePath, startingX, startingY);
        this.setDirection(0.0);
        this.setSpeed(3.0);
        this.setHitPoints(10);
    }
}
