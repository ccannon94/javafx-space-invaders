package spaceinvaders.datatypes;

import java.util.ArrayList;

public class GameConfigData {
    public static final int GOOD_GUY_IMAGE_PATH_INDEX = 0;
    public static final int BAD_GUY_IMAGE_PATH_INDEX = 1;
    public static final int BAD_BOSS_IMAGE_PATH_INDEX = 2;
    public static final int BACKGROUND_IMAGE_PATH_INDEX = 3;
    public static final int BAD_GUY_SPEED = 5;
    public static final int GOOD_GUY_SPEED = 5;


    private String[] imagePaths;
    private int playAreaWidth;
    private int playAreaHeight;
    private LevelData levelData;

    public GameConfigData(int playAreaWidth, int playAreaHeight, LevelData levelData) {
        imagePaths = new String[4];
        this.levelData = levelData;
        this.playAreaHeight = playAreaHeight;
        this.playAreaWidth = playAreaWidth;
    }

    public int getPlayAreaWidth() {
        return playAreaWidth;
    }

    public int getPlayAreaHeight() {
        return playAreaHeight;
    }

    public String getImagePath(int index){
        return imagePaths[index];
    }

    public void setImagePath(int index, String path){
        imagePaths[index] = path;
    }

    public LevelData getLevelData(){
        return levelData;
    }

    public void setLevelData(LevelData levelData){
        this.levelData = levelData;
    }
    public String toString(){
        String returnString = "";
        for(String path : imagePaths){
            returnString += path + System.lineSeparator();
        }
        returnString += playAreaWidth + ", " + playAreaHeight + System.lineSeparator() + levelData.toString();
        return returnString;
    }
}
