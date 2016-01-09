package main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Siku on 26/12/15.
 * Loob nupud mänguekraanis kasutamiseks Game klassi sees. Iga nupp kas vahetab väärtust vastuses,
 * kustutab vastuse või saadab vastuse pärimiseks (checkEquation()), kas on õige vastus.
 */
public class Buttons {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button buttonMinus;
    Button buttonDelete;
    Button buttonVasta;
    Button startGame;

    public Buttons(TextField answerField, Game currentGame) {
        ImageView number1 = new ImageView(new Image("main/graphics/button1.png"));
        button1 = new Button("", number1);
        button1.setOnAction(event -> answerField.setText(answerField.getText() + "1"));
        button1.setPadding(Insets.EMPTY);
        button1.setStyle("-fx-background-color: transparent");

        ImageView number2 = new ImageView(new Image("main/graphics/button2.png"));
        button2 = new Button("", number2);
        button2.setOnAction(event -> answerField.setText(answerField.getText() + "2"));
        button2.setPadding(Insets.EMPTY);
        button2.setStyle("-fx-background-color: transparent");

        ImageView number3 = new ImageView(new Image("main/graphics/button3.png"));
        button3 = new Button("", number3);
        button3.setOnAction(event -> answerField.setText(answerField.getText() + "3"));
        button3.setPadding(Insets.EMPTY);
        button3.setStyle("-fx-background-color: transparent");

        ImageView number4 = new ImageView(new Image("main/graphics/button4.png"));
        button4 = new Button("", number4);
        button4.setOnAction(event -> answerField.setText(answerField.getText() + "4"));
        button4.setPadding(Insets.EMPTY);
        button4.setStyle("-fx-background-color: transparent");

        ImageView number5 = new ImageView(new Image("main/graphics/button5.png"));
        button5 = new Button("", number5);
        button5.setOnAction(event -> answerField.setText(answerField.getText() + "5"));
        button5.setPadding(Insets.EMPTY);
        button5.setStyle("-fx-background-color: transparent");

        ImageView number6 = new ImageView(new Image("main/graphics/button6.png"));
        button6 = new Button("", number6);
        button6.setOnAction(event -> answerField.setText(answerField.getText() + "6"));
        button6.setPadding(Insets.EMPTY);
        button6.setStyle("-fx-background-color: transparent");

        ImageView number7 = new ImageView(new Image("main/graphics/button7.png"));
        button7 = new Button("", number7);
        button7.setOnAction(event -> answerField.setText(answerField.getText() + "7"));
        button7.setPadding(Insets.EMPTY);
        button7.setStyle("-fx-background-color: transparent");

        ImageView number8 = new ImageView(new Image("main/graphics/button8.png"));
        button8 = new Button("", number8);
        button8.setOnAction(event -> answerField.setText(answerField.getText() + "8"));
        button8.setPadding(Insets.EMPTY);
        button8.setStyle("-fx-background-color: transparent");

        ImageView number9 = new ImageView(new Image("main/graphics/button9.png"));
        button9 = new Button("", number9);
        button9.setOnAction(event -> answerField.setText(answerField.getText() + "9"));
        button9.setPadding(Insets.EMPTY);
        button9.setStyle("-fx-background-color: transparent");

        ImageView number0 = new ImageView(new Image("main/graphics/button0.png"));
        button0 = new Button("", number0);
        button0.setOnAction(event -> answerField.setText(answerField.getText() + "0"));
        button0.setPadding(Insets.EMPTY);
        button0.setStyle("-fx-background-color: transparent");

        ImageView numberMinus = new ImageView(new Image("main/graphics/buttonminus.png"));
        buttonMinus = new Button("", numberMinus);
        buttonMinus.setOnAction(event -> answerField.setText("-"));
        buttonMinus.setPadding(Insets.EMPTY);
        buttonMinus.setStyle("-fx-background-color: transparent");

        ImageView numberDelete = new ImageView(new Image("main/graphics/buttonback.png"));
        buttonDelete = new Button("", numberDelete);
        buttonDelete.setOnAction(event -> answerField.clear());
        buttonDelete.setPadding(Insets.EMPTY);
        buttonDelete.setStyle("-fx-background-color: transparent");

        ImageView numberVasta = new ImageView(new Image("main/graphics/buttonVasta.png"));
        buttonVasta = new Button("", numberVasta);
        buttonVasta.setOnAction(event -> currentGame.checkEquation());
        buttonVasta.setPadding(Insets.EMPTY);
        buttonVasta.setStyle("-fx-background-color: transparent");
    }

    public Button getButton1Label () {
        return button1;
    }

    public Button getButton2Label() {
        return button2;
    }

    public Button getButton3Label() {
        return button3;
    }

    public Button getButton4Label() {
        return button4;
    }

    public Button getButton5Label() {
        return button5;
    }

    public Button getButton6Label() {
        return button6;
    }

    public Button getButton7Label() {
        return button7;
    }

    public Button getButton8Label() {
        return button8;
    }

    public Button getButton9Label() {
        return button9;
    }

    public Button getButton0Label() {
        return button0;
    }

    public Button getButtonMinusLabel() {
        return buttonMinus;
    }

    public Button getButtonDeleteLabel() {
        return buttonDelete;
    }

    public Button getButtonVastaLabel() {
        return buttonVasta;
    }

    public Button getButtonStartGame() {
        return startGame;
    }

}


