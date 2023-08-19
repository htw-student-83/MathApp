package com.example.mathapp.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    // - the place to define the database

    private Context context;
    private static final String DATABASE_NAME = "management.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "math_user";
    private static final String COLUMN_PIN = "PIN";
    private static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    private static final String COLUMN_LASTNAME = "LASTNAME";
    private static final String COLUMN_MOBIL_NUMBER = "NUMBER";


    public Database(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cleanerInfo(COLUMN_PIN INTEGER UNIQUE, " +
                                            "COLUMN_FIRSTNAME VARSCHAR, " +
                                            "COLUMN_LASTNAME VARSCHAR," +
                                            "COLUMN_MOBILENUMBER VARSCHAR)");
        /*String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_PIN + "INTEGER, " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_LASTNAME + " TEXT);";
        db.execSQL(query);
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    boolean addNewPerson(int pin, String firstname, String lastname, String mobilnumber){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(COLUMN_PIN, pin);
            value.put(COLUMN_FIRSTNAME, firstname);
            value.put(COLUMN_LASTNAME, lastname);
            value.put(COLUMN_MOBIL_NUMBER, mobilnumber);
            db.insert(TABLE_NAME,null, value);
            Toast.makeText(context, "Registration successfully.", Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    int getPersonData(String phonenumber){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();
        //TODO Daten aus der bestehenden DB auslesen
        long result = db.insert(TABLE_NAME,null, value);
        if(result == -1){
            Toast.makeText(context, "Mobile number is unknown", Toast.LENGTH_LONG).show();
        }
        return 1001;
    }
}
