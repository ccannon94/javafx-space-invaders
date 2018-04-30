package spaceinvaders.datatypes;

import java.util.ArrayList;

public class PlayerProfileCollection {
    private ArrayList<PlayerProfile> profiles;

    public PlayerProfileCollection() {
        profiles = new ArrayList<>();
    }

    public int getNumPlayerProfiles() {
        return profiles.size();
    }

    public PlayerProfile getPlayerProfile(int index) {
        return profiles.get(index);
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
