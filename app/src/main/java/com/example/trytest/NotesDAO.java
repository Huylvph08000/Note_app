package com.example.trytest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotesDAO {
    private SQLiteDatabase sqLitedb;
    private Databasehelper dbhelper;
    private Context context;
    public NotesDAO(Context context){
        this.context = context;
        dbhelper = new Databasehelper(context);
        sqLitedb = dbhelper.getWritableDatabase();
    }
    public boolean insertNotes(Notes notes){
        try {
            ContentValues values = new ContentValues();
            values.put("Title", notes.getTitle());
            values.put("Content", notes.getContent());
            values.put("Time", notes.getTime());
            long numInsert = sqLitedb.insert("Notes", null, values);
            if (numInsert<=0)
                return false;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return  false;
        }
        return true;
    }
    public List<Notes> getAllNotes(){
        List<Notes> notesList1 = new ArrayList<>();
        String sqlSelect = "select * from Notes";
        Cursor cursor = sqLitedb.rawQuery(sqlSelect, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String Title = cursor.getString(0);
            String Content = cursor.getString(1);
            String Time = cursor.getString(2);
            Notes objNotes = new Notes(Title, Content, Time);
            notesList1.add(objNotes);
            cursor.moveToNext();
        }
        return notesList1;
    }
    public int deleteNote(String id){
        return sqLitedb.delete("Notes", "Title=?", new String[]{id});
    }
}
