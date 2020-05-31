package com.juju.bndapplication.Utils;

import android.content.Context;
import android.database.Cursor;

import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.UserBean;

public class RequeteGetDAO extends DatabaseDAO {

    public RequeteGetDAO(Context context) {
        super(context);
    }

    public static AdresseBean getAdresse(int userID){
        AdresseBean adresseBean = new AdresseBean();
        return adresseBean;
    }

    public static UserBean selectUserByID(long id){

        try {
            Cursor c = myDB.rawQuery("SELECT * FROM " + DatabaseHandler.TABLE_USER_NAME + " WHERE " + DatabaseHandler.USER_COL_USER_ID + " = ?",
                    new String[]{String.valueOf(id)});

            c.moveToNext();

            int userID = c.getInt(0);
            String pseudo = c.getString(1);
            String adresseMail = c.getString(2);
            String password = c.getString(3);
            int adresseID = c.getInt(4);

            c.close();

            return new UserBean(userID, pseudo, adresseMail, password, adresseID);
        }
        catch (Exception e){
            e.printStackTrace();
            return new UserBean();
        }

    }
}
