package spaceinvaders.gui;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import spaceinvaders.datatypes.Game;
import spaceinvaders.datatypes.GameConfigData;
import spaceinvaders.datatypes.PlayerProfileCollection;
import spaceinvaders.framework.SpaceInvadersIO;

import java.util.ArrayList;
import java.util.Random;

public class GameStage extends Stage {
    private PlayerProfileCollection playerProfileCollection;
    private GameConfigData gameConfigData;
    private StatusPane statusPane;
    private GamePane gamePane;
    private long previous = 0;
    private GameTimer timer = new GameTimer();
    private ArrayList<Torpedo> firedTorpedos;
    private Game activeGame;
    private boolean originAlive;
    private ControlPane controlPane;

    public GameStage(PlayerProfileCollection playerProfileCollection, GameConfigData gameConfigData) {
        this.playerProfileCollection = playerProfileCollection;
        this.gameConfigData = gameConfigData;
        playerProfileCollection.getActiveProfile().setGamesPlayed(playerProfileCollection.getActiveProfile().getGamesPlayed() + 1);
        statusPane = new StatusPane(playerProfileCollection.getHighScore(), gameConfigData.getImagePath(GameConfigData.GOOD_GUY_IMAGE_PATH_INDEX));
        gamePane = new GamePane(gameConfigData);
        gamePane.setGamePaneBackground(gameConfigData.getImagePath(GameConfigData.BACKGROUND_IMAGE_PATH_INDEX));
        activeGame = new Game(gamePane.getCurrentLevel().getStartingNumBadGuys());
        originAlive = true;
        controlPane = new ControlPane();

        BorderPane root = new BorderPane();
        root.setTop(statusPane);
        root.setCenter(gamePane);
        root.setBottom(controlPane);

        KeyboardHandler keyHandler = new KeyboardHandler();
        Scene scene = new Scene(root, 600, 600);
        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);

        firedTorpedos = new ArrayList<>();

        timer.start();
        this.setScene(scene);
        this.setTitle("Space Invaders!");
        this.show();

        SpaceInvadersIO.writePlayerProfiles("playerProfileData.csv",playerProfileCollection);
    }

    public void winGame(){
        boolean newHighScore = activeGame.getScore() > playerProfileCollection.getActiveProfile().getHighScore();

        timer.stop();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(newHighScore) {
                    playerProfileCollection.getActiveProfile().setHighScore(activeGame.getScore());
                    Alert newHighScoreAlert = new Alert(Alert.AlertType.INFORMATION);
                    newHighScoreAlert.setTitle("You Won!");
                    newHighScoreAlert.setHeaderText("New High Score!!" + System.lineSeparator() + activeGame.getScore());
                    newHighScoreAlert.setContentText("Congratulations " + playerProfileCollection.getActiveProfile().getGamerID() + " you have saved the day!");
                    newHighScoreAlert.showAndWait();
                    SpaceInvadersIO.writePlayerProfiles("playerProfileData.csv",playerProfileCollection);
                }else{
                    Alert wonGameAlert = new Alert(Alert.AlertType.INFORMATION);
                    wonGameAlert.setTitle("You won!");
                    wonGameAlert.setHeaderText("Score: " + activeGame.getScore());
                    wonGameAlert.setContentText("Congratulations " + playerProfileCollection.getActiveProfile().getGamerID() + " you have saved the day!");
                    wonGameAlert.showAndWait();
                }
            }
        });
    }

    public void loseGame(){
        boolean newHighScore = activeGame.getScore() > playerProfileCollection.getActiveProfile().getHighScore();

        timer.stop();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(newHighScore) {
                    playerProfileCollection.getActiveProfile().setHighScore(activeGame.getScore());
                    Alert newHighScoreAlert = new Alert(Alert.AlertType.INFORMATION);
                    newHighScoreAlert.setTitle("You've been defeated!!");
                    newHighScoreAlert.setHeaderText("New High Score!!" + System.lineSeparator() + activeGame.getScore());
                    newHighScoreAlert.setContentText("Congratulations " + playerProfileCollection.getActiveProfile().getGamerID() + " you have set a new high score!");
                    newHighScoreAlert.showAndWait();
                    SpaceInvadersIO.writePlayerProfiles("playerProfileData.csv",playerProfileCollection);
                }else{
                    Alert wonGameAlert = new Alert(Alert.AlertType.INFORMATION);
                    wonGameAlert.setTitle("You have been defeated!");
                    wonGameAlert.setHeaderText("Score: " + activeGame.getScore());
                    wonGameAlert.setContentText("Unfortunately " + playerProfileCollection.getActiveProfile().getGamerID() + " you have been overrun by the invaders!");
                    wonGameAlert.showAndWait();
                }
            }
        });
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
                }else if(event.getCode().equals(KeyCode.F)){
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
                dropBombs();

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
                checkEndOfGame();
            }

            previous = now;
        }

        private void dropBombs() {
            Random rand = new Random();
            int fireInt = rand.nextInt(10);
            if(fireInt == 5){
                int firingShipRow = rand.nextInt(gamePane.getCurrentLevel().getBadGuysRows());
                int firingShipColumn = rand.nextInt(gamePane.getCurrentLevel().getBadGuysColumns());
                if(firingShipRow == 0 && firingShipColumn == 0){
                    if(gamePane.getCurrentLevel().getBadGuyCraft(firingShipRow, firingShipColumn).isVisible()){
                        firedTorpedos.add(gamePane.getCurrentLevel().dropBomb(gamePane.getCurrentLevel().getBadGuyCraft(firingShipRow, firingShipColumn)));
                    }
                }else if(gamePane.getCurrentLevel().getBadGuyCraft(firingShipRow, firingShipColumn) != null){
                    firedTorpedos.add(gamePane.getCurrentLevel().dropBomb(gamePane.getCurrentLevel().getBadGuyCraft(firingShipRow, firingShipColumn)));
                }
            }
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
                                        handleBadGuyHit(i, j);
                                    } else if (torpedo.getStartX() < (gamePane.getCurrentLevel().getBadGuyCraft(i, j).getY() + gamePane.getCurrentLevel().getBadGuyCraft(i, j).getFitHeight()) && torpedo.getStartX() > gamePane.getCurrentLevel().getBadGuyCraft(i, j).getY()) {
                                        gamePane.getCurrentLevel().detonateTorpedo(firedTorpedos.remove(k));
                                        gamePane.getCurrentLevel().getBadGuyCraft(i, j).setHitPoints(gamePane.getCurrentLevel().getBadGuyCraft(i, j).getHitPoints() - 10);
                                        handleBadGuyHit(i, j);
                                    }
                                }
                            }
                        }
                    }
                    if(torpedo.getEndY() < gamePane.getCurrentLevel().getBadGuysStartingY() - 20){
                        gamePane.getCurrentLevel().detonateTorpedo(firedTorpedos.remove(k));
                    }
                }else if(torpedo instanceof BadGuyTorpedo){
                    if(torpedo.getEndY() > gamePane.getCurrentLevel().getGoodGuyCraft().getY() && torpedo.getEndY() < (gamePane.getCurrentLevel().getGoodGuyCraft().getY() + gamePane.getCurrentLevel().getGoodGuyCraft().getFitHeight())){
                        if(torpedo.getStartX() > gamePane.getCurrentLevel().getGoodGuyCraft().getX() && torpedo.getEndX() < (gamePane.getCurrentLevel().getGoodGuyCraft().getX() + gamePane.getCurrentLevel().getGoodGuyCraft().getFitWidth())){
                            gamePane.getCurrentLevel().detonateTorpedo(firedTorpedos.remove(k));
                            gamePane.getCurrentLevel().getGoodGuyCraft().setHitPoints(gamePane.getCurrentLevel().getGoodGuyCraft().getHitPoints() - 10);
                            if(gamePane.getCurrentLevel().getGoodGuyCraft().getHitPoints() <= 0){
                                statusPane.decrementLivesRemaining();
                                gamePane.getCurrentLevel().getGoodGuyCraft().setHitPoints(40);
                                if(statusPane.getLivesRemaining() <= 0)
                                    loseGame();
                            }
                        }
                    }
                }
            }
        }

        private void handleBadGuyHit(int i, int j) {
            if (gamePane.getCurrentLevel().getBadGuyCraft(i, j).getHitPoints() <= 0) {
                if(i == 0 & j == 0 && originAlive){
                    gamePane.getCurrentLevel().killBadGuy(i, j);
                    activeGame.setScore(activeGame.getScore() + 5);
                    activeGame.setBadGuysRemaining(activeGame.getBadGuysRemaining() - 1);
                    statusPane.setScoreValueLabel(activeGame.getScore());
                    originAlive = false;
                }if(i != 0 || j != 0){
                    gamePane.getCurrentLevel().killBadGuy(i, j);
                    activeGame.setScore(activeGame.getScore() + 5);
                    activeGame.setBadGuysRemaining(activeGame.getBadGuysRemaining() - 1);
                    statusPane.setScoreValueLabel(activeGame.getScore());
                }
            }
        }

        private void checkEndOfGame(){
            if(activeGame.getBadGuysRemaining() <= 0){
                winGame();
            }
        }
    }

    public class ControlPane extends HBox {
        public ControlPane(){
            Button newGameButton = new Button("New Game");
            Button exitButton = new Button("Exit");
            this.getChildren().addAll(newGameButton, exitButton);
            this.setAlignment(Pos.CENTER);
            this.setSpacing(50.0);

            newGameButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    gamePane = new GamePane(gameConfigData);
                    statusPane = new StatusPane(playerProfileCollection.getHighScore(), gameConfigData.getImagePath(GameConfigData.GOOD_GUY_IMAGE_PATH_INDEX));
                }
            });

            exitButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.exit(0);
                }
            });
        }
    }
}
