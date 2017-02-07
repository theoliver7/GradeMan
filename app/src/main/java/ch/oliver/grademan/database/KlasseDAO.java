package ch.oliver.grademan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ch.oliver.grademan.model.Klasse;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class KlasseDAO extends DatabaseDAO{

    public KlasseDAO(Context context) {
        super(context);
    }

    public long klasseerstellen(Klasse klasse){
        open();
        ContentValues values = new ContentValues();
        values.put(KlasseSQL.KLASSE_NAME, klasse.getKlassenname());
        values.put(KlasseSQL.GESAMTSCHNITT, klasse.getGesamtschnitt());
        values.put(KlasseSQL.FACH_ID,klasse.getFachliste());
        long klasse_id = db.insert(KlasseSQL.TABLE_KLASSE,null,values);
        return klasse_id;
    }
    public List<Klasse> getAllKlassen(){
        List<Klasse> klassen= new ArrayList<Klasse>();
        Klasse klasse;
        open();

        Cursor cursor = db.query(KlasseSQL.TABLE_KLASSE,new String[]{KlasseSQL.KEY_ID,KlasseSQL.KLASSE_NAME,KlasseSQL.GESAMTSCHNITT},null,null,null,null,null,null);


        while(cursor.moveToNext()){
            klasse = new Klasse();
            klasse.setKlassenname(cursor.getString(cursor.getColumnIndex(KlasseSQL.KLASSE_NAME)));
            klasse.setId_klasse(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KlasseSQL.KEY_ID))));
            klasse.setGesamtschnitt(Float.parseFloat(cursor.getString(cursor.getColumnIndex(KlasseSQL.GESAMTSCHNITT))));
            klassen.add(klasse);
        }
        close();
        return klassen;
    }
}
