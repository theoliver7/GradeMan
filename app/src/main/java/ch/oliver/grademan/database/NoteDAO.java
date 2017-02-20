package ch.oliver.grademan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Note;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class NoteDAO extends DatabaseDAO {
    public NoteDAO(Context context) {
        super(context);
    }

    public long noteerstellen(Note note){
        open();
        ContentValues values = new ContentValues();
        values.put(NoteSQL.THEMA,note.getThema());
        values.put(NoteSQL.GEWICHTUNG,note.getGewichtung());
        values.put(NoteSQL.NOTE,note.getNote());
        System.out.println(note.getFach_id());
        values.put(NoteSQL.FACH_ID,note.getFach_id());

        long note_id = db.insert(NoteSQL.NOTE,null,values);
        return note_id;
    }

    public ArrayList<Note> getAllNotefromFach(Fach fach) {
        ArrayList<Note> noten = new ArrayList<Note>();
        Note note;
        open();
        System.out.println(fach.getId_fach());
        Cursor cursor = db.query(NoteSQL.TABLE_NOTE, new String[]{NoteSQL.KEY_ID, NoteSQL.GEWICHTUNG, NoteSQL.NOTE, NoteSQL.THEMA, NoteSQL.FACH_ID}, NoteSQL.FACH_ID + "= ?", new String[]{Integer.toBinaryString(fach.getId_fach())}, null, null, null, null);
        while (cursor.moveToNext()) {
            System.out.println("+");
            note= new Note();
            note.setId_note(Integer.parseInt(cursor.getString(cursor.getColumnIndex(NoteSQL.KEY_ID))));
            note.setNote(Float.parseFloat(cursor.getString(cursor.getColumnIndex(NoteSQL.NOTE))));
            note.setFach_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(NoteSQL.FACH_ID))));
            note.setThema(cursor.getString(cursor.getColumnIndex(NoteSQL.KEY_ID)));
            note.setGewichtung(Integer.parseInt(cursor.getString(cursor.getColumnIndex(NoteSQL.KEY_ID))));
            noten.add(note);
        }
        close();

        return noten;
    }
}

