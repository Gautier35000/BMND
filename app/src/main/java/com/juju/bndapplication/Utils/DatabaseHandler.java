package com.juju.bndapplication.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String RESERVATION_COL_ADRESSE_ID = "ADRESSE_ID";
    public static final String RESERVATION_COL_PRESTATION_ID = "PRESTATION_ID";
    public static final String RESERVATION_COL_OPTION = "OPTION";
    public static final String RESERVATION_COL_COIFFEUSE_ID = "COIFFEUSE_ID";
    public static final String RESERVATION_COL_DATE_RESERVATION = "DATE_RESERVATION";
    public static final String RESERVATION_COL_CRENEAU = "CRENEAU";
    public static final String RESERVATION_COL_CLIENT_ID = "CLIENT_ID";
    public static final String RESERVATION_COL_RESERVATION_ID = "RESERVATION_ID";

    public static final String USER_COL_USER_ID = "USER_ID";
    public static final String USER_COL_PSEUDO = "PSEUDO";
    public static final String USER_COL_ADRESSE_MAIL = "ADRESSE_MAIL";
    public static final String USER_COL_PASSWORD = "PASSWORD";
    public static final String USER_COL_ADRESSE_ID = "ADRESSE_ID";

    public static final String TABLE_RESERVATION_NAME = "RESERVATION";
    public static final String TABLE_USER_NAME = "USER";

    public static final String TABLE_RESERVATION_CREATE =
            "CREATE TABLE " + TABLE_RESERVATION_NAME + " (" + RESERVATION_COL_ADRESSE_ID + " INTEGER, " + RESERVATION_COL_PRESTATION_ID +
                    " INTEGER, " + RESERVATION_COL_OPTION + " TEXT, " + RESERVATION_COL_COIFFEUSE_ID + " INTEGER, " + RESERVATION_COL_DATE_RESERVATION +
                    " TEXT, " + RESERVATION_COL_CRENEAU + " TEXT, " + RESERVATION_COL_CLIENT_ID + " INTEGER, " + RESERVATION_COL_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT);";

    public static final String TABLE_USER_CREATE =
            "CREATE TABLE " + TABLE_USER_NAME + " (" + USER_COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_COL_PSEUDO + " TEXT, "
                    + USER_COL_ADRESSE_MAIL + " TEXT, " + USER_COL_PASSWORD + " TEXT, " + USER_COL_ADRESSE_ID + " INTEGER);";

    public static final String TABLE_RESERVATION_DROP = "DROP TABLE IF EXISTS " + TABLE_RESERVATION_NAME + ";";
    public static final String TABLE_USER_DROP = "DROP TABLE IF EXISTS " + TABLE_USER_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_RESERVATION_CREATE);
        db.execSQL(TABLE_USER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_RESERVATION_DROP);
        db.execSQL(TABLE_USER_DROP);
        onCreate(db);
    }
}
