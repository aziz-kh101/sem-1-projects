package com.example.project.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NOTES_TABLE = "notes";
    public static final String USERS_TABLE = "users";
    public static final String DATABASE_NAME = "notes_app";
    static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase getDatabase(){
        return getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+NOTES_TABLE+" (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, description TEXT NOT NULL, created_at TEXT)");
        db.execSQL("CREATE TABLE "+USERS_TABLE+" (username TEXT PRIMARY KEY, password TEXT NOT NULL)");

        db.execSQL("INSERT INTO "+USERS_TABLE+ " VALUES('aziz', '123456')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+NOTES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+USERS_TABLE);

        onCreate(db);
    }
}
