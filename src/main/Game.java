package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    ImageView giffy;
    private int correctAnswerCounter;
    GameLevel currentlevel;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        displayStartScreen();
        primaryStage.show();

    }

    private void displayStartScreen(){
        Pane startTaust = new Pane();
        BackgroundImage startTaustPilt = new BackgroundImage(new Image("main/esileht.png",750,500,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        startTaust.setBackground(new Background(startTaustPilt));


        ImageView alusta = new ImageView(new Image("main/buttonAlusta.png"));
        Button startGameButton = new Button("", alusta);
        startGameButton.setLayoutX(540);
        startGameButton.setLayoutY(405);
        startGameButton.setOnAction(event -> newGameScene(GameLevel.ONE));
        startGameButton.setPadding(Insets.EMPTY);

        startTaust.getChildren().add(startGameButton);

        Scene scene = new Scene(startTaust, 750, 500);
        stage.setScene(scene);

    }

    /*giffy = new ImageView();
    Image kittypiltlevel1 = new Image("main/kittylevel1.png");
    giffy.setImage(kittypiltlevel1);
    maailm.add(giffy, 0,4);*/

    private ImageView setGiffies(GameLevel level){
        giffy = new ImageView();
        switch (level) {
            case ONE: {
                Image kittypiltlevel1 = new Image("main/kittylevel1.png");
                giffy.setImage(kittypiltlevel1);
                break;
            }
            case TWO: {
                Image kittypiltlevel2 = new Image("main/kittylevel2.png");
                giffy.setImage(kittypiltlevel2);
                break;
            }
            case THREE: {
                Image kittypiltlevel3 = new Image("main/kittylevel3.png");
                giffy.setImage(kittypiltlevel3);
                break;
            }
        }return giffy;
    }

    public void newGameScene(GameLevel level) {
        currentlevel = level;
        correctAnswerCounter = 0;
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
        maailm.getRowConstraints().add(new RowConstraints(63));
        maailm.getRowConstraints().add(new RowConstraints(1));

        m2nguTaust.getChildren().add(maailm);
        answer = new TextField();
        answer.setPrefSize(100, 100);
        answer.setBackground(Background.EMPTY);
        answer.setFont(Font.font("Impact", 60));
        maailm.add(answer, 4, 1, 3, 1);

        Timer runTimer = new Timer(this);
        Buttons numPad = new Buttons(answer, this);

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

        firstEquation = new Equation(level);

        maailm.add(setGiffies(currentlevel), 0, 4);

        maailm.add(firstEquation.getLabel(), 2, 1, 3, 1);


        runTimer.startTimer();

        Scene scene2 = new Scene(m2nguTaust);
        stage.setScene(scene2);
        stage.setOnCloseRequest(event -> System.exit(0));

        answer.setOnAction((event) -> checkEquation());
    }

    public void checkEquation() {
        String input = answer.getText();
        if (firstEquation.isCorrectAnswer(Double.parseDouble(input))) {
            correctAnswerCounter++;
            if (currentlevel == GameLevel.ONE){
                Image happykittygif1 = new Image("main/happykittylevel1.gif");
                giffy.setImage(happykittygif1);
            } else if (currentlevel == GameLevel.TWO){
                Image happykittygif2 = new Image("main/happykittylevel2.gif");
                giffy.setImage(happykittygif2);
            } else{
                Image happykittygif3 = new Image("main/happykittylevel3.gif");
                giffy.setImage(happykittygif3);
            }
            firstEquation.generateRandomValues(currentlevel);
            answer.clear();
        } else {
            if (currentlevel == GameLevel.ONE){
                Image sadkittygif1 = new Image("main/sadkittylevel1.gif");
                giffy.setImage(sadkittygif1);
            } else if (currentlevel == GameLevel.TWO){
                Image sadkittygif2 = new Image("main/sadkittylevel2.gif");
                giffy.setImage(sadkittygif2);
            } else{
                Image sadkittygif3 = new Image("main/sadkittylevel3.gif");
                giffy.setImage(sadkittygif3);
            }
            answer.clear();
        }
    }

    public void endGame() {
        if (correctAnswerCounter >= 10) {
            Pane v6iduTaust = new Pane();
            BackgroundImage plainTaustPilt = new BackgroundImage(new Image("main/plainTaust.png",750,500,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            v6iduTaust.setBackground(new Background(plainTaustPilt));
            ImageView tubli = new ImageView(new Image("main/winGamePilt.png"));
            tubli.setLayoutX(290);
            tubli.setLayoutY(150);

            ImageView j2tka = new ImageView(new Image("main/buttonJ2tka.png"));
            Button j2tkaGameButton = new Button("", j2tka);
            j2tkaGameButton.setLayoutX(290);
            j2tkaGameButton.setLayoutY(310);
            if (currentlevel == GameLevel.ONE) {
                j2tkaGameButton.setOnAction(event -> newGameScene(GameLevel.TWO));
            } else if (currentlevel == GameLevel.TWO) {
                j2tkaGameButton.setOnAction(event -> newGameScene(GameLevel.THREE));
            } else {
                j2tkaGameButton.setOnAction(event -> v6iduScene());
            }

            j2tkaGameButton.setPadding(Insets.EMPTY);

            v6iduTaust.getChildren().add(tubli);
            v6iduTaust.getChildren().add(j2tkaGameButton);

            Scene scene = new Scene(v6iduTaust, 750, 500);
            stage.setScene(scene);
        } else {

            Pane kaotusTaust = new Pane();
            BackgroundImage plainTaustPilt = new BackgroundImage(new Image("main/plainTaust.png", 750, 500, false, true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            kaotusTaust.setBackground(new Background(plainTaustPilt));
            ImageView mitteTubli = new ImageView(new Image("main/loseGamePilt.png"));
            mitteTubli.setLayoutX(145);
            mitteTubli.setLayoutY(140);

            ImageView uuesti = new ImageView(new Image("main/buttonUuesti.png"));
            Button uuestiGameButton = new Button("", uuesti);
            uuestiGameButton.setLayoutX(230);
            uuestiGameButton.setLayoutY(310);
            uuestiGameButton.setOnAction(event -> newGameScene(GameLevel.ONE));
            uuestiGameButton.setPadding(Insets.EMPTY);

            kaotusTaust.getChildren().add(mitteTubli);
            kaotusTaust.getChildren().add(uuestiGameButton);

            Scene scene = new Scene(kaotusTaust, 750, 500);
            stage.setScene(scene);
        }
    }

    private void v6iduScene() {
        Pane v6iduTaust = new Pane();
        BackgroundImage v6iduPilt = new BackgroundImage(new Image("main/finalWinImage.png",750,500,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        v6iduTaust.setBackground(new Background(v6iduPilt));

        ImageView uusm2ng = new ImageView(new Image("main/buttonUusM2ng.png"));
        Button uusM2ngButton = new Button("", uusm2ng);
        uusM2ngButton.setLayoutX(530);
        uusM2ngButton.setLayoutY(420);
        uusM2ngButton.setOnAction(event -> newGameScene(GameLevel.ONE));

        uusM2ngButton.setPadding(Insets.EMPTY);

        v6iduTaust.getChildren().add(uusM2ngButton);

        Scene scene = new Scene(v6iduTaust, 750, 500);
        stage.setScene(scene);
    }
}
