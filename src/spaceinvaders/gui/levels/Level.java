package spaceinvaders.gui.levels;

import javafx.scene.layout.Pane;
import spaceinvaders.gui.*;

public abstract class Level extends Pane {
    protected BadGuyCraft[][] badGuys;
    protected GoodGuyCraft goodGuy;

    public BadGuyCraft getBadGuyCraft(int row, int column){
        if(badGuys[row][column] != null)
            return badGuys[row][column];
        else
            return null;
    }

    public GoodGuyCraft getGoodGuyCraft(){
        return goodGuy;
    }

    public Torpedo fireTorpedo(){
        Torpedo newTorpedo = new GoodGuyTorpedo(goodGuy.getX(), goodGuy.getY());
        this.getChildren().add(newTorpedo);
        return newTorpedo;
    }

    public Torpedo dropBomb(BadGuyCraft badGuy){
        Torpedo newBomb = new BadGuyTorpedo(badGuy.getX(), badGuy.getY());
        this.getChildren().add(newBomb);
        return newBomb;
    }

    public void detonateTorpedo(Torpedo torpedo){
        this.getChildren().remove(torpedo);
    }

    public void killBadGuy(int row, int column){
        if(row == 0 && column == 0){
            badGuys[row][column].setVisible(false);
        }else {
            this.getChildren().remove(badGuys[row][column]);
            badGuys[row][column] = null;
        }
    }

    public abstract int getBadGuysColumns();

    public abstract int getBadGuysRows();

    public  abstract int getBadGuysStartingX();

    public abstract int getBadGuysStartingY();

    public abstract int getBadGuysEndingX();

    public abstract int getBadGuysEndingY();

    public abstract int getStartingNumBadGuys();
}
