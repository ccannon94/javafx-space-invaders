package spaceinvaders.gui;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Label title_label = new Label("JavaFX Space Invaders");
        root.setTop(title_label);

        GridPane playerSelection_pane = new GridPane();
        ComboBox playerProfiles_comboBox = new ComboBox();
        Button playerProfilesSelect_button = new Button("Select");
        Label newPlayerGamerID_label = new Label("Gamer ID");
        TextField newPlayerGamerID_textField = new TextField();
    }
}
