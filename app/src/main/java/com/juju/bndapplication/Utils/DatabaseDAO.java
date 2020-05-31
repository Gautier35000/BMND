package com.juju.bndapplication.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DatabaseDAO {

    //Valeur à changer pour déclencher une mise à jour (méthode onUpgrade())
    protected final static int VERSION = 1;
    protected final static String NOM_DB = "databaseBMND.db";

    protected static SQLiteDatabase myDB = null;
    protected DatabaseHandler mHandler;

    public DatabaseDAO(Context context) {
        this.mHandler = new DatabaseHandler(context, NOM_DB, null, VERSION);
    }

    public SQLiteDatabase open(){
        myDB = mHandler.getWritableDatabase();
        return myDB;
    }

    public void close(){
        myDB.close();
    }

    public SQLiteDatabase getDB(){
        return myDB;
    }
}
