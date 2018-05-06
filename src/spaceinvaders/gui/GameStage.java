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

public class GameStage extends Stage {
    private PlayerProfileCollection playerProfileCollection;
    private GameConfigData gameConfigData;
    private StatusPane statusPane;
    private GamePane gamePane;
    private long previous = 0;
    private GameTimer timer = new GameTimer();
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

                if(gamePane.getCurrentLevel().getGoodGuyCraft().getX() <= 0.0){
                    gamePane.getCurrentLevel().getGoodGuyCraft().setX(5.0);
                }
                if(gamePane.getCurrentLevel().getGoodGuyCraft().getX() >= 500.0){
                    gamePane.getCurrentLevel().getGoodGuyCraft().setX(495.0);
                }
                /*//check for collision
                if (ball.getCenterY() - ball.getRadius() <= 0) {
                    ball.setCenterY(ball.getRadius()+1);
                    ball.setDirection(360 - ball.getDirection());
                }

                //check for collision
                if (ball.getCenterY() + ball.getRadius() >= paneHeight) {
                    ball.setCenterY(paneHeight - (ball.getRadius()+1));
                    ball.setDirection(360 - ball.getDirection());
                }

                //check for collision
                if (ball.getCenterX() - ball.getRadius() <= 0) {
                    ball.setCenterX((ball.getRadius()+1));
                    if (ball.getDirection() < 180) {
                        ball.setDirection(90 + (90 - ball.getDirection()));
                    }
                    else {
                        ball.setDirection(270 + (270 - ball.getDirection()));
                    }
                }

                //check for collision
                if (ball.getCenterX() + ball.getRadius() >= paneWidth) {
                    ball.setCenterX(paneWidth - (ball.getRadius()+1));
                    if (ball.getDirection() < 180) {
                        ball.setDirection(90 + (90 - ball.getDirection()));
                    }
                    else {
                        ball.setDirection(270 + (270 - ball.getDirection()));
                    }
                }*/
            }

            previous = now;
        }
    }
}
