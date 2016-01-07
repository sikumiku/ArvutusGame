package main;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.TimerTask;

/**
 * Created by Siku on 17/12/15.
 * Klass, mis loob ja seadistab mängu countdown taimeri.
 */
public class Timer {

    java.util.Timer timer;
    int countDown;
    Label countDownText;
    Game currentGame;

    public Timer (Game currentGame) {
        countDownText = new Label();
        countDown = GameConfiguration.TIMERSTARTCOUNT;
        timer = new java.util.Timer();
        this.currentGame = currentGame;
    }

    public void startTimer() {
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(() -> {
                    countDown--;

                    countDownText.setText(Integer.toString(countDown));
                    countDownText.setTextFill(Color.RED);
                    countDownText.setStyle("-fx-font-size: 4em;");

                    if (countDown == 0) {
                        currentGame.endGame();
                        timer.cancel();
                    }
                });
            }
        },1000, 1000);
    }
    public Label getLabel() {
        return countDownText;
    }
}
