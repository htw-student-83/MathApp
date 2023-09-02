package com.example.mathapp.vertrag;

public interface LogicOfTheGame {

    /**
     * give the current product of two numbers out
     * @return the product
     */
    int getProduct();

    /**
     * give the current point of the game.
     * @return current point
     */
    int getCnt();

    /*
     * give the max points, which can be achieved.
     * @return max points
     */
    int getMAX_GAME_POINTS();

    /**
     * give the first Number of a multiplication.
     * @return first Number
     */
    int getFirstNumber();

    /**
     * give the second Number of a multiplication.
     * @return second Number
     */
    int getSecondNumber();

}
