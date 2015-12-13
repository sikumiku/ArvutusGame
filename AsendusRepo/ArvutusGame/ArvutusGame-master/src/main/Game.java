package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Siku on 13/12/15.
 */
public class Game extends Application {

    GridPane maailm;
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        maailm = new GridPane();

        Equation firstEquation = new Equation(GameLevel.ONE);
        maailm.add(firstEquation.getLabel(), 0, 0);

        TextField answer = new TextField();
        maailm.add(answer, 1, 0);



        Scene scene = new Scene(maailm);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));

        primaryStage.show();

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
