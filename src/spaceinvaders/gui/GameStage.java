package spaceinvaders.gui;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spaceinvaders.datatypes.GameConfigData;
import spaceinvaders.datatypes.PlayerProfileCollection;

import java.util.ArrayList;

public class GameStage extends Stage {
    private PlayerProfileCollection playerProfileCollection;
    private GameConfigData gameConfigData;
    private StatusPane statusPane;
    private GamePane gamePane;
    private long previous = 0;
    private GameTimer timer = new GameTimer();
    private ArrayList<Torpedo> firedTorpedos;
    //private ControlPane controlPane;

    public GameStage(PlayerProfileCollection playerProfileCollection, GameConfigData gameConfigData) {
        this.playerProfileCollection = playerProfileCollection;
        this.gameConfigData = gameConfigData;

        statusPane = new StatusPane(0, gameConfigData.getImagePath(GameConfigData.GOOD_GUY_IMAGE_PATH_INDEX));
        gamePane = new GamePane(gameConfigData);
        gamePane.setGamePaneBackground(gameConfigData.getImagePath(GameConfigData.BACKGROUND_IMAGE_PATH_INDEX));

        BorderPane root = new BorderPane();
        root.setTop(statusPane);
        root.setCenter(gamePane);

        KeyboardHandler keyHandler = new KeyboardHandler();
        Scene scene = new Scene(root, 600, 600);
        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);

        firedTorpedos = new ArrayList<>();

        timer.start();
        this.setScene(scene);
        this.setTitle("Space Invaders!");
        this.show();
    }

    public class KeyboardHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            if(event.getEventType().equals(KeyEvent.KEY_PRESSED)){
                if(event.getCode().equals(KeyCode.RIGHT)){
                    gamePane.getCurrentLevel().getGoodGuyCraft().setSpeed(5.0);
                    gamePane.getCurrentLevel().getGoodGuyCraft().setDirection(0.0);
                }else if(event.getCode().equals(KeyCode.LEFT)){
                    gamePane.getCurrentLevel().getGoodGuyCraft().setSpeed(5.0);
                    gamePane.getCurrentLevel().getGoodGuyCraft().setDirection(180.0);
                }else if(event.getCode().equals(KeyCode.SPACE)){
                    firedTorpedos.add(gamePane.getCurrentLevel().fireTorpedo());
                }
            }else if(event.getEventType().equals(KeyEvent.KEY_RELEASED)){
                if(event.getCode().isArrowKey()){
                    gamePane.getCurrentLevel().getGoodGuyCraft().setSpeed(0.0);
                }
            }
        }
    }

    public class GameTimer extends AnimationTimer {

        @Override
        public void handle(long now) {
            if (now - previous >= 200000) {
                gamePane.getCurrentLevel().getGoodGuyCraft().move();

                moveTorpedos();
                moveBadGuys();

                //Check to see if the player craft has reached the left or right bound
                if(gamePane.getCurrentLevel().getGoodGuyCraft().getX() <= 0.0){
                    gamePane.getCurrentLevel().getGoodGuyCraft().setX(5.0);
                }
                if(gamePane.getCurrentLevel().getGoodGuyCraft().getX() >= 500.0){
                    gamePane.getCurrentLevel().getGoodGuyCraft().setX(495.0);
                }

                //Check to see if the bad guy crafts have reached their movement limits
                if(gamePane.getCurrentLevel().getBadGuyCraft(0,0).getDirection() == 0.0 && gamePane.getCurrentLevel().getBadGuyCraft(0,0).getX() >= gamePane.getCurrentLevel().getBadGuysStartingX() + 30.0){
                    for(int i = 0; i < gamePane.getCurrentLevel().getBadGuysRows(); i++){
                        for(int j = 0; j < gamePane.getCurrentLevel().getBadGuysColumns(); j++) {
                            if(gamePane.getCurrentLevel().getBadGuyCraft(i,j) != null)
                                gamePane.getCurrentLevel().getBadGuyCraft(i,j).setDirection(90.0);
                        }
                    }
                }else if(gamePane.getCurrentLevel().getBadGuyCraft(0,0).getDirection() == 90.0 && gamePane.getCurrentLevel().getBadGuyCraft(0,0).getY() >= gamePane.getCurrentLevel().getBadGuysStartingY() + 30.0){
                    for(int i = 0; i < gamePane.getCurrentLevel().getBadGuysRows(); i++){
                        for(int j = 0; j < gamePane.getCurrentLevel().getBadGuysColumns(); j++) {
                            if(gamePane.getCurrentLevel().getBadGuyCraft(i,j) != null)
                                gamePane.getCurrentLevel().getBadGuyCraft(i,j).setDirection(180.0);
                        }
                    }
                }else if(gamePane.getCurrentLevel().getBadGuyCraft(0,0).getDirection() == 180.0 && gamePane.getCurrentLevel().getBadGuyCraft(0,0).getX() <= gamePane.getCurrentLevel().getBadGuysStartingX()){
                    for(int i = 0; i < gamePane.getCurrentLevel().getBadGuysRows(); i++){
                        for(int j = 0; j < gamePane.getCurrentLevel().getBadGuysColumns(); j++) {
                            if(gamePane.getCurrentLevel().getBadGuyCraft(i,j) != null)
                                gamePane.getCurrentLevel().getBadGuyCraft(i,j).setDirection(270.0);
                        }
                    }
                }else if(gamePane.getCurrentLevel().getBadGuyCraft(0,0).getDirection() == 270.0 && gamePane.getCurrentLevel().getBadGuyCraft(0,0).getY() <= gamePane.getCurrentLevel().getBadGuysStartingY()){
                    for(int i = 0; i < gamePane.getCurrentLevel().getBadGuysRows(); i++){
                        for(int j = 0; j < gamePane.getCurrentLevel().getBadGuysColumns(); j++) {
                            if(gamePane.getCurrentLevel().getBadGuyCraft(i,j) != null)
                                gamePane.getCurrentLevel().getBadGuyCraft(i,j).setDirection(0.0);
                        }
                    }
                }

                checkTorpedoCollisions();
            }

            previous = now;
        }

        private void moveTorpedos(){
            for(Torpedo torpedo : firedTorpedos){
                if(torpedo != null){
                    torpedo.move();
                }
            }
        }

        private void moveBadGuys(){
            for(int i = 0; i < gamePane.getCurrentLevel().getBadGuysRows(); i++){
                for(int j = 0; j < gamePane.getCurrentLevel().getBadGuysColumns(); j++){
                    if(gamePane.getCurrentLevel().getBadGuyCraft(i,j) != null)
                        gamePane.getCurrentLevel().getBadGuyCraft(i, j).move();
                }
            }
        }

        private void checkTorpedoCollisions(){
            for(int k = 0; k < firedTorpedos.size(); k++){
                Torpedo torpedo = firedTorpedos.get(k);
                if(torpedo instanceof GoodGuyTorpedo){
                    for(int i = 0; i < gamePane.getCurrentLevel().getBadGuysRows(); i++) {
                        for (int j = 0; j < gamePane.getCurrentLevel().getBadGuysColumns(); j++) {
                            if(gamePane.getCurrentLevel().getBadGuyCraft(i,j) != null) {
                                if (torpedo.getStartX() > gamePane.getCurrentLevel().getBadGuyCraft(i, j).getX() && torpedo.getStartX() < (gamePane.getCurrentLevel().getBadGuyCraft(i, j).getX() + gamePane.getCurrentLevel().getBadGuyCraft(i, j).getFitWidth())) {
                                    if (torpedo.getEndY() > gamePane.getCurrentLevel().getBadGuyCraft(i, j).getY() && torpedo.getEndY() < (gamePane.getCurrentLevel().getBadGuyCraft(i, j).getY() + gamePane.getCurrentLevel().getBadGuyCraft(i, j).getFitHeight())) {
                                        gamePane.getCurrentLevel().detonateTorpedo(firedTorpedos.remove(k));
                                        gamePane.getCurrentLevel().getBadGuyCraft(i, j).setHitPoints(gamePane.getCurrentLevel().getBadGuyCraft(i, j).getHitPoints() - 10);
                                        if (gamePane.getCurrentLevel().getBadGuyCraft(i, j).getHitPoints() <= 0) {
                                            gamePane.getCurrentLevel().killBadGuy(i, j);
                                        }
                                    } else if (torpedo.getStartX() < (gamePane.getCurrentLevel().getBadGuyCraft(i, j).getY() + gamePane.getCurrentLevel().getBadGuyCraft(i, j).getFitHeight()) && torpedo.getStartX() > gamePane.getCurrentLevel().getBadGuyCraft(i, j).getY()) {
                                        gamePane.getCurrentLevel().detonateTorpedo(firedTorpedos.remove(k));
                                        gamePane.getCurrentLevel().getBadGuyCraft(i, j).setHitPoints(gamePane.getCurrentLevel().getBadGuyCraft(i, j).getHitPoints() - 10);
                                        if (gamePane.getCurrentLevel().getBadGuyCraft(i, j).getHitPoints() <= 0) {
                                            gamePane.getCurrentLevel().killBadGuy(i, j);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
