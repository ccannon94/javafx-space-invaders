package spaceinvaders.datatypes;

public class PlayerProfile {
    private String gamerID;
    private String emailAddress;
    private int highScore;
    private int gamesPlayed;

    //TODO: Make a PlayerProfile constructor that accepts a String for gamerID and a String for emailAddress,

    //TODO: Make a PlayerProfile constructor that accepts an argument for each property of PlayerProfile

    public String getGamerID() {
        return gamerID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    //TODO: Write a toString method that returns all of the properties of PlayerProfile in the order they are declared as comma-separated values
}
