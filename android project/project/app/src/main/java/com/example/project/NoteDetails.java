package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.utils.Helper;
import com.google.android.material.appbar.MaterialToolbar;

import java.time.ZonedDateTime;

public class NoteDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        MaterialToolbar bar = findViewById(R.id.topAppBar);
        bar.setNavigationOnClickListener(view -> {
            finish();
        });

        ImageView imageView = findViewById(R.id.details_image);
        TextView title = findViewById(R.id.details_title);
        TextView description = findViewById(R.id.details_description);
        TextView createdAt = findViewById(R.id.details_created_at);

        Intent intent = getIntent();

        imageView.setImageResource(R.drawable.essay);
        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        createdAt.setText("created at : "+ intent.getStringExtra("createdAt"));
    }
}