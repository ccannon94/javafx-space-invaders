package spaceinvaders.datatypes;

public class Level {
    private int levelNum;
    private int numBadGuys;
    private int numBadBosses;
    private int badBossHitPoints;

    public Level(int levelNum, int numBadGuys, int numBadBosses, int badBossHitPoints){
        this.levelNum = levelNum;
        this.numBadGuys = numBadGuys;
        this.numBadBosses = numBadBosses;
        this.badBossHitPoints = badBossHitPoints;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public int getNumBadGuys() {
        return numBadGuys;
    }

    public int getNumBadBosses() {
        return numBadBosses;
    }

    public int getBadBossHitPoints() {
        return badBossHitPoints;
    }

    public String toString() {
        return String.format("%-5d, %-5d, %-5d, %-5d", getLevelNum(), getNumBadGuys(), getNumBadBosses(), getBadBossHitPoints());
    }
}
