package com.example.mathapp.frontend;

import com.example.mathapp.backend.Businesslogic;

/**
 * This class represents the controller
 */
public class ControllPage {
    private ViewPage viewpage;
    private Businesslogic model;

    /**
     * Initial new object from the View and the model
     * @param view Frontend
     */
    public ControllPage(ViewPage view){
        this.viewpage = view;
        this.model = new Businesslogic();
    }

    /**
     * Change the currently points.
     */
    public void changePoints (){
        int currentPoint = viewpage.getViewPoint();
        if(currentPoint < model.getMAX_GAME_POINTS()){
            model.setCnt(currentPoint);
        }else {
            viewpage.goToGratulation();
        }
    }

    /**
     * calculate the product of two nature numbers
     */
    protected void presentProperNumbers(){
        int number1 = model.getFirstNumber();
        int number2 = model.getSecondNumber();
        model.setProduct(number1, number2);
        viewpage.showNewExercise(number1, number2);
    }

    /**
     * evaluate tehe result of the user with the product
     * @throws InterruptedException, if something is wrong
     */
    public void evaluation() throws InterruptedException {
        if(gameIsOver(this.model.getCurrent_round())){
            if(model.getProduct() != viewpage.getInputFromUser()){
                viewpage.showFaildToast();
                this.model.setCurrent_round();
            }else{
                viewpage.showSuccessToast();
                changePoints();
                giveNewPointStand();
                this.model.setCurrent_round();
                presentProperNumbers();
            }
        }else{
            checkEndPoints(this.model.getCnt());
            //TODO don'st start the next round so fast.
            nextRound();
        }
    }

    private void checkEndPoints(int endpoints){
        if(endpoints < 0.5 * this.model.getMAX_GAME_POINTS()){
            viewpage.setFeedback("5.0");
        }else if(endpoints < 0.55 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.5 * this.model.getMAX_GAME_POINTS()){
            viewpage.setFeedback("4.0");
        }else if(endpoints < 0.6 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.55 * this.model.getMAX_GAME_POINTS()){
            viewpage.setFeedback("3.7");
        }else if(endpoints < 0.65 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.6 * this.model.getMAX_GAME_POINTS()){
            viewpage.setFeedback("3.3");
        }else if(endpoints < 0.7 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.65 * this.model.getMAX_GAME_POINTS()) {
            viewpage.setFeedback("3.0");
        }else if(endpoints < 0.75 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.7 * this.model.getMAX_GAME_POINTS()) {
            viewpage.setFeedback("2.7");
        }else if(endpoints < 0.8 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.75 * this.model.getMAX_GAME_POINTS()) {
            viewpage.setFeedback("2.3");
        }else if(endpoints < 0.85 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.8 * this.model.getMAX_GAME_POINTS()) {
            viewpage.setFeedback("2.0");
        }else if(endpoints < 0.9 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.85 * this.model.getMAX_GAME_POINTS()) {
            viewpage.setFeedback("1.7");
        }else if(endpoints < 0.95 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.9 * this.model.getMAX_GAME_POINTS()) {
            viewpage.setFeedback("1.3");
        }else if(endpoints >= 0.95 * this.model.getMAX_GAME_POINTS() && endpoints < this.model.getMAX_GAME_POINTS()) {
            viewpage.setFeedback("1.0");
        }else{
            viewpage.goToGratulation();
        }
    }

    /**
     * pints always the currently Stand of points.
     */
    public void giveNewPointStand(){
        viewpage.showcurrentlyPoints(model.getCnt());
    }

    /**
     * prüft, ob die Gesamtversuche bereits erreicht wurde
     * @param numberOfTrying ...?
     * @return true, if the game is over otherwise false
     */
    private boolean gameIsOver(int numberOfTrying){
        return numberOfTrying < this.model.getMAX_ROUNDS_OF_GAME() - 1;
    }

    private void nextRound(){
        viewpage.startNewExerciseAgain();
    }

}
