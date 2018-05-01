package spaceinvaders.gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spaceinvaders.datatypes.GameConfigData;
import spaceinvaders.datatypes.PlayerProfile;
import spaceinvaders.datatypes.PlayerProfileCollection;

public class GameStage extends Stage {
    private PlayerProfileCollection playerProfileCollection;
    private GameConfigData gameConfigData;
    private StatusPane statusPane;
    //private GamePane gamePane;
    //private ControlPane controlPane;

    public GameStage(PlayerProfileCollection playerProfileCollection, GameConfigData gameConfigData) {
        this.playerProfileCollection = playerProfileCollection;
        this.gameConfigData = gameConfigData;

        statusPane = new StatusPane(0, "spaceinvaders/resources/images/goodguy.png");

        BorderPane root = new BorderPane();
        root.setTop(statusPane);

        Scene scene = new Scene(root, 500, 500);

        this.setScene(scene);
        this.setTitle("Space Invaders!");
        this.show();
    }


}
