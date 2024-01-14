package com.example.project.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.entity.User;

import java.util.Optional;

public class UserDBHelper {
    private Context context;
    public UserDBHelper(Context context){
        this.context = context;
    }

    public Optional<User> getByUsername(String username){
        try (DatabaseHelper databaseHelper = new DatabaseHelper(context)){
            Cursor cursor = databaseHelper.
                    getDatabase()
                    .query(DatabaseHelper.USERS_TABLE, new String[]{"username","password"},"username = ?", new String[]{username},null,null,null,null);
            if(cursor != null && cursor.moveToFirst()){
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                return Optional.of(user);
            }
            return Optional.empty();
        }
    }
}
