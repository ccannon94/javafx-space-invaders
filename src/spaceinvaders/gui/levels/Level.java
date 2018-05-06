package spaceinvaders.gui.levels;

import javafx.scene.layout.Pane;
import spaceinvaders.gui.BadGuyCraft;
import spaceinvaders.gui.GoodGuyCraft;
import spaceinvaders.gui.GoodGuyTorpedo;
import spaceinvaders.gui.Torpedo;

public abstract class Level extends Pane {
    protected BadGuyCraft[][] badGuys;
    protected GoodGuyCraft goodGuy;

    public BadGuyCraft getBadGuyCraft(int row, int column){
        return badGuys[row][column];
    }

    public GoodGuyCraft getGoodGuyCraft(){
        return goodGuy;
    }

    public Torpedo fireTorpedo(){
        Torpedo newTorpedo = new GoodGuyTorpedo(goodGuy.getX(), goodGuy.getY());
        this.getChildren().add(newTorpedo);
        return newTorpedo;
    }

    public abstract int getBadGuysColumns();

    public abstract int getBadGuysRows();

    public  abstract int getBadGuysStartingX();

    public abstract int getBadGuysStartingY();

    public abstract int getBadGuysEndingX();

    public abstract int getBadGuysEndingY();
}
