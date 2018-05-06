package spaceinvaders.gui.levels;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Pane;
import spaceinvaders.datatypes.GameConfigData;
import spaceinvaders.gui.BadGuyCraft;
import spaceinvaders.gui.GamePane;
import spaceinvaders.gui.GoodGuyCraft;


public class LevelOne extends Level {
    private final int levelOneBadGuysColumns = 5;
    private final int levelOneBadGuysRows = 3;
    private final int badGuysStartingX = 100;
    private final int badGuysEndingX = 200;
    private final int badGuysStartingY = 50;
    private final int badGuysEndingY = 300;

    public LevelOne(GameConfigData gameData) {
        badGuys = new BadGuyCraft[levelOneBadGuysRows][levelOneBadGuysColumns];
        for(int i = 1; i <= levelOneBadGuysRows; i++){
            for(int j = 1; j <= levelOneBadGuysColumns; j++){
                badGuys[i - 1][j - 1] = new BadGuyCraft(gameData.getImagePath(GameConfigData.BAD_GUY_IMAGE_PATH_INDEX), j * badGuysStartingX, i * badGuysStartingY);
                this.getChildren().add(badGuys[i - 1][j - 1]);
            }
        }

        goodGuy = new GoodGuyCraft(gameData.getImagePath(GameConfigData.GOOD_GUY_IMAGE_PATH_INDEX), 250, 450);
        this.getChildren().add(goodGuy);

        this.setPrefHeight(GamePane.getGamePaneHeight());
        this.setPrefWidth(GamePane.getGamePaneWidth());
    }

    @Override
    public int getBadGuysColumns(){
        return levelOneBadGuysColumns;
    }

    @Override
    public int getBadGuysRows(){
        return levelOneBadGuysRows;
    }

    @Override
    public  int getBadGuysStartingX(){
        return badGuysStartingX;
    }

    @Override
    public int getBadGuysStartingY(){
        return badGuysStartingY;
    }

    @Override
    public int getBadGuysEndingX(){
        return badGuysEndingX;
    }

    @Override
    public int getBadGuysEndingY(){
        return badGuysEndingY;
    }
}
