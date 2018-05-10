package spaceinvaders.framework;

import spaceinvaders.datatypes.GameConfigData;
import spaceinvaders.datatypes.PlayerProfile;
import spaceinvaders.datatypes.PlayerProfileCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SpaceInvadersIO {
    public static PlayerProfileCollection readPlayerProfiles(String filename) {
        PlayerProfileCollection profiles = new PlayerProfileCollection();
        try {
            Scanner profileReader = new Scanner(new File(filename));
            while(profileReader.hasNext()) {
                String[] profileLine = profileReader.nextLine().split(",");
                PlayerProfile newProfile = new PlayerProfile(profileLine[0].trim(), profileLine[1].trim(), Integer.parseInt(profileLine[2].trim()), Integer.parseInt(profileLine[3].trim()));
                profiles.addPlayerProfile(newProfile);
                if(newProfile.getHighScore() > profiles.getHighScore())
                    profiles.setHighScore(newProfile.getHighScore());
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("Player Profile data not found in %20s", filename));
        }
        return profiles;
    }

    public static void writePlayerProfiles(String filename, PlayerProfileCollection profiles) {
        //TODO: Add logic that will write the toString method of each PlayerProfile in profiles to the given file
    }

    public static GameConfigData readGameConfigData(String filename){
        //TODO: Add logic that will instantiate a new GameConfigData object, and read the paths from the given file and return the newly populated GameConfigData
    }
}
