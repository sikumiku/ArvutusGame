package main;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Siku on 26/12/15.
 */
public class Buttons {
    Button button5;

    public Buttons(TextField answerField) {
        ImageView number5 = new ImageView(new Image("main/button5.png"));
        button5 = new Button("", number5);
        button5.setOnAction(event -> answerField.setText("5"));
    }

    public Button getLabel() {
        return button5;
    }
}

