package spaceinvaders.datatypes;

import java.util.ArrayList;

public class PlayerProfileCollection {
    private ArrayList<PlayerProfile> profiles;
    private PlayerProfile activeProfile;
    private int highScore;

    public PlayerProfileCollection() {
        profiles = new ArrayList<>();
    }

    public int getNumPlayerProfiles() {
        return profiles.size();
    }

    public PlayerProfile getPlayerProfile(int index) {
        return profiles.get(index);
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore){
        this.highScore = highScore;
    }

    public void addPlayerProfile(PlayerProfile newProfile) {
        profiles.add(newProfile);
    }

    public void setPlayerProfile(int index, PlayerProfile newProfile) {
        profiles.set(index, newProfile);
    }

    public PlayerProfile removePlayerProfile(int index) {
        return profiles.remove(index);
    }

    public PlayerProfile getActiveProfile() {
        return activeProfile;
    }

    public void setActiveProfile(PlayerProfile profile) {
        this.activeProfile = profile;
    }

    public void updateHighScore(){
        for (PlayerProfile profile : profiles){
            if (profile.getHighScore() > highScore)
                highScore = profile.getHighScore();
        }
    }

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
