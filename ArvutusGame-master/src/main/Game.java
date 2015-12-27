package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Siku on 13/12/15.
 */
public class Game extends Application {

    GridPane maailm;
    StackPane m2nguTaust;
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        seadistaStseen();
        primaryStage.show();

    }

    private void seadistaStseen() {
        m2nguTaust = new StackPane();
        Rectangle taustapilt = new Rectangle(750,500);
        taustapilt.setFill(Color.YELLOW);
        m2nguTaust.getChildren().add(taustapilt);

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
        TextField answer = new TextField();
        maailm.add(answer, 5, 1);

        Equation firstEquation = new Equation(GameLevel.ONE);
        Timer runTimer = new Timer();
        Buttons tekitaNupp1 = new Buttons(answer);
        maailm.add(firstEquation.getLabel(), 2, 1);
        maailm.add(runTimer.getLabel(), 6, 3);
        maailm.add(tekitaNupp1.getLabel(), 3, 3);

        runTimer.startTimer();



        Scene scene = new Scene(m2nguTaust);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));

        answer.setOnAction((event) -> {
            String input = answer.getText();
            if (firstEquation.isCorrectAnswer(Double.parseDouble(input))) {
                answer.setStyle("-fx-control-inner-background: #008800");
                firstEquation.generateRandomValues(GameLevel.ONE);


            } else {
                answer.setStyle("-fx-control-inner-background: #880000");
            }
        });
    }
}
