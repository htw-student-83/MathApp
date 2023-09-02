package com.example.mathapp.vertrag;

/**
 * This interface represents the logic of the database
 */
public interface DB_Logic {

    /**
     * to register a new person into the db.
     * @param pin the personally Key
     * @param firstname Firstname
     * @param lastname Lastname
     * @param mobilenumber Mobilenumber
     * @return true, if the person is in the databse otherwise false
     */
    boolean registerMembern(String pin, String firstname, String lastname, String mobilenumber);

    /**
     * check the input pin in the DB
     * @param inputPin user input
     * @return true, if the pin is saved otherwise false
     */
    boolean login(String inputPin);


    /**
     * read the saved pin from the db
     * @param inputNumber user input number
     * @return true, if the pin is saved is ready for the transport otherwise false
     */
    String sendKnownPin(String inputNumber);

    /**
     * check the input number in the databse
     * @param inputNumber user input number
     * @return true, if the pin is saved is ready for the transport otherwise false
     */
    boolean checkMobileNumber(String inputNumber);

}
