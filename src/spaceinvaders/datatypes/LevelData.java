package spaceinvaders.datatypes;

import java.util.ArrayList;

public class LevelData {
    private ArrayList<Level> levels;

    public LevelData() {
        this.levels = new ArrayList<>();
    }

    public int getNumLevels(){
        return levels.size();
    }

    public Level getLevel(int i){
        return levels.get(i);
    }

    public void addLevel(Level newLevel){
        levels.add(newLevel);
    }

    public void setLevel(int i, Level newLevel){
        levels.set(i, newLevel);
    }

    public Level removeLevel(int i){
        return levels.remove(i);
    }

    public String toString(){
        String returnString = "";
        for(int i = 0; i < levels.size(); i++) {
            returnString += levels.get(i).toString();
            if(i < (levels.size() - 1)){
                returnString += System.lineSeparator();
            }
        }
        return returnString;
    }
}
