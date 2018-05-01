package spaceinvaders.gui;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import spaceinvaders.datatypes.GameConfigData;

import java.io.FileInputStream;

public class GamePane extends Pane {
    private final int HEIGHT = 500;
    private final int WIDTH = 500;
    private final int BAD_GUY_COLS = 10;

    private GoodGuyCraft goodGuy;
    private BadGuyCraft[][] badGuys;

    public GamePane(GameConfigData gameConfigData){
        //need some method that will create an array of bad guys and arrange them in a grid.
        /*int numBadGuyRows = gameConfigData.getLevelData().getLevel(0).getNumBadGuys() / BAD_GUY_COLS;
        if(gameConfigData.getLevelData().getLevel(0).getNumBadBosses() > 0)
            numBadGuyRows += 1;
        badGuys = new BadGuyCraft[numBadGuyRows][BAD_GUY_COLS];
        if(gameConfigData.getLevelData().getLevel(0).getNumBadBosses() > 0){
            for(int i = 0; i < gameConfigData.getLevelData().getLevel(0).getNumBadBosses(); i++){
                BossCraft newBoss = new Boss()
            }
        }*/

        goodGuy = new GoodGuyCraft("spaceinvaders/resources/images/goodguy.png", 250.0, 500.0);
        this.getChildren().addAll(goodGuy);
        this.setPrefHeight(500);
        this.setPrefWidth(500);
    }

    public void setGamePaneBackground(String bgImagePath) {
        Image image1 = new Image(bgImagePath);
        BackgroundImage myBI = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
        this.setBackground(new Background(myBI));
    }
}
