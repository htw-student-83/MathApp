package com.example.mathapp.login;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DB_Helper {
    private Context con;
    private SQLiteDatabase db;

    public DB_Helper(Context con){
        this.con = con;
    }

    public DB_Helper openDB(){
        Database connecotor = new Database(con);
        db = connecotor.getWritableDatabase();
        return this;
    }

    public boolean registerDaten(DB_Daten daten){
        try {
            ContentValues value = new ContentValues();
            value.put("pin", daten.getPin());
            value.put("firstname", daten.getFirstname());
            value.put("lastname", daten.getLastname());
            value.put("mobile_number", daten.getMobile_number());
            db.insert("math_user",null, value);
            return true;
        }catch (Exception e) {
            Toast.makeText(con, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
