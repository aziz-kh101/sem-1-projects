package com.example.project.utils;

import android.content.Context;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class Helper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

    public static String formatDate(Temporal t){
        return  formatter.format(t);
    }

    public static void makeLongToast(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }
}
