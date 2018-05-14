package spaceinvaders.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import spaceinvaders.datatypes.GameConfigData;
import spaceinvaders.gui.levels.Level;
import spaceinvaders.gui.levels.LevelOne;

public class GamePane extends Pane {
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    private Level currentLevel;

    public GamePane(GameConfigData gameConfigData){
        currentLevel = new LevelOne(gameConfigData);

        //TODO: Add currentLevel to display in this pane

        //TODO: Set the height and width of the Pane using the constants in this class
    }

    //TODO: Create the accessor method for the game pane's height. It will be called getGamePaneHeight and return an int

    //TODO: Create the accessor method for game pane's width. It will be called getGamePaneWidth and return an int


    public Level getCurrentLevel(){
        return currentLevel;
    }

    public void setCurrentLevel(Level newLevel){
        currentLevel = newLevel;
    }

    public void setGamePaneBackground(String bgImagePath) {
        Image image1 = new Image(bgImagePath);
        BackgroundImage myBI = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
        this.setBackground(new Background(myBI));
    }
}
