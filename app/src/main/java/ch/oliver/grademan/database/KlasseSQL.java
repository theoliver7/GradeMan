package ch.oliver.grademan.database;

import java.util.ArrayList;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class KlasseSQL {
    public static final String TABLE_KLASSE= "Klasse";
    public static final String KEY_ID = "Klasse_id";
    public static final String KLASSE_NAME="Name";
    public static final String GESAMTSCHNITT= "Gesamtschnitt";
    public static final String FACH_ID="Faecherliste_ID";

    public static String getSqlQueryForCreateTableKlasse() {
        return "CREATE TABLE "
                + TABLE_KLASSE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KLASSE_NAME
                + " TEXT," + GESAMTSCHNITT + " FLOAT," + FACH_ID + " INTEGER)";
    }


}
