package com.extli.bloodiagluc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MedicoDataBaseHelper {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "MEDICO" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "NAME text, ADDRESS text, PHONE text , MAIL text,PASSWORD text ); ";
    public SQLiteDatabase db;
    private final Context context;
    private MedicoHelper mdHelper;

    public MedicoDataBaseHelper(Context _context) {
        context = _context;
        mdHelper = new MedicoHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public MedicoDataBaseHelper open() throws SQLException {
        db = mdHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String name, String address ,String phone, String mail, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("NAME", name);
        newValues.put("ADDRESS", address);
        newValues.put("PHONE", phone);
        newValues.put("MAIL", mail);
        newValues.put("PASSWORD", password);
        db.insert("MEDICO", null, newValues);

    }

    public int deleteEntry(String UserName) {

        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where,
                new String[] { UserName });
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String mail) {
        Cursor cursor = db.query("MEDICO", null, " MAIL=?",
                new String[] { mail }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    /*public void updateEntry(String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[] { userName });
    } */
}

