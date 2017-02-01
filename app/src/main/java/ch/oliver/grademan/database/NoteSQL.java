package ch.oliver.grademan.database;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class NoteSQL {
    public  static final String TABLE_NOTE = "Note";
    public static final String KEY_ID = "Note_id";
    public static final String THEMA="Thema";
    public static final String NOTE= "Note";
    public static final String GEWICHTUNG="Gewichtung";
    public static final String FACH_ID="Fach_ID";


    public static String getSqlQueryForCreateTableNote() {
        return "CREATE TABLE "
                + TABLE_NOTE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + THEMA
                + " TEXT," + NOTE + " FLOAT," + GEWICHTUNG + " FLOAT,"+FACH_ID+" INTEGER)";
    }


}
