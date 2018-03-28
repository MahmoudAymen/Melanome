package com.example.aymen.projet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by aymen on 30/04/2017.
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper {
    public static final int Base_Version=1;
    public static final String Base_Name="KNN";
    public static final String Table_Name="table_Knn";
    public static final String Colone1="id";
    public static final String Colone2="Image";
    public static final String Colone3="valeur1";
    public static final String Colone4="valeur2";
    public static final String Colone5="valeur3";
    public static final String Colone6="valeur4";
    public static final String Colone7="valeur5";
    public static final String Colone8="valeur6";
    public static final String Colone9="valeur7";
    public static final String Colone10="Result";
    String Req="create table table_Knn(id integer primary key ,Image blob ,valeur1 real,valeur2 real ,valeur3 real ,valeur4  real ,valeur5  real ,valeur6  real ,valeur7 real ,Result integer )";
    public DataBaseOpenHelper(Context context) {

        super(context, Base_Name, null, Base_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DataBaseOpenHelper", "Base create");
        db.execSQL(Req);
        Log.d("DataBaseOpenHelper", "Base create 1");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DataBaseOpenHelper","Base recree");
        db.execSQL("drop table if exists table_Knn");
        onCreate(db);
        Log.d("DataBaseOpenHelper", "Base recree1");

    }
}
