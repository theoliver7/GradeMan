package ch.oliver.grademan.database;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class FachSQL {
    public  static final String TABLE_FACH = "Fach";
    public static final String KEY_ID = "Fach_id";
    public static final String FACH_NAME="Fachname";
    public static final String SEMESTER="Semester";
    public static final String SCHNITT="Schnitt";
    public static final String ABKRZ="Abkuerzung";
    public static final String KLASSE_ID = "Klasse_ID";


    public static String getSqlQueryForCreateTableFach() {
        return "CREATE TABLE "
                + TABLE_FACH + "(" + KEY_ID + " INTEGER PRIMARY KEY," + FACH_NAME
                + " TEXT," + SEMESTER + " INTEGER," + SCHNITT + " FLOAT," + ABKRZ + " TEXT," + KLASSE_ID + " INTEGER)";
    }

}
