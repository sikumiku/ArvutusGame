package main;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.TimerTask;

/**
 * Created by Siku on 17/12/15.
 */
public class Timer {


    java.util.Timer timer;
    int countDown;
    Label countDownText;
    //Game currentGame;

    public Timer () {
        countDownText = new Label();
        countDown = 60;
        timer = new java.util.Timer();
        //currentGame = ;
    }

    public void startTimer() {
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        countDown--;

                        countDownText.setText(Integer.toString(countDown));
                        countDownText.setTextFill(Color.RED);
                        countDownText.setStyle("-fx-font-size: 4em;");

                        if (countDown == 0)
                            timer.cancel();
                            //currentGame.endGame();
                    }
                });
            }
        },1000, 1000);

    }
    public Label getLabel() {
        return countDownText;
    }
}
