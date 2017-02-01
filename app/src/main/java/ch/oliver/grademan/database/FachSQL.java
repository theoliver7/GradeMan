package ch.oliver.grademan.database;

import java.util.ArrayList;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class FachSQL {
    public  static final String TABLE_FACH = "Fach";
    public static final String KEY_ID = "Fach_id";
    public static final String FACH_NAME="Fachname";
    public static final String NOTEN_ID= "Noten_ID";
    public static final String SEMESTER="Semester";
    public static final String SCHNITT="Schnitt";
    public static final String ABKRZ="Abkuerzung";

    public static String getSqlQueryForCreateTableFach() {
        return "CREATE TABLE "
                + TABLE_FACH + "(" + KEY_ID + " INTEGER PRIMARY KEY," + FACH_NAME
                + " TEXT," + SEMESTER + " INTEGER,"+ SCHNITT + " FLOAT,"+ ABKRZ + " TEXT," + NOTEN_ID + " INTEGER)";
    }

    public static String getSqlQuerySelectForeignKey(String liste) {
        return "SELECT " + KEY_ID + " FROM " + TABLE_FACH + " WHERE " + KEY_ID + " = '" + liste + "';";
    }
}
