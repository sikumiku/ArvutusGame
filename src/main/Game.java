package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by Siku on 13/12/15.
 */
public class Game extends Application {

    GridPane maailm;
    StackPane m2nguTaust;
    Stage stage;
    Equation firstEquation;
    TextField answer;
    BackgroundImage taustapilt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        seadistaStseen();
        primaryStage.show();

    }

    private void seadistaStseen() {
        m2nguTaust = new StackPane();
        taustapilt = new BackgroundImage(new Image("main/m2ngutaust.png",750,500,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        m2nguTaust.setBackground(new Background(taustapilt));

        maailm = new GridPane();
        maailm.setHgap(27);
        maailm.setVgap(27);
        maailm.getColumnConstraints().add(new ColumnConstraints(133));
        maailm.getColumnConstraints().add(new ColumnConstraints(68));
        maailm.getColumnConstraints().add(new ColumnConstraints(63));
        maailm.getColumnConstraints().add(new ColumnConstraints(63));
        maailm.getColumnConstraints().add(new ColumnConstraints(63));
        maailm.getColumnConstraints().add(new ColumnConstraints(32));
        maailm.getColumnConstraints().add(new ColumnConstraints(166));
        maailm.getRowConstraints().add(new RowConstraints(6));
        maailm.getRowConstraints().add(new RowConstraints(79));
        maailm.getRowConstraints().add(new RowConstraints(63));
        maailm.getRowConstraints().add(new RowConstraints(63));
        maailm.getRowConstraints().add(new RowConstraints(63));
        maailm.getRowConstraints().add(new RowConstraints(91));
        maailm.setGridLinesVisible(true);

        m2nguTaust.getChildren().add(maailm);
        answer = new TextField();
        answer.setPrefSize(100, 100);
        answer.setBackground(Background.EMPTY);
        answer.setFont(Font.font("Impact", 40));
        maailm.add(answer, 5, 1, 2, 1);

        firstEquation = new Equation(GameLevel.ONE);
        Timer runTimer = new Timer();
        Buttons numPad = new Buttons(answer, this);

        maailm.add(firstEquation.getLabel(), 2, 1);
        maailm.add(runTimer.getLabel(), 6, 3);
        maailm.add(numPad.getButton1Label(), 2, 2);
        maailm.add(numPad.getButton2Label(), 3, 2);
        maailm.add(numPad.getButton3Label(), 4, 2);
        maailm.add(numPad.getButton4Label(), 2, 3);
        maailm.add(numPad.getButton5Label(), 3, 3);
        maailm.add(numPad.getButton6Label(), 4, 3);
        maailm.add(numPad.getButton7Label(), 2, 4);
        maailm.add(numPad.getButton8Label(), 3, 4);
        maailm.add(numPad.getButton9Label(), 4, 4);
        maailm.add(numPad.getButtonMinusLabel(), 2, 5);
        maailm.add(numPad.getButton0Label(), 3, 5);
        maailm.add(numPad.getButtonDeleteLabel(), 4, 5);
        maailm.add(numPad.getButtonVastaLabel(), 5, 5);
        maailm.add(numPad.getLabelKitty(), 0, 4);

        runTimer.startTimer();

        Scene scene = new Scene(m2nguTaust);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));

        answer.setOnAction((event) -> { //Muuda textboxi v2rvi, kui vastus on vale v6i 6ige - debuggimiseks
            checkEquation();
        });
    }

    public void checkEquation() {
        String input = answer.getText();
        if (firstEquation.isCorrectAnswer(Double.parseDouble(input))) {
            answer.setStyle("-fx-control-inner-background: #008800");
            firstEquation.generateRandomValues(GameLevel.ONE);
            answer.clear();
        } else {
            answer.setStyle("-fx-control-inner-background: #880000");
            answer.clear();
        }
    }

    /*/public void endGame() {
        Label tekst = new Label("Võitsid!");
        Button v6itsid = new Button();
        StackPane v6iduStseen = new StackPane();
        v6iduStseen.getChildren().add(tekst);
        v6iduStseen.getChildren().add(v6itsid);
        Scene stseen = new Scene(v6iduStseen, 750, 500);
        stage.setScene(stseen);
    }/*/
}
