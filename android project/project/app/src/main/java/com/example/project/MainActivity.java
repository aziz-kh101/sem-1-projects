package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.Toast;

import com.example.project.adapter.NoteAdapter;
import com.example.project.decorator.VerticalSpaceItemDecoration;
import com.example.project.entity.Note;
import com.example.project.utils.NoteDBHelper;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private final int VERTICAL_SPACE_HEIGHT = 20;

    NoteDBHelper noteDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        recyclerView = findViewById(R.id.note_list);

        noteDBHelper = new NoteDBHelper(MainActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_SPACE_HEIGHT));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Note> list = noteDBHelper.getAllNotes();
        recyclerView.setAdapter(new NoteAdapter(MainActivity.this, list));
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((NoteAdapter)recyclerView.getAdapter()).getActionMode().ifPresent(ActionMode::finish);
    }

    public void addNote(View view){
        startActivity(new Intent(MainActivity.this.getApplicationContext(), AddNote.class));
    }

}