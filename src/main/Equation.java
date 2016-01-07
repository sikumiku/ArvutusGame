package main;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.Random;

/**
 * Created by Siku on 13/12/15.
 * Klassis luuakse suvaline 1. ja 2. number olenevalt levelist, kalkuleeritakse suvaliste arvude tehte vastus ning
 * luuakse tehte Label m‰ngu jaoks, mida ka m‰ngijatele kuvatakse.
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
        // loo maatriks, mida saab kasutada, et valida suvaline tehtem‰rk 3 erineva tehtem‰rgi
        // seast (hangitakse Sign enum klassist)
        Sign[] signs = Sign.values();
        Random random = new Random();
        sign = signs[random.nextInt(3)];
        // anna m‰ngule teada, milliste suvaliste v‰‰rtustega arvud kuvada 3 erineva leveli puhul. V‰‰rtuste piirkonda
        // kontrollitakse GameConfiguration klassis.
        switch (level) {
            case ONE: {
                firstMember = GameConfiguration.TASE1_LOWEST_NUMBER + random.nextInt(GameConfiguration.TASE1_HIGHEST_NUMBER);
                secondMember = GameConfiguration.TASE1_LOWEST_NUMBER + random.nextInt(GameConfiguration.TASE1_HIGHEST_NUMBER);
                break;
            }
            case TWO: {
                firstMember = GameConfiguration.TASE2_LOWEST_NUMBER + random.nextInt(GameConfiguration.TASE2_HIGHEST_NUMBER);
                secondMember = GameConfiguration.TASE2_LOWEST_NUMBER + random.nextInt(GameConfiguration.TASE2_HIGHEST_NUMBER);
                break;
            }
            case THREE: {
                firstMember = GameConfiguration.TASE3_LOWEST_NUMBER + random.nextInt(GameConfiguration.TASE3_HIGHEST_NUMBER);
                secondMember = GameConfiguration.TASE3_LOWEST_NUMBER + random.nextInt(GameConfiguration.TASE3_HIGHEST_NUMBER);
                break;
            }
        }
        generateLabel();
    }

    //arvutatakse, mis on tehte tegelik vastus olenevalt sellest, kas tehe on liitmine, lahutamine vıi korrutamine
    public boolean isCorrectAnswer(double responseToTest) {
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

    //Kui labelit ei ole, luuakse tehte Label, mida kuvatakse m‰ngus ja antakse font ning fondi suurus.
    private void generateLabel() {
        if (label == null) {
            label = new Label(firstMember.toString() + " " + sign.getSignCharacter() + " " + secondMember.toString() + " = ");
        } else {
            label.setText(firstMember + " " + sign.getSignCharacter() + " " + secondMember.toString() + " = ");
        }
        label.setFont(new Font("Impact", 60));
    }

    public Label getLabel() {
        return label;
    }
}
