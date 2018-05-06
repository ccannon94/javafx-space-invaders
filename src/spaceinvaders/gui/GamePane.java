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

        this.getChildren().add(currentLevel);

        this.setPrefHeight(500);
        this.setPrefWidth(500);
    }

    public static int getGamePaneHeight(){
        return HEIGHT;
    }

    public static int getGamePaneWidth(){
        return WIDTH;
    }

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
