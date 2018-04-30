package spaceinvaders.datatypes;

public class PlayerProfile {
    private String gamerID;
    private String emailAddress;
    private int highScore;
    private int gamesPlayed;

    public PlayerProfile(String gamerID, String emailAddress) {
        this.gamerID = gamerID;
        this.emailAddress = emailAddress;
    }

    public PlayerProfile(String gamerID, String emailAddress, int highScore, int gamesPlayed) {
        this(gamerID, emailAddress);
        this.highScore = highScore;
        this.gamesPlayed = gamesPlayed;
    }

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

    @Override
    public String toString() {
        return String.format("%-15s, %-30s, %10d, %10d", gamerID, emailAddress, highScore, gamesPlayed);
    }
}
