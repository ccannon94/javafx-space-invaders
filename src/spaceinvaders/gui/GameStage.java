package spaceinvaders.gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spaceinvaders.datatypes.GameConfigData;
import spaceinvaders.datatypes.PlayerProfileCollection;

public class GameStage extends Stage {
    private PlayerProfileCollection playerProfileCollection;
    private GameConfigData gameConfigData;
    private StatusPane statusPane;
    private GamePane gamePane;
    //private ControlPane controlPane;

    public GameStage(PlayerProfileCollection playerProfileCollection, GameConfigData gameConfigData) {
        this.playerProfileCollection = playerProfileCollection;
        this.gameConfigData = gameConfigData;

        statusPane = new StatusPane(0, "spaceinvaders/resources/images/goodguy.png");
        gamePane = new GamePane(gameConfigData);
        gamePane.setGamePaneBackground("spaceinvaders/resources/images/background.jpg");

        BorderPane root = new BorderPane();
        root.setTop(statusPane);
        root.setCenter(gamePane);

        Scene scene = new Scene(root, 600, 600);

        this.setScene(scene);
        this.setTitle("Space Invaders!");
        this.show();
    }


}
