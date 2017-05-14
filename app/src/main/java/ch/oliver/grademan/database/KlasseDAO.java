package ch.oliver.grademan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ch.oliver.grademan.model.Klasse;

public class KlasseDAO extends DatabaseDAO{

    public KlasseDAO(Context context) {
        super(context);
    }

    public long klasseerstellen(Klasse klasse){
        open();
        ContentValues values = new ContentValues();
        values.put(KlasseSQL.KLASSE_NAME, klasse.getKlassenname());
        values.put(KlasseSQL.GESAMTSCHNITT, klasse.getGesamtschnitt());
        values.put(KlasseSQL.IS_FAVORITE_KLASSE,klasse.getIs_favorite_klasse());
        long klasse_id = db.insert(KlasseSQL.TABLE_KLASSE,null,values);
        close();
        return klasse_id;
    }
    public Klasse getFavoriteKlasse(){
        open();
        Cursor cursor =  db.rawQuery("Select * from Klasse where "+ KlasseSQL.IS_FAVORITE_KLASSE +" = "+ 1,null);
        Klasse klasse = null;
        while(cursor.moveToNext()) {
            klasse = new Klasse();
            klasse.setKlassenname(cursor.getString(cursor.getColumnIndex(KlasseSQL.KLASSE_NAME)));
            klasse.setId_klasse(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KlasseSQL.KEY_ID))));
            klasse.setGesamtschnitt(Float.parseFloat(cursor.getString(cursor.getColumnIndex(KlasseSQL.GESAMTSCHNITT))));
            klasse.setIs_favorite_klasse(Integer.parseInt(cursor.getString((cursor.getColumnIndex(KlasseSQL.IS_FAVORITE_KLASSE)))));
        }
        cursor.close();
        close();
        return klasse;
    }
    public Klasse getKlasseById(int id){
        open();
        Cursor cursor =  db.rawQuery("Select * from Klasse where "+ KlasseSQL.KEY_ID +" = "+ id,null);
        Klasse klasse = null;
        while(cursor.moveToNext()) {
            klasse = new Klasse();
            klasse.setKlassenname(cursor.getString(cursor.getColumnIndex(KlasseSQL.KLASSE_NAME)));
            klasse.setId_klasse(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KlasseSQL.KEY_ID))));
            klasse.setGesamtschnitt(Float.parseFloat(cursor.getString(cursor.getColumnIndex(KlasseSQL.GESAMTSCHNITT))));
            klasse.setIs_favorite_klasse(Integer.parseInt(cursor.getString((cursor.getColumnIndex(KlasseSQL.IS_FAVORITE_KLASSE)))));
        }
        cursor.close();
        close();
        return klasse;
    }
    public void makeFavoriteKlasse(Klasse klasse){
        open();
        ContentValues values = new ContentValues();
        values.put(KlasseSQL.IS_FAVORITE_KLASSE, klasse.getIs_favorite_klasse());
        db.execSQL("Update "+KlasseSQL.TABLE_KLASSE+" Set "+KlasseSQL.IS_FAVORITE_KLASSE+" = 0 where "+ KlasseSQL.IS_FAVORITE_KLASSE+" ="+1);
        // updating row
         db.update(KlasseSQL.TABLE_KLASSE,values, KlasseSQL.KEY_ID + " = ?",
                new String[] { String.valueOf(klasse.getId_klasse()) });
        close();
    }
    public List<Klasse> getAllKlassen(){
        List<Klasse> klassen= new ArrayList<>();
        Klasse klasse;
        open();

        Cursor cursor = db.query(KlasseSQL.TABLE_KLASSE,new String[]{KlasseSQL.KEY_ID,KlasseSQL.KLASSE_NAME,KlasseSQL.GESAMTSCHNITT,KlasseSQL.IS_FAVORITE_KLASSE},null,null,null,null,null,null);


        while(cursor.moveToNext()){
            klasse = new Klasse();
            klasse.setKlassenname(cursor.getString(cursor.getColumnIndex(KlasseSQL.KLASSE_NAME)));
            klasse.setId_klasse(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KlasseSQL.KEY_ID))));
            klasse.setGesamtschnitt(Float.parseFloat(cursor.getString(cursor.getColumnIndex(KlasseSQL.GESAMTSCHNITT))));
            klasse.setIs_favorite_klasse(Integer.parseInt(cursor.getString((cursor.getColumnIndex(KlasseSQL.IS_FAVORITE_KLASSE)))));
            klassen.add(klasse);
        }
        cursor.close();
        close();
        return klassen;
    }

}
