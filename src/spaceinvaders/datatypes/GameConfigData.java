package spaceinvaders.datatypes;

import java.util.ArrayList;

public class GameConfigData {
    public static final int GOOD_GUY_IMAGE_PATH_INDEX = 0;
    public static final int BAD_GUY_IMAGE_PATH_INDEX = 1;
    public static final int BAD_BOSS_IMAGE_PATH_INDEX = 2;
    public static final int BACKGROUND_IMAGE_PATH_INDEX = 3;
    public static final double BAD_GUY_SPEED = 3.0;
    public static final int GOOD_GUY_SPEED = 3;


    private String[] imagePaths;

    public GameConfigData() {
        imagePaths = new String[4];
    }

    public String getImagePath(int index){
        return imagePaths[index];
    }

    public void setImagePath(int index, String path){
        imagePaths[index] = path;
    }

    public String toString(){
        String returnString = "";
        for(String path : imagePaths){
            returnString += path + System.lineSeparator();
        }
        return returnString;
    }
}
