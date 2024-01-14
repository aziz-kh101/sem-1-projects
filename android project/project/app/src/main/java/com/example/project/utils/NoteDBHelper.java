package com.example.project.utils;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.example.project.entity.Note;
import com.example.project.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NoteDBHelper {
    private Context context;
    public NoteDBHelper(Context context){
        this.context = context;
    }

    public List<Note> getAllNotes(){
        List<Note> list = new ArrayList<>();
        try(DatabaseHelper databaseHelper = new DatabaseHelper(context)) {
            Cursor cursor = databaseHelper.
                    getDatabase()
                    .query(DatabaseHelper.NOTES_TABLE, new String[]{"id","title","description","created_at"},null,null,null,null,null,null);
            if(cursor!= null){
                while (cursor.moveToNext()){
                    Note note = new Note();
                    note.setId(cursor.getInt(0));
                    note.setTitle(cursor.getString(1));
                    note.setDescription(cursor.getString(2));
                    note.setCreatedAt(cursor.getString(3));
                    list.add(note);
                }
            }
        }
        return list;
    }

    public void addNote(Note note){
        try(DatabaseHelper databaseHelper = new DatabaseHelper(context)) {
            databaseHelper.getDatabase().execSQL("insert into "+DatabaseHelper.NOTES_TABLE+" values(null, ?, ?, ?)", new Object[]{note.getTitle(),note.getDescription(),note.getCreatedAt()});
        }
    }

    public void deleteAll(Collection<Note> list){
        try(DatabaseHelper databaseHelper = new DatabaseHelper(context))  {
            List<Integer> listIds = list.stream().map(Note::getId).collect(Collectors.toList());
            System.out.println(TextUtils.join(",",listIds));
            databaseHelper.getDatabase().execSQL("DELETE FROM "+DatabaseHelper.NOTES_TABLE+" WHERE id in ("+TextUtils.join(",",listIds)+")");
        }
    }
}
