package spaceinvaders.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import spaceinvaders.datatypes.PlayerProfileCollection;
import spaceinvaders.framework.SpaceInvadersIO;

public class StartMenu extends Application {
    private static PlayerProfileCollection playerProfiles;

    public static void main(String[] args) {
        playerProfiles = SpaceInvadersIO.readPlayerProfiles(args[0]);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Label title_label = new Label("Select Your Profile");
        root.setTop(title_label);

        GridPane playerSelection_pane = new GridPane();

        ComboBox playerProfiles_comboBox = new ComboBox();
        Button playerProfilesSelect_button = new Button("Select");
        Label newPlayerGamerID_label = new Label("Gamer ID");
        TextField newPlayerGamerID_textField = new TextField();
        Label newPlayerEmail_label = new Label("Email Address");
        TextField newPlayerEmail_textField = new TextField();
        Button newPlayerRegister_button = new Button("Register New Player");

        playerSelection_pane.add(playerProfiles_comboBox, 0, 0);
        playerSelection_pane.add(playerProfilesSelect_button, 1, 0);
        playerSelection_pane.add(newPlayerGamerID_label, 0, 1);
        playerSelection_pane.add(newPlayerGamerID_textField,1, 1);
        playerSelection_pane.add(newPlayerEmail_label, 0, 2);
        playerSelection_pane.add(newPlayerEmail_textField, 1, 2);
        playerSelection_pane.add(newPlayerRegister_button, 1, 3);

        playerSelection_pane.setPadding(new Insets(10,10,10,10));

        root.setCenter(playerSelection_pane);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Space Invaders");
        primaryStage.show();
    }
}
