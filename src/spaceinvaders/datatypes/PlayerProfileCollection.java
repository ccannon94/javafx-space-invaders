package spaceinvaders.datatypes;

import java.util.ArrayList;

public class PlayerProfileCollection {
    private ArrayList<PlayerProfile> profiles;
    private PlayerProfile activeProfile;
    private int highScore;

    public PlayerProfileCollection() {
        profiles = new ArrayList<>();
    }

    // TODO: Create the 5 methods required to access and mutate an ArrayList as described in the instructions

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore){
        this.highScore = highScore;
    }

    public PlayerProfile getActiveProfile() {
        return activeProfile;
    }

    public void setActiveProfile(PlayerProfile profile) {
        this.activeProfile = profile;
    }

    // TODO: Create a method called updateHighScore the will check all of the high scores from each profile

    @Override
    public String toString() {
        String returnString = "";
        for(int i = 0; i < getNumPlayerProfiles(); i++) {
            returnString += getPlayerProfile(i).toString();
            if(i < (getNumPlayerProfiles() - 1)) {
                returnString += System.lineSeparator();
            }
        }
        return returnString;
    }
}
