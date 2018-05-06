package spaceinvaders.gui.levels;

import javafx.scene.layout.Pane;
import spaceinvaders.gui.BadGuyCraft;
import spaceinvaders.gui.GoodGuyCraft;

public abstract class Level extends Pane {
    protected BadGuyCraft[][] badGuys;
    protected GoodGuyCraft goodGuy;

    public BadGuyCraft getBadGuyCraft(int row, int column){
        return badGuys[row][column];
    }

    public GoodGuyCraft getGoodGuyCraft(){
        return goodGuy;
    }
}
