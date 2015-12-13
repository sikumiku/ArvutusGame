package main;

/**
 * Created by Siku on 13/12/15.
 */
public enum Sign {
    PLUS("+"), MINUS("-"), MULTIPLY("*");

    String signCharacter;

    Sign(String signCharacter) {
        this.signCharacter = signCharacter;
    }

    public String getSignCharacter() {
        return signCharacter;
    }
}
