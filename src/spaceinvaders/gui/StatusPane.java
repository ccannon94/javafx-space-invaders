package spaceinvaders.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class StatusPane extends HBox {
    private Label scoreLabel;
    private Label scoreValueLabel;
    private Label highScoreLabel;
    private Label highScoreValueLabel;
    private Label livesRemainingLabel;
    private HBox livesRemainingHBox;
    private ImageView[] lifeImages;

    private int livesRemaining;

    public StatusPane(int highScore, String goodGuyImagePath){
        scoreLabel = new Label("Score: ");
        scoreValueLabel = new Label("0");
        scoreValueLabel.setPadding(new Insets(0,40,0,0));
        highScoreLabel = new Label("High Score: ");
        highScoreValueLabel = new Label(highScore + "");
        highScoreValueLabel.setPadding(new Insets(0,40,0,0));
        livesRemainingLabel = new Label("Lives Remaining: ");
        lifeImages = new ImageView[3];
        lifeImages[0] = new ImageView(goodGuyImagePath);
        lifeImages[0].setFitHeight(20.0);
        lifeImages[0].setFitWidth(20.0);
        lifeImages[1] = new ImageView(goodGuyImagePath);
        lifeImages[1].setFitHeight(20.0);
        lifeImages[1].setFitWidth(20.0);
        lifeImages[2] = new ImageView(goodGuyImagePath);
        lifeImages[2].setFitHeight(20.0);
        lifeImages[2].setFitWidth(20.0);
        livesRemainingHBox = new HBox();
        livesRemainingHBox.getChildren().addAll(lifeImages[0], lifeImages[1], lifeImages[2]);
        livesRemaining = 3;

        this.getChildren().addAll(scoreLabel, scoreValueLabel, highScoreLabel, highScoreValueLabel, livesRemainingLabel, livesRemainingHBox);
    }

    public void setScoreText(int score){
        scoreValueLabel.setText(score + "");
    }

    public void setHighScoreValueLabel(int highScore){
        highScoreValueLabel.setText(highScore + "");
    }

    public int getLivesRemaining() {
        return livesRemaining;
    }

    public int decrementLivesRemaining(){
        switch(livesRemaining) {
            case 3:
                livesRemaining = 2;
                lifeImages[2].setVisible(false);
                return livesRemaining;
            case 2:
                livesRemaining = 1;
                lifeImages[1].setVisible(false);
                return livesRemaining;
            case 1:
                livesRemaining = 0;
                lifeImages[0].setVisible(false);
                return livesRemaining;
            default:
                livesRemaining = -1;
                return livesRemaining;
        }
    }
}
