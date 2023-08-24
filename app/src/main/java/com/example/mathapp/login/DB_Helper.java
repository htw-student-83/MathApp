package com.example.mathapp.login;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MemberOfTheMathApp";

    //Die Datenbanktabelle definieren
    private static final int DATABSE_VERSION = 1;
    private static final String TABLENAME = "Members";
    private static final String COLUMN_PIN = "pin";
    private static final String COLUMN_FIRSTNAME = "fn";
    private static final String COLUMN_LASTNAME = "ln";
    private static final String COLUMN_MOBILENUMBER = "mn";

    /**
     * A new instance of the db
     * @param context
     */
    public DB_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        this.context = context;
    }

    /**
     * create a new table of the db
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLENAME +
                        "(" + COLUMN_PIN + " TEXT," +
                            COLUMN_FIRSTNAME + " TEXT, " +
                            COLUMN_LASTNAME +  " TEXT, " +
                            COLUMN_MOBILENUMBER + " TEXT);";

        db.execSQL(query);
    }

    /**
     * change the DB
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }

    /**
     * register a new member
     * @param pin the key to use the app
     * @param firstname Firstname
     * @param lastname Lastname
     * @param mobilenumber Mobile number
     * @return true, if the registration was sucessfully otherwise false
     */
    public boolean registerMembern(String pin, String firstname, String lastname, String mobilenumber){
        //Ermöglicht Daten in die DB zu schreiben
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_PIN, pin);
        value.put(COLUMN_FIRSTNAME, firstname);
        value.put(COLUMN_LASTNAME, lastname);
        value.put(COLUMN_MOBILENUMBER, mobilenumber);
        long result = db.insert(TABLENAME,null, value);
        if(result == -1){
            Toast.makeText(this.context, "something was failed.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this.context, "New member added successfully.", Toast.LENGTH_LONG).show();
        }
        return true;
    }


    /**
     * check the input pin in the DB
     * @param inputPin user input
     * @return true, if the pin is saved otherwise false
     */
    public boolean login(String inputPin){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT * FROM " + TABLENAME + " where " + COLUMN_PIN + "=?";
        Cursor cursor =  db.rawQuery(sql, new String[]{inputPin});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    /**
     * read the saved pin from the db
     * @param inputNumber user input number
     * @return true, if the pin is saved is ready for the transport otherwise false
     */
    public String sendKnownPin(String inputNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT " + COLUMN_PIN + " FROM " + TABLENAME + " where " + COLUMN_MOBILENUMBER + "=?";
        Cursor cursor =  db.rawQuery(sql, new String[]{inputNumber});
        String savedPIN = null;
        if(cursor.moveToFirst()){
            savedPIN = cursor.getString(0);
        }
        cursor.close();
        return savedPIN;
    }


    /**
     * check the input number in the databse
     * @param inputNumber user input number
     * @return true, if the pin is saved is ready for the transport otherwise false
     */
    public boolean checkMobileNumber(String inputNumber){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT " + COLUMN_MOBILENUMBER + " FROM " + TABLENAME + " where " + COLUMN_MOBILENUMBER + "=?";
        Cursor cursor =  db.rawQuery(sql, new String[]{inputNumber});
        if(cursor.moveToFirst()){
            return true;
        }
        cursor.close();
        return false;
    }

}
