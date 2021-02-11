package com.example.notes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    public static final  String NOTE_INFO = "com.example.notes.NOTE_INFO";
    private NoteInfo mNote;
    private boolean mIsNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create Spinner and Edit Text Fields
        Spinner spinnerCourses = findViewById(R.id.spinner);
        EditText titleTextField = findViewById(R.id.text_note_title);
        EditText noteTextField = findViewById(R.id.text_note_text);

        //Get List of Courses from Data Manager
        List<CourseInfo> courses = DataManager.getInstance().getCourses();

        //Create Adapter for Spinner
        ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set Adapter for Spinner
        spinnerCourses.setAdapter(adapterCourses);

        //Get Intent Extra into Field
        readStartingIntentExtras();

        //Place Extra into Fields
        if(!mIsNewNote) {
            displayNote(spinnerCourses, titleTextField, noteTextField);
        }

    }

    private void displayNote(Spinner spinnerCourses, EditText titleTextField, EditText noteTextField) {
        //Set Spinner Content
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(mNote.getCourse());
        spinnerCourses.setSelection(courseIndex);

        //Set Text Field Content
        titleTextField.setText(mNote.getTitle());
        noteTextField.setText(mNote.getText());
    }

    private void readStartingIntentExtras() {
        Intent intent = getIntent();
        mNote = intent.getParcelableExtra(NOTE_INFO);

        //Check if No Extra
        mIsNewNote = mNote == null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}