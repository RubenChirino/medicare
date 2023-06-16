package com.example.medicare.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.medicare.Helpers.DbHelper;
import com.example.medicare.Models.TurnModel;

import java.util.ArrayList;

public class TurnController extends DbHelper {

    private static final String TABLE_NAME = "turn";
    Context context;

    public TurnController(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long addTurn(TurnModel turn, long userId) {
        long id = 0;
        try {
            DbHelper DbHelper = new DbHelper(context);
            SQLiteDatabase db = DbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("id_user", userId);
            values.put("speciality", turn.medicalSpeciality);
            values.put("date", turn.date);
            values.put("time", turn.time);

            id = db.insert(TABLE_NAME, null, values);
            turn.id = id;

            db.close();
        } catch (Exception ex) {
            System.out.println("Error => " + ex);
        }
        return id;
    }

    public ArrayList<TurnModel> getAllTurnByUser(long userId) {
        ArrayList<TurnModel> turns = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id_user = " + userId + ";";
        DbHelper DbHelper = new DbHelper(context);
        SQLiteDatabase db = DbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                TurnModel turn = new TurnModel(cursor.getLong(0), cursor.getLong(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                turns.add(turn);
            }
        }
        return turns;
    }

    public boolean deleteTurn(long turnId) {
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            int rowsAffected = db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(turnId)});
            db.close();

            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println("Error => " + ex);
            return false;
        }
    }

}
