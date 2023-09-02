package com.example.mathapp.frontend;

import com.example.mathapp.backend.Businesslogic;

/**
 * This class represents the controller
 */
public class ControllPage {
    private ViewPage viewpage;
    private Businesslogic model;

    //TODO Pro Runde sind 20 Versuche möglich, also pro Aufgabe 1 Versuch!

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
        if(!gameIsOver(this.model.getCurrent_round())){
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
        }
    }

    private void checkEndPoints(int endpoints){
        if(endpoints < 0.5 * this.model.getMAX_GAME_POINTS()){
            //Urteil mangelhaft
            viewpage.setFeedback("mangelhaft");
        }else if(endpoints < 0.6 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.5 * this.model.getMAX_GAME_POINTS()){
            //Urteil ausreichend
            viewpage.setFeedback("ausreichend");
        }else if(endpoints < 0.75 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.6 * this.model.getMAX_GAME_POINTS()){
            //Urteil befriedigend
            viewpage.setFeedback("befriedigend");
        }else if(endpoints < 0.9 * this.model.getMAX_GAME_POINTS() && endpoints >= 0.75 * this.model.getMAX_GAME_POINTS()){
            //Urteil gut
            viewpage.setFeedback("gut");
        }else if(endpoints >= 0.9 * this.model.getMAX_GAME_POINTS() && endpoints < this.model.getMAX_GAME_POINTS()){
            //Urteil sehr gut
            viewpage.setFeedback("sehr gut");
        }else if(endpoints == this.model.getMAX_GAME_POINTS()){
            viewpage.setFeedback("hervorragend");
            viewpage.goToGratulation();
        }
    }

    /**
     * pints always the currently Stand of points.
     */
    public void giveNewPointStand(){
        viewpage.showcurrentlyPoints(model.getCnt());
    }

    //TODO prüft, ob die Gesamtversuche bereits erreicht wurde
    private boolean gameIsOver(int numberOfTrying){
        return numberOfTrying == this.model.getMAX_ROUNDS_OF_GAME();
    }

}
