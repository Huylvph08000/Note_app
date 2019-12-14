package com.example.trytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AddNotesActivity extends AppCompatActivity {
    Button btnsave;
    private NotesDAO notesDAO;
    private List<Notes> notesList;
    EditText txtTitle, txtContent, txtTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        notesDAO = new NotesDAO(this);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        txtTime = findViewById(R.id.txtTime);
        btnsave = findViewById(R.id.btnSave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = txtTitle.getText().toString();
                String Content = txtContent.getText().toString();
                String Time = txtTime.getText().toString();
                if(TextUtils.isEmpty(Title)) {
                    txtTitle.setError("The item title cannot be empty");
                    return;
                }else if (TextUtils.isEmpty(Content)) {
                    txtContent.setError("The item content cannot be empty");
                    return;
                }
                else if (TextUtils.isEmpty(Time)){
                    txtTime.setError("The item time cannot be empty");
                }
                Notes objNotes = new Notes(Title, Content, Time);
                boolean isSuccess = notesDAO.insertNotes(objNotes);
                if (isSuccess){
                    Toast.makeText(AddNotesActivity.this, "Insert successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddNotesActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(AddNotesActivity.this, "Insert failed", Toast.LENGTH_SHORT).show();
                }








            }
        });
    }



}
