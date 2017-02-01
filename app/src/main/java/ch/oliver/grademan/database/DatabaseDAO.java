package ch.oliver.grademan.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class DatabaseDAO {
    protected DatabaseManager dbHelper;
    protected SQLiteDatabase db;

    public DatabaseDAO(Context context) {
        dbHelper = new DatabaseManager(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
