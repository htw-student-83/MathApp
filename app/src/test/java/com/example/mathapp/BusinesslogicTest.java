package com.example.mathapp;

import com.example.mathapp.backend.Businesslogic;

import org.junit.Assert;
import org.junit.Test;

public class BusinesslogicTest {

    /**
     * Das Produkt aus zwei Zahlen wird gebildet.
     */
    @Test
    public void multiplikationsTest(){
        int zahl1  = 10;
        int zahl2 = 20;
        Businesslogic model = new Businesslogic();
        model.setProduct(zahl1, zahl2);
        Assert.assertEquals(model.getProduct(), model.getProduct());
    }

    /**
     * Prüft, ob die maximale Punktezahl zu Beginn richig gesetzt wird
     */
    @Test
    public void initialMaximalePunkteTest(){
        int maxPointsOfGme = 20;
        Businesslogic model = new Businesslogic();
        Assert.assertEquals(maxPointsOfGme, model.getMAX_GAME_POINTS());
    }

    /**
     * Prüft, ob eine richtige Zufallszahl erzeugt wird
     */
    @Test
    public void properNumberTest(){
        Businesslogic model = new Businesslogic();
        Assert.assertTrue(model.getFirstNumber() > -1);
        Assert.assertTrue(model.getFirstNumber() < 11);
    }

    /**
     * Prüft, ob eine richtige Zufallszahl erzeugt wird
     */
    @Test
    public void properNumber2Test(){
        Businesslogic model = new Businesslogic();
        Assert.assertTrue(model.getSecondNumber() > -1);
        Assert.assertTrue(model.getFirstNumber() < 20);
    }

    /**
     * Prüft, ob zu Beginn eines Spiels die Anzahl der Runden richtig gesetzt ist
     */
    @Test
    public void numberofRoundsTest(){
        int start = 0;
        Businesslogic model = new Businesslogic();
        Assert.assertTrue(model.getCurrent_round() == start);
    }
}

