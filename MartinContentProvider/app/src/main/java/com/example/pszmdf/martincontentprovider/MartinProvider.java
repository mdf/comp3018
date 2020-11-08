package com.example.pszmdf.martincontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileNotFoundException;

public class MartinProvider extends ContentProvider {

    private DBHelper dbHelper = null;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MartinProviderContract.AUTHORITY, "people", 1);
        uriMatcher.addURI(MartinProviderContract.AUTHORITY, "people/#", 2);
        uriMatcher.addURI(MartinProviderContract.AUTHORITY, "animals", 3);
        uriMatcher.addURI(MartinProviderContract.AUTHORITY, "animals/#", 4);
        uriMatcher.addURI(MartinProviderContract.AUTHORITY, "food", 5);
        uriMatcher.addURI(MartinProviderContract.AUTHORITY, "food/#", 6);
        uriMatcher.addURI(MartinProviderContract.AUTHORITY, "*", 7);
    }

    @Override
    public boolean onCreate() {

        Log.d("g53mdp", "contentprovider oncreate");
        this.dbHelper = new DBHelper(this.getContext(), "mydb", null, 9);
        return true;
    }

    @Override
    public String getType(Uri uri) {

        String contentType;

        if (uri.getLastPathSegment()==null) {
            contentType = MartinProviderContract.CONTENT_TYPE_MULTIPLE;
        } else {
            contentType = MartinProviderContract.CONTENT_TYPE_SINGLE;
        }

        return contentType;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName;

        switch(uriMatcher.match(uri)) {
            case 1:
                tableName = "people";
                break;
            case 3:
                tableName = "animals";
                break;
            default:
                tableName = "people";
                break;
        }

        long id = db.insert(tableName, null, values);
        db.close();
        Uri nu = ContentUris.withAppendedId(uri, id);

        Log.d("g53mdp", nu.toString());

        getContext().getContentResolver().notifyChange(nu, null);

        return nu;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.d("g53mdp", uri.toString() + " " + uriMatcher.match(uri));

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch(uriMatcher.match(uri)) {
            case 2:
                selection = "_ID = " + uri.getLastPathSegment();
            case 1:
                return db.query("people", projection, selection, selectionArgs, null, null, sortOrder);
            case 4:
                selection = "_ID = " + uri.getLastPathSegment();
            case 3:
                return db.query("animals", projection, selection, selectionArgs, null, null, sortOrder);
            case 5:
                String q5 = "SELECT _id, name, food FROM people UNION SELECT _id, name, food FROM animals";
                return db.rawQuery(q5, selectionArgs);
            case 6:
                String q6 = "SELECT _id, name, food FROM people UNION SELECT _id, name, food FROM animals WHERE _ID = " + uri.getLastPathSegment();
                return db.rawQuery(q6, selectionArgs);
            case 7:
                String q7 = "SELECT * FROM people UNION SELECT * FROM animals";
                return db.rawQuery(q7, selectionArgs);
            default:
                return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        throw new UnsupportedOperationException("not implemented");
    }
}
