package com.example.trytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private ListView lvNotes;
private List<Notes> dsNotes;
private NotesDAO notesDAO;
private Databasehelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Notes");
        lvNotes = findViewById(R.id.lvNotes);
        notesDAO = new NotesDAO(this);
        databasehelper = new Databasehelper(this);
        dsNotes = notesDAO.getAllNotes();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dsNotes);
        adapter.notifyDataSetChanged();
        lvNotes.setAdapter(adapter);
        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notes notes = dsNotes.get(position);
                View dialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_notes_detail, null, false);
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                TextView edTitle = dialog.findViewById(R.id.tdTitle);
                TextView edContent = dialog.findViewById(R.id.tdContent);
                TextView edTime = dialog.findViewById(R.id.tdTime);
                 edTitle.setText(notes.getTitle());
                edTime.setText(notes.getTime());
                edContent.setText(notes.getContent());
                builder.setView(dialog);
                builder.show();
            }
        });
    lvNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Notes notes = dsNotes.get(position);
        notesDAO.deleteNote(notes.getTitle());
        Toast.makeText(getApplicationContext(), "Delete successfuly", Toast.LENGTH_SHORT).show();
        dsNotes.clear();
        dsNotes.addAll(notesDAO.getAllNotes());
        adapter.notifyDataSetChanged();
        return false;
    }
});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    Intent intent;
        switch (item.getItemId()){
            case R.id.menu_add_note:
            intent = new Intent(this, AddNotesActivity.class);
            startActivity(intent);
                this.setTitle("Add a note");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
