package ch.oliver.grademan.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotenDB.db";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + FachSQL.TABLE_FACH);
        db.execSQL("DROP TABLE IF EXISTS " + NoteSQL.TABLE_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + KlasseSQL.TABLE_KLASSE);
        db.execSQL(KlasseSQL.getSqlQueryForCreateTableKlasse());
        db.execSQL(FachSQL.getSqlQueryForCreateTableFach());
        db.execSQL(NoteSQL.getSqlQueryForCreateTableNote());

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FachSQL.TABLE_FACH);
        db.execSQL("DROP TABLE IF EXISTS " + NoteSQL.TABLE_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + KlasseSQL.TABLE_KLASSE);
        onCreate(db);
    }
}
