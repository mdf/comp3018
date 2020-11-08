package com.example.pszmdf.martinstorage;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAdapter {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_COLOUR = "colour";

    private static final String SQLITE_TABLE = "myList";

    private static final String SQLITE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_NAME + "," +
                    KEY_COLOUR +
                    ");";

    private DatabaseHelper dbHelper;
    public SQLiteDatabase db;
    private Context context;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, "fruitDB", null, 8);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("g53mdp", "onCreate");
            db.execSQL(SQLITE_CREATE);

            db.execSQL("INSERT INTO myList (name, colour) VALUES ('banana','yellow');");
            db.execSQL("INSERT INTO myList (name, colour) VALUES ('apple','green');");
            db.execSQL("INSERT INTO myList (name, colour) VALUES ('raspberry','red');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // do translation between database versions here
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public DatabaseAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    public void addNameColour(String name, String colour) {
        db.execSQL("INSERT INTO myList (name, colour) " +
                "VALUES " +
                "('" + name + "','" + colour + "');");
    }

    public Cursor fetchAll() {
        Cursor c = db.query("myList", new String[] { "_id", "name", "colour" }, null, null, null, null, null);
        return c;
    }
}