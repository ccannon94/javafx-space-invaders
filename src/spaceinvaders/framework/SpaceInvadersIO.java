package spaceinvaders.framework;

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
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("Player Profile data not found in %20s", filename));
        }
        return profiles;
    }

    public static void writePlayerProfiles(String filename, PlayerProfileCollection profiles) {
        try {
            PrintWriter profileWriter = new PrintWriter(new File(filename));
            for(int i = 0; i < profiles.getNumPlayerProfiles(); i++) {
                profileWriter.println(profiles.getPlayerProfile(i).toString());
            }
            profileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println(String.format("Player data output could not be written to %20s", filename));
        }
    }
}
