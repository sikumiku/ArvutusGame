package main;

import javafx.scene.control.Label;

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
        // pick random values for the first and the second parameter
        Sign[] signs = Sign.values();
        Random random = new Random();
        sign = signs[random.nextInt(2)];
        switch (level) {
            case ONE: {
                firstMember = 1 + random.nextInt(5);
                secondMember = 1 + random.nextInt(5);
                break;
            }
            case TWO: {

                firstMember = 4 + random.nextInt(6);
                secondMember = 4 + random.nextInt(6);
                break;
            }
            case THREE: {
                firstMember = 10 + random.nextInt(10);
                secondMember = 10 + random.nextInt(10);
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


    private void generateLabel() {
        if (label == null) {
            label = new Label(firstMember.toString() + " " + sign.getSignCharacter() + " " + secondMember.toString() + " = ");
        } else {
            label.setText(firstMember.toString() + " " + sign.getSignCharacter() + " " + secondMember.toString() + " = ");
        }
    }

    public Label getLabel() {
        return label;
    }
}
