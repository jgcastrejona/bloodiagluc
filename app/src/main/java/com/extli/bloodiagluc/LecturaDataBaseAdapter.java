package com.extli.bloodiagluc;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LecturaDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "LECTURA" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "PREPRANDIAL text, POSTPRANDIAL text, FOREIGN KEY (L_ID) REFERENCES LOGIN(ID) ); ";
    public SQLiteDatabase db;
    private final Context context;
    private HelperLectura dbHelper;

    public LecturaDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new HelperLectura(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public LecturaDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String preprandial, String postprandial , int Usr) {
        ContentValues newValues = new ContentValues();
        newValues.put("PREPRANDIAL", preprandial);
        newValues.put("POSTPRANDIAL", postprandial);
        newValues.put("L_ID", Usr);

        db.insert("LECTURA", null, newValues);

    }

/*    public int deleteEntry(String UserName) {

        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where,
                new String[] { UserName });
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?",
                new String[] { userName }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[] { userName });
    } */
}