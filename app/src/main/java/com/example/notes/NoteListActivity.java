package com.example.notes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        //Method to show List View
        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        //Create List View
        final ListView notesList = findViewById(R.id.list_notes);

        //Get Notes list from DataManager
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        //Setup Array Adapter for List View using notes
        ArrayAdapter<NoteInfo> notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,notes);
        notesList.setAdapter(notesAdapter);

        //Create Onclick listener for List View Item
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long l) {

                //Create Intent for each click
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
                NoteInfo note = (NoteInfo) notesList.getItemAtPosition(itemPosition);
                intent.putExtra(NoteActivity.NOTE_INFO, note);
                startActivity(intent);

            }
        });
    }
}