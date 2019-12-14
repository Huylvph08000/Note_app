package com.example.trytest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {
    public Databasehelper(@Nullable Context context) {
        super(context, "dbGhiChu", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTableNotes = "create table Notes(Title text primary key, Content text, Time text)";
    db.execSQL(createTableNotes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists Notes");
    onCreate(db);
    }
}
