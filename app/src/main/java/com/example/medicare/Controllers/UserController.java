package com.example.medicare.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.medicare.Helpers.DbHelper;
import com.example.medicare.Models.UserModel;

public class UserController extends DbHelper {

    private static final String TABLE_NAME = "user";
    Context context;

    public UserController(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long addUser(UserModel user) {
        long id = 0;
        try {
            DbHelper DbHelper = new DbHelper(context);
            SQLiteDatabase db = DbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("email", user.email);
            values.put("password", user.password);
            values.put("name", user.name);
            values.put("last_name", user.lastName);
            values.put("address", user.address);
            values.put("gender", user.gender);
            values.put("birth_date", (user.birthDate != null) ? user.birthDate : "");
            values.put("role", user.role);

            id = db.insert(TABLE_NAME, null, values);
            user.id = id;

            db.close();
        } catch (Exception ex) {
            System.out.println("Error => " + ex);
        }
        return id;
    }

    public UserModel getUserByEmailAndPassword(String email, String password) {
        UserModel user = null;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String[] projection = {
                    "id",
                    "email",
                    "password",
                    "name",
                    "last_name",
                    "address",
                    "gender",
                    "birth_date",
                    "role"
            };

            String selection = "email = ? AND password = ?";
            String[] selectionArgs = {email, password};

            Cursor cursor = db.query("user", projection, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex("id");
                int emailIndex = cursor.getColumnIndex("email");
                int passwordIndex = cursor.getColumnIndex("password");
                int nameIndex = cursor.getColumnIndex("name");
                int lastNameIndex = cursor.getColumnIndex("last_name");
                int genderIndex = cursor.getColumnIndex("gender");
                int addressIndex = cursor.getColumnIndex("address");
                int birthDateIndex = cursor.getColumnIndex("birth_date");
                int roleIndex = cursor.getColumnIndex("role");

                int id = cursor.getInt(idIndex);
                String userEmail = cursor.getString(emailIndex);
                String userPassword = cursor.getString(passwordIndex);
                String name = cursor.getString(nameIndex);
                String lastName = cursor.getString(lastNameIndex);
                String gender = cursor.getString(genderIndex);
                String address = cursor.getString(addressIndex);
                String birthDate = cursor.getString(birthDateIndex);
                String role = cursor.getString(roleIndex);

                user = new UserModel(id, userEmail, userPassword, name, lastName, address, gender, birthDate, role);
            }

            cursor.close();
            db.close();
        } catch (Exception ex) {
            System.out.println("Error => " + ex);
        }

        return user;
    }

    public boolean updateUser(UserModel user) {
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("email", user.email);
            values.put("password", user.password);
            values.put("name", user.name);
            values.put("last_name", user.lastName);
            values.put("address", user.address);
            values.put("gender", user.gender);
            values.put("birth_date", (user.birthDate != null) ? user.birthDate : "");
            values.put("role", user.role);

            String selection = "id = ?";
            String[] selectionArgs = {String.valueOf(user.id)};

            int rowsAffected = db.update(TABLE_NAME, values, selection, selectionArgs);

            db.close();

            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println("Error => " + ex);
        }

        return false;
    }

    public boolean deleteUserById(long userId) {
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String selection = "id = ?";
            String[] selectionArgs = {String.valueOf(userId)};

            int rowsAffected = db.delete(TABLE_NAME, selection, selectionArgs);

            db.close();

            return rowsAffected > 0;
        } catch (Exception ex) {
            System.out.println("Error => " + ex);
        }

        return false;
    }
}
