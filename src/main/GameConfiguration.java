package main;

/**
 * Created by Siku on 7/1/16.
 * Seadistab kogu mängu.
 */
public class GameConfiguration {
    public static final int TIMERSTARTCOUNT = 61; //Mitu sekundit kestab üks level.

    //Mis on kõige madalam väärtus, mida iga leveli puhul võidakse kuvada.
    public static final int TASE1_LOWEST_NUMBER = 1;
    public static final int TASE2_LOWEST_NUMBER = 3;
    public static final int TASE3_LOWEST_NUMBER = 6;

    //Mis on kõige kõrgem väärtus, mida iga leveli puhul võidakse kuvada (LOW NUMBER + antud number).
    public static final int TASE1_HIGHEST_NUMBER = 6;
    public static final int TASE2_HIGHEST_NUMBER = 6;
    public static final int TASE3_HIGHEST_NUMBER = 6;
}
