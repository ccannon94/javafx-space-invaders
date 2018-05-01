package spaceinvaders.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import spaceinvaders.datatypes.PlayerProfile;
import spaceinvaders.datatypes.PlayerProfileCollection;
import spaceinvaders.framework.SpaceInvadersIO;

import java.util.ArrayList;

public class StartMenu extends Application {
    private static PlayerProfileCollection playerProfiles;
    private ComboBox playerProfiles_comboBox;
    private TextField newPlayerGamerID_textField;
    private TextField newPlayerEmail_textField;
    private Label newPlayerGamerID_label;
    private Label newPlayerEmail_label;
    private Button newPlayerRegister_button;
    private Button playerProfilesSelect_button;
    private static String playerProfileConfigFileName;

    public static void main(String[] args) {
        playerProfileConfigFileName = args[0];
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StartMenuHandler handler = new StartMenuHandler();
        playerProfiles = SpaceInvadersIO.readPlayerProfiles(playerProfileConfigFileName);

        BorderPane root = new BorderPane();
        Label title_label = new Label("Select Your Profile");
        root.setTop(title_label);

        GridPane playerSelection_pane = new GridPane();

        playerProfiles_comboBox = new ComboBox();
        ArrayList<String> gamerIDs = new ArrayList<>();
        for(int i = 0; i < playerProfiles.getNumPlayerProfiles(); i++) {
            gamerIDs.add(playerProfiles.getPlayerProfile(i).getGamerID());
        }
        gamerIDs.add("Create New Profile");
        playerProfiles_comboBox.setItems(FXCollections.observableArrayList(gamerIDs));
        playerProfiles_comboBox.setPrefWidth(300);
        playerProfilesSelect_button = new Button("Select");
        playerProfilesSelect_button.setOnAction(handler);
        newPlayerGamerID_label = new Label("Gamer ID");
        newPlayerGamerID_textField = new TextField();
        newPlayerEmail_label = new Label("Email Address");
        newPlayerEmail_textField = new TextField();
        newPlayerRegister_button = new Button("Register New Player");
        newPlayerRegister_button.setOnAction(handler);
        hideNewPlayerControls();

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

    public void showNewPlayerControls() {
        newPlayerGamerID_label.setVisible(true);
        newPlayerGamerID_textField.setVisible(true);
        newPlayerEmail_label.setVisible(true);
        newPlayerEmail_textField.setVisible(true);
        newPlayerRegister_button.setVisible(true);
    }

    public void hideNewPlayerControls() {
        newPlayerGamerID_label.setVisible(false);
        newPlayerGamerID_textField.setVisible(false);
        newPlayerEmail_label.setVisible(false);
        newPlayerEmail_textField.setVisible(false);
        newPlayerRegister_button.setVisible(false);
    }

    public class StartMenuHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Button buttonClicked = (Button) event.getSource();

            if(buttonClicked == playerProfilesSelect_button) {
                if(playerProfiles_comboBox.getSelectionModel().getSelectedIndex() >= playerProfiles.getNumPlayerProfiles()) {
                    showNewPlayerControls();
                }else{
                    PlayerProfile selectedProfile = playerProfiles.getPlayerProfile(playerProfiles_comboBox.getSelectionModel().getSelectedIndex());
                    playerProfiles.setActiveProfile(selectedProfile);
                    GameStage game = new GameStage(playerProfiles, null);
                    //launch game with selected profile


                }
            }else {
                PlayerProfile newPlayer = new PlayerProfile(newPlayerGamerID_textField.getText(), newPlayerEmail_textField.getText());
                //Check and see if gamerID already exists
                if(validateGamerID(newPlayer.getGamerID())) {
                    playerProfiles.addPlayerProfile(newPlayer);
                    playerProfiles_comboBox.getItems().remove("Create New Profile");
                    playerProfiles_comboBox.getItems().add(newPlayer.getGamerID());
                    playerProfiles_comboBox.getItems().add("Create New Profile");
                    playerProfiles_comboBox.getSelectionModel().select(playerProfiles.getNumPlayerProfiles() - 1);
                    SpaceInvadersIO.writePlayerProfiles(playerProfileConfigFileName, playerProfiles);

                    Alert playerAddedSucessfullyAlert = new Alert(Alert.AlertType.INFORMATION);
                    playerAddedSucessfullyAlert.setTitle("Success!");
                    playerAddedSucessfullyAlert.setHeaderText(null);
                    playerAddedSucessfullyAlert.setContentText("Your profile " + newPlayer.getGamerID() + " has been successfully created!");

                    playerAddedSucessfullyAlert.showAndWait();
                }else {
                    Alert addPlayerErrorAlert = new Alert(Alert.AlertType.ERROR);
                    addPlayerErrorAlert.setTitle("Error Occured!");
                    addPlayerErrorAlert.setHeaderText("Unable to add " + newPlayer.getGamerID() + ". Please make sure your requested Gamer ID has no spaces and does not already exists.");

                    addPlayerErrorAlert.showAndWait();
                }

            }
        }

        private boolean validateGamerID(String gamerID) {
            if(gamerID.contains(" "))
                return false;
            for(int i = 0; i < playerProfiles.getNumPlayerProfiles(); i++) {
                if(gamerID.equals(playerProfiles.getPlayerProfile(i).getGamerID()))
                    return false;
            }
            return true;
        }
    }
}
