package ch.oliver.grademan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Klasse;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class FachDAO extends DatabaseDAO {
    public FachDAO(Context context) {
        super(context);
    }

    public long facherstellen(Fach fach){
        open();
        ContentValues values = new ContentValues();
        values.put(FachSQL.FACH_NAME, fach.getName());
        values.put(FachSQL.ABKRZ, fach.getAbkrz());
        values.put(FachSQL.SCHNITT, fach.getSchnitt());
        values.put(FachSQL.SEMESTER,fach.getSemester());
        values.put(FachSQL.KLASSE_ID, fach.getKlasse_id());
        long klasse_id = db.insert(FachSQL.TABLE_FACH,null,values);
        return klasse_id;
    }

    public ArrayList<Fach> getAllFacherfromClass(Klasse klasse) {
        ArrayList<Fach> facher = new ArrayList<Fach>();
        Fach fach;
        open();
        Cursor cursor = db.query(FachSQL.TABLE_FACH, new String[]{FachSQL.KEY_ID, FachSQL.SEMESTER, FachSQL.SCHNITT, FachSQL.KLASSE_ID, FachSQL.ABKRZ, FachSQL.FACH_NAME}, FachSQL.KLASSE_ID + "=?", new String[]{Integer.toBinaryString(klasse.getId_klasse())}, null, null, null, null);

        while (cursor.moveToNext()) {
            fach = new Fach();
            fach.setId_fach(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FachSQL.KEY_ID))));
            fach.setSchnitt(Float.parseFloat(cursor.getString(cursor.getColumnIndex(FachSQL.SCHNITT))));
            fach.setSemester(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FachSQL.SEMESTER))));
            fach.setName(cursor.getString(cursor.getColumnIndex(FachSQL.FACH_NAME)));
            fach.setAbkrz(cursor.getString(cursor.getColumnIndex(FachSQL.ABKRZ)));
            fach.setKlasse_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FachSQL.KLASSE_ID))));
            facher.add(fach);
        }
        close();
        return facher;
    }


    public List<Fach> getAllFaecher(){
        List<Fach> faecher= new ArrayList<Fach>();
        Fach fach;
        open();

        Cursor cursor = db.query(FachSQL.TABLE_FACH, new String[]{FachSQL.ABKRZ, FachSQL.FACH_NAME, FachSQL.KEY_ID, FachSQL.KLASSE_ID, FachSQL.SCHNITT, FachSQL.SEMESTER}, null, null, null, null, null, null);


        while(cursor.moveToNext()){
            fach = new Fach();
            fach.setName(cursor.getString(cursor.getColumnIndex(FachSQL.FACH_NAME)));
            fach.setAbkrz(cursor.getString(cursor.getColumnIndex(FachSQL.ABKRZ)));
            fach.setKlasse_id(Long.parseLong(cursor.getString(cursor.getColumnIndex(FachSQL.KEY_ID))));
            fach.setSchnitt(Float.parseFloat(cursor.getString(cursor.getColumnIndex(FachSQL.SCHNITT))));
            fach.setSemester(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FachSQL.SEMESTER))));
            faecher.add(fach);
        }
        close();
        return faecher;
    }
}
