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
        model.setCnt(currentPoint);
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
        if(model.getProduct() != viewpage.getInputFromUser()){
            viewpage.showFaildToast();
        }else{
            viewpage.showSuccessToast();
            changePoints();
            if(model.getCnt() == model.getMAX_GAME_POINTS()){
                viewpage.goToGratulation();
            }else{
                changePoints();
                giveNewPointStand();
                presentProperNumbers();
            }
        }
    }

    /**
     * pints always the currently Stand of points.
     */
    public void giveNewPointStand(){
        viewpage.showcurrentlyPoints(model.getCnt());
    }

}
