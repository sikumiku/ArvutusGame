package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Siku on 13/12/15.
 * Põhiline klass, kus kontrollitakse kõiki stseene ning kogu mängu.
 */
public class Game extends Application {

    public static final int NUMPAD_BUTTON_WIDTH = 63;
    GridPane gameGrid;
    StackPane m2nguTaust;
    Stage stage;
    Equation firstEquation;
    TextField answer;
    BackgroundImage taustapilt;
    ImageView kittyImage;
    private int correctAnswerCounter;
    GameLevel currentlevel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        displayStartScreen();
        primaryStage.show();
    }

    //Loob ja kuvab esimese stseeni, kui mäng käivitatakse. Siit liigutakse edasi päris mängu (newGameScene()) peale
    // ALUSTA nupu vajutamist.
    private void displayStartScreen(){
        Pane startTaust = new Pane();
        BackgroundImage startTaustPilt = getBackGroundImage("main/graphics/esileht.png");
        startTaust.setBackground(new Background(startTaustPilt));

        ImageView alusta = new ImageView(new Image("main/graphics/buttonAlusta.png"));
        Button startGameButton = new Button("", alusta);
        startGameButton.setLayoutX(540);
        startGameButton.setLayoutY(405);
        startGameButton.setOnAction(event -> newGameScene(GameLevel.ONE));
        startGameButton.setPadding(Insets.EMPTY);

        startTaust.getChildren().add(startGameButton);

        Scene scene = new Scene(startTaust, 750, 500);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    //Kuna erinevaid taustasid kuvatakse erinevates stseenides, luuakse taustapilt siin meetodis, kuhu on vajalik
    //input (String), milline fail kuvatakse taustapildiks.
    private BackgroundImage getBackGroundImage(String backgroundImageFile) {
        return new BackgroundImage(new Image(backgroundImageFile, 750, 500, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
    }

    //Loob default kiisupildi mängus. Milline pilt luuakse, seda kontrollitakse järgmises meetodis getNeutralKitty().
    private ImageView createKittyImage(GameLevel level){
        kittyImage = new ImageView();
        kittyImage.setImage(getNeutralKitty(level));
        return kittyImage;
    }

    //Hangi default kiisupildi graafika failidest olenevalt levelist.
    private Image getNeutralKitty(GameLevel level){
        switch (level) {
            case ONE: {
                return new Image("main/graphics/kittylevel1.png");
            }
            case TWO: {
                return new Image("main/graphics/kittylevel2.png");
            }
            case THREE: {
                return new Image("main/graphics/kittylevel3.png");
            }
            default:{
                return new Image("main/graphics/kittylevel1.png");
            }
        }
    }

    //Loob ja kuvab mänguekraani, kus on tehe, vastus (Textfield), kalkulaatori Numpad, vastuse nupp ning kiisu
    // graafika. Seejärel jooksutab Timeri. Kasutan GridPanei, sest paljud nupud on loogilises ruudustikus.
    public void newGameScene(GameLevel level) {
        currentlevel = level;
        m2nguTaust = new StackPane();
        //Vaheta tausta olenevalt sellest, mis level hetkel jookseb.
        if (currentlevel == GameLevel.ONE){
            taustapilt = getBackGroundImage("main/graphics/m2ngutaust.png");
        } else if (currentlevel == GameLevel.TWO) {
            taustapilt = getBackGroundImage("main/graphics/javam2ngutaust2.png");
        } else {
            taustapilt = getBackGroundImage("main/graphics/javam2ngutaust3.png");
        }
        m2nguTaust.setBackground(new Background(taustapilt));

        gameGrid = createGameGridPane();
        m2nguTaust.getChildren().add(gameGrid);

        answer = new TextField();
        answer.setPrefSize(100, 100);
        answer.setBackground(Background.EMPTY);
        answer.setFont(Font.font("Impact", 60));
        gameGrid.add(answer, 4, 1, 3, 1);

        Timer runTimer = new Timer(this);
        Buttons numPad = new Buttons(answer, this);

        gameGrid.add(runTimer.getLabel(), 6, 3);
        createNumPad(numPad);

        firstEquation = new Equation(level);
        gameGrid.add(firstEquation.getLabel(), 2, 1, 3, 1);

        gameGrid.add(createKittyImage(currentlevel), 0, 4);

        runTimer.startTimer();

        answer.setOnAction((event) -> checkEquation()); //Enterit vajutades kontrollib, kas vastus on õige.

        Scene scene = new Scene(m2nguTaust);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    //Määratakse mängu nuppude asukohad ekraanil (GridPane'is).
    private void createNumPad(Buttons numPad) {
        gameGrid.add(numPad.getButton1Label(), 2, 2);
        gameGrid.add(numPad.getButton2Label(), 3, 2);
        gameGrid.add(numPad.getButton3Label(), 4, 2);
        gameGrid.add(numPad.getButton4Label(), 2, 3);
        gameGrid.add(numPad.getButton5Label(), 3, 3);
        gameGrid.add(numPad.getButton6Label(), 4, 3);
        gameGrid.add(numPad.getButton7Label(), 2, 4);
        gameGrid.add(numPad.getButton8Label(), 3, 4);
        gameGrid.add(numPad.getButton9Label(), 4, 4);
        gameGrid.add(numPad.getButtonMinusLabel(), 2, 5);
        gameGrid.add(numPad.getButton0Label(), 3, 5);
        gameGrid.add(numPad.getButtonDeleteLabel(), 4, 5);
        gameGrid.add(numPad.getButtonVastaLabel(), 5, 5);
    }

    //Loob GridPane'i enda loogika. Veergude ja ridade vahe on alati 27 ning enamus veerud ning read on määratud
    //nupu laiuse alusel.
    private GridPane createGameGridPane() {
        GridPane gridPaneResult = new GridPane();
        gridPaneResult.setHgap(27);
        gridPaneResult.setVgap(27);
        gridPaneResult.getColumnConstraints().add(new ColumnConstraints(133));
        gridPaneResult.getColumnConstraints().add(new ColumnConstraints(63));
        gridPaneResult.getColumnConstraints().add(new ColumnConstraints(NUMPAD_BUTTON_WIDTH));
        gridPaneResult.getColumnConstraints().add(new ColumnConstraints(NUMPAD_BUTTON_WIDTH));
        gridPaneResult.getColumnConstraints().add(new ColumnConstraints(NUMPAD_BUTTON_WIDTH));
        gridPaneResult.getColumnConstraints().add(new ColumnConstraints(32));
        gridPaneResult.getColumnConstraints().add(new ColumnConstraints(166));
        gridPaneResult.getRowConstraints().add(new RowConstraints(6));
        gridPaneResult.getRowConstraints().add(new RowConstraints(79));
        gridPaneResult.getRowConstraints().add(new RowConstraints(NUMPAD_BUTTON_WIDTH));
        gridPaneResult.getRowConstraints().add(new RowConstraints(NUMPAD_BUTTON_WIDTH));
        gridPaneResult.getRowConstraints().add(new RowConstraints(NUMPAD_BUTTON_WIDTH));
        gridPaneResult.getRowConstraints().add(new RowConstraints(NUMPAD_BUTTON_WIDTH));
        gridPaneResult.getRowConstraints().add(new RowConstraints(1));
        return gridPaneResult;
    }

    //Meetod kontrollib, kas Textfieldi sisestatud väärtus võrdub sellega, mida on kalkuleerinud isCorrectAnswer() ja
    //selle põhjal, kas see on tõene või vale, kuvab rõõmsa või nukra kiisu graafika.
    public void checkEquation() {
        String input = answer.getText();
        if (firstEquation.isCorrectAnswer(Double.parseDouble(input))) {
            correctAnswerCounter++; //Igat õiget vastust loetakse, et teada, kas level on edukalt läbitud.
            kittyImage.setImage(getWinKittyImage());
            firstEquation.generateRandomValues(currentlevel); //Loo uus suvaline tehe.
            answer.clear();
        } else {
            kittyImage.setImage(getLoseKittyImage());
            answer.clear();
        }
    }

    //Hangib õige rõõmsa (õige vastuse) kiisu graafika olenevalt sellest, mis level hetkel käib.
    private Image getWinKittyImage() {
        if (currentlevel == GameLevel.ONE){
            return new Image("main/graphics/happykittylevel1.gif");
        } else if (currentlevel == GameLevel.TWO){
            return new Image("main/graphics/happykittylevel2.gif");
        } else{
            return new Image("main/graphics/happykittylevel3.gif");
        }
    }

    //Hangib õige kurva (vale vastuse) kiisu graafika olenevalt sellest, mis level hetkel käib.
    private Image getLoseKittyImage() {
        if (currentlevel == GameLevel.ONE){
            return new Image("main/graphics/sadkittylevel1.gif");
        } else if (currentlevel == GameLevel.TWO){
            return new Image("main/graphics/sadkittylevel2.gif");
        } else{
            return new Image("main/graphics/sadkittylevel3.gif");
        }
    }

    //meetod, mis jooksutatakse siis, kui Timer jõuab nulli iga level. Kui õigeid vastuseid on 10 või enam,
    //kuvatakse võiduekraan. Peale kolmandat levelit viib võiduekraan mängu lõpu võiduekraanini. Vale vastuse
    //puhul kuvatakse kaotusekraan ja nupp, mis alustab mängu (level1) uuesti.
    public void endGame() {
        if (correctAnswerCounter >= 10) {
            Pane v6iduTaust = new Pane();
            BackgroundImage plainTaustPilt = getBackGroundImage("main/graphics/plainTaust.png");
            v6iduTaust.setBackground(new Background(plainTaustPilt));
            ImageView tubli = new ImageView(new Image("main/graphics/winGamePilt.png"));
            tubli.setLayoutX(290);
            tubli.setLayoutY(150);

            ImageView j2tka = new ImageView(new Image("main/graphics/buttonJ2tka.png"));
            Button j2tkaGameButton = new Button("", j2tka);
            j2tkaGameButton.setLayoutX(290);
            j2tkaGameButton.setLayoutY(310);
            //Olenevalt levelist, käitub nupp erinevalt ja alustab kas järgmist levelit või kuvab mängu võiduekraani.
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
            stage.setOnCloseRequest(event -> System.exit(0));
        } else {
            Pane kaotusTaust = new Pane();
            BackgroundImage plainTaustPilt = getBackGroundImage("main/graphics/plainTaust.png");
            kaotusTaust.setBackground(new Background(plainTaustPilt));
            ImageView mitteTubli = new ImageView(new Image("main/graphics/loseGamePilt.png"));
            mitteTubli.setLayoutX(145);
            mitteTubli.setLayoutY(140);

            ImageView uuesti = new ImageView(new Image("main/graphics/buttonUuesti.png"));
            Button uuestiGameButton = new Button("", uuesti);
            uuestiGameButton.setLayoutX(230);
            uuestiGameButton.setLayoutY(310);
            uuestiGameButton.setOnAction(event -> newGameScene(GameLevel.ONE)); //Käivita level1 mäng uuesti.
            uuestiGameButton.setPadding(Insets.EMPTY);

            kaotusTaust.getChildren().add(mitteTubli);
            kaotusTaust.getChildren().add(uuestiGameButton);

            Scene scene = new Scene(kaotusTaust, 750, 500);
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> System.exit(0));
        }
    }

    //Loob ja kuvab võiduekraani, milleni pääseb vaid siis, kui kõik 3 levelit on eduakalt läbitud.
    private void v6iduScene() {
        Pane v6iduTaust = new Pane();
        BackgroundImage v6iduPilt = getBackGroundImage("main/graphics/finalWinImage.png");
        v6iduTaust.setBackground(new Background(v6iduPilt));

        ImageView uusm2ng = new ImageView(new Image("main/graphics/buttonUusM2ng.png"));
        Button uusM2ngButton = new Button("", uusm2ng);
        uusM2ngButton.setLayoutX(530);
        uusM2ngButton.setLayoutY(420);
        uusM2ngButton.setOnAction(event -> newGameScene(GameLevel.ONE)); //Käivita level1 mäng uuesti.

        uusM2ngButton.setPadding(Insets.EMPTY);

        v6iduTaust.getChildren().add(uusM2ngButton);

        Scene scene = new Scene(v6iduTaust, 750, 500);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));
    }
}