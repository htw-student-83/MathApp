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
    private static final String TABLE_NAME = "people";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    private static final String COLUMN_LASTNAME = "LASTNAME";
    private static final String COLUMN_MAIL = "MAIL";
    private static final String COLUMN_PIN = "PIN";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_LASTNAME + " TEXT, " +
                COLUMN_PIN + " INTEGER, " +
                COLUMN_MAIL + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    void addNewPerson(String firstname, String lastname, String mail, int pin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_FIRSTNAME, firstname);
        value.put(COLUMN_LASTNAME, lastname);
        value.put(COLUMN_MAIL, mail);
        value.put(COLUMN_PIN, pin);
        long result = db.insert(TABLE_NAME,null, value);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "New Person is added into the DB", Toast.LENGTH_LONG).show();
        }
    }
}
