package main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * Created by Siku on 13/12/15.
 */
public class Equation {
    Integer firstMember;
    Integer secondMember;
    Sign sign;
    Label label;

    public Equation(GameLevel level) {
        generateRandomValues(level);
    }

    public void generateRandomValues(GameLevel level) {
        // vali suvalised v22rtused esimeseks ja teiseks parameetriks
        Sign[] signs = Sign.values();
        Random random = new Random();
        sign = signs[random.nextInt(3)];
        // loo 3 erinevat levelit suvaliste v22rtuste tekitamiseks
        switch (level) {
            case ONE: {
                firstMember = 1 + random.nextInt(1); //esimene arv on 1-7
                secondMember = 1 + random.nextInt(7); //teine arv on 1-7
                break;
            }
            case TWO: {

                firstMember = 4 + random.nextInt(12); //esimene arv on 4-12
                secondMember = 4 + random.nextInt(12); //teine arv on 4-12
                break;
            }
            case THREE: {
                firstMember = 10 + random.nextInt(20); //esimene arv on 10-20
                secondMember = 10 + random.nextInt(20); //teine arv on 10-20
                break;
            }

        }

        generateLabel();
    }

    public boolean isCorrectAnswer(double responseToTest) {
        // checks if value is true
        switch (sign) {
            case PLUS: {
                return (firstMember + secondMember) == responseToTest;
            }
            case MINUS: {
                return (firstMember - secondMember) == responseToTest;
            }
            case MULTIPLY: {
                return (firstMember * secondMember) == responseToTest;
            }

        }
        return true;
    }

    /*/public Label looEsimeseleV22rtuselePilt(int firstMember) {
        Label numberyks = new Label();
        if (firstMember == 1) {
            Image image = new Image("main/numberyks.png");
            ImageView pilt1 = new ImageView();
            pilt1.setImage(image);
        }
        return numberyks;
    }/*/

    private void generateLabel() {
        if (label == null) {
            label = new Label(firstMember.toString() + " " + sign.getSignCharacter() + " " + secondMember.toString() + " = ");
        } else {
            label.setText(firstMember + " " + sign.getSignCharacter() + " " + secondMember.toString() + " = ");
        }
    }



    public Label getLabel() {
        return label;
    }
}
