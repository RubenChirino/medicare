package com.example.medicare.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "hospital_system.db";

    // === TABLES ===

    // USER
    private final String TABLE_USER_CREATION_QUERY = "CREATE TABLE user (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "email TEXT UNIQUE NOT NULL," +
            "password TEXT NOT NULL," +
            "name TEXT," +
            "last_name TEXT," +
            "address TEXT," +
            "gender TEXT," +
            "birth_date DATE," +
            "role TEXT" + ")";
    private final String TABLE_USER_DELETION_QUERY = "DROP TABLE user";

    // Turn
    private final String TABLE_TURN_CREATION_QUERY = "CREATE TABLE turn (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "id_user INTEGER REFERENCES user(id)," +
            "speciality TEXT NOT NULL," +
            "date DATE," +
            "time TEXT" + ")";

    private final String TABLE_TURN_DELETION_QUERY = "DROP TABLE turn";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATION_QUERY);
        db.execSQL("PRAGMA foreign_keys = ON;");
        db.execSQL(TABLE_TURN_CREATION_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_USER_DELETION_QUERY);
        db.execSQL(TABLE_TURN_DELETION_QUERY);
        onCreate(db);
    }
}
