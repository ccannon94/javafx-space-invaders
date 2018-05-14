package spaceinvaders.gui;

public class BadGuyCraft extends Craft {
    public BadGuyCraft(String imageFilePath, double startingX, double startingY, double speed){
        super(imageFilePath, startingX, startingY);
        this.setDirection(0.0);
        this.setSpeed(speed);
        this.setHitPoints(10);
    }
}
