package com.example.arvutusgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Siku on 30/10/15.
 */
public class LaevadePommitamine { //ka muutujat saab teha staatiliseks
    static int [][] laud; //selline asi nagu laud on olemas. salvestatud klassi kylge, nyyd saab kasutada yksk6ik kus
    public static void main(String[] args) {


        //m2ngulaud tekitada
        generateTable () ; //et teada, et pean meetodi kirjutama (meetod ei ole veel aktiivne!)
        //T2ida laud laevadega
        fillTablewithShips () ; //meetodi formaat on selline, et nimi + sulud
        //Kasutajalt kysid, kuhu ta pommitada tahab
        askWheretoBomb ();
        //Pommitamise tulemus
        resultofBombing();
        //Kontrollida kas on laevu
        anyShipsLeft ();
        //Kui laevad otsas, siis m2ng l2bi v6i uuesti step 3 juurde
        if (/* no ships left*/) {
            //end game
        }
    }

    private static void generateTable() {
        HashMap<String, Object> a = new HashMap(); //<> vahele saab panna, mida m22ratakse
        a.put("Toomas", 5); //v6tmete kirjutamine ja v22rtuste andmine
        a.put("Janne", 4);
        System.out.println(a.toString());
        //static ei kasuta v2ljatpoolt infot, see kasutab enda oma.
        ArrayList a = new ArrayList(); //arraylist v6ib kanda nii Stringe kui ka inte
        a.add(7);
        a.add(9);
        a.add(8);
        a.add(54);
        System.out.println(a.toString());
        // kas on klass orienteeritud v6i objekt orienteeritud, kas on static vaja
        //a[0] = 5; //v6ib teha nii, et tekitad massiivi, aga paindlikum on teha ArrayList
        //a[1] = 6;
        //a[2] = 7;
        //a[3] = 4;
        //System.out.println(Arrays.toString(a));
        // vajutades alt enter veel mitte genereeritud meetodi peale, tekitab intellij selle meetodi ise
    }

    public static void resultofBombing {
        //meetodeid endaid tuleb kirjutada
        //kui kasutaja sai pihta
        //main meetodis tegelt ei tohiks palju koodi olla, et see oleks loetav. Hiljem peaks olema loetav.
        pihtas ();
        // kui ei saanud pihta, siis
        m66das ();
    }
}
