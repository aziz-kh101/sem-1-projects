package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.project.entity.Note;
import com.example.project.utils.DatabaseHelper;
import com.example.project.utils.Helper;
import com.example.project.utils.NoteDBHelper;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNote extends AppCompatActivity {
    EditText title;
    EditText description;

    NoteDBHelper noteDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        MaterialToolbar bar = findViewById(R.id.topAppBar);
        bar.setNavigationOnClickListener(view -> {
            finish();
        });

        noteDBHelper = new NoteDBHelper(AddNote.this);

        title = findViewById(R.id.add_note_title);
        description = findViewById(R.id.add_note_description);
    }

    public void add(View v){
        String titleText = title.getText().toString().trim();
        String descriptionText = description.getText().toString().trim();
        if(titleText.isEmpty() || descriptionText.isEmpty()){
            Helper.makeLongToast(AddNote.this, "you need to complete information");
            return;
        }

        try {
            noteDBHelper.addNote(new Note(titleText, descriptionText));
            Helper.makeLongToast(AddNote.this, "note added successfully");
            title.setText("");
            description.setText("");
        }catch (android.database.SQLException e){
            Helper.makeLongToast(AddNote.this, "can't add note , please try again later");
        }

    }
}