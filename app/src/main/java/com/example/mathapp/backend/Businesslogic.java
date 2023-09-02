package com.example.mathapp.backend;

import com.example.mathapp.vertrag.LogicOfTheGame;

import java.util.Random;

/**
 * This class represents the model of the MVC-Pattern.
 */
public class Businesslogic implements LogicOfTheGame {

    private int product  = 0;
    private int cnt = 0;
    private int MAX_GAME_POINTS = 5;


    //TODO Please change the modificator!
    public void setCnt(int point) {
        this.cnt = point + 1;
    }


    //TODO Please change the modificator!
    public void setProduct(int number1, int number2) {
        this.product = number1 * number2;
    }


    @Override
    public int getProduct() {
        return this.product;
    }


    @Override
    public int getCnt() {
        return this.cnt;
    }


    @Override
    public int getMAX_GAME_POINTS() {
        return this.MAX_GAME_POINTS;
    }


    @Override
    public int getFirstNumber(){
        Random firstnumber = new Random();
        return firstnumber.nextInt(10);
    }

    @Override
    public int getSecondNumber(){
        Random secoundNumber = new Random();
        return secoundNumber.nextInt(10);
    }


}
