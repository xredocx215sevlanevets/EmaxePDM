package com.marlonheraclito.emaxepdm.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Exame.db";
    public static final String TABLE_NAME = "login_table";
    public static final String TABLE_NAME_ALUNO = "aluno_table";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT,PASSWORD TEXT)");
        db.execSQL("create table " + TABLE_NAME_ALUNO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, NUMERO TEXT, " +
                "PC TEXT, PRESENCIA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ALUNO);
            onCreate(db);

    }
}
