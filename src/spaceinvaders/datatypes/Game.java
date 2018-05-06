package spaceinvaders.datatypes;

public class Game {
    private int score;
    private int badGuysRemaining;

    public Game(int badGuysRemaining){
        score = 0;
        this.badGuysRemaining = badGuysRemaining;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getBadGuysRemaining(){
        return badGuysRemaining;
    }

    public void setBadGuysRemaining(int badGuysRemaining){
        this.badGuysRemaining = badGuysRemaining;
    }
}
