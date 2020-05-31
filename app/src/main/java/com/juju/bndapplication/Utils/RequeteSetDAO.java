package com.juju.bndapplication.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;

public class RequeteSetDAO extends DatabaseDAO {

    RequeteGetDAO requeteGetDAO;

    public RequeteSetDAO(Context context) {
        super(context);
        this.requeteGetDAO = new RequeteGetDAO(context);
    }

    public static void validationReservation(Context context, ReservationBean reservation) {

        //Une fois la réservation enregistrée
        Toast.makeText(context, "Réservation enregistrée", Toast.LENGTH_SHORT).show();
    }

    public void setUserbean(UserBean bean){

        //if(requeteGetDAO.selectUserByID(bean.getUserID()) == null){
        /*ContentValues values = new ContentValues();
        values.put(DatabaseHandler.USER_COL_USER_ID, bean.getIdUsers());
        values.put(DatabaseHandler.USER_COL_PSEUDO, bean.getPseudo());
        values.put(DatabaseHandler.USER_COL_ADRESSE_MAIL, bean.getMail());
        values.put(DatabaseHandler.USER_COL_PASSWORD, bean.getPassword());
        values.put(DatabaseHandler.USER_COL_ADRESSE_ID, bean.getAdresseID());
        //myDB = mHandler.getWritableDatabase();
        myDB.insert(DatabaseHandler.TABLE_USER_NAME, null, values);
        //myDB.close();
        Log.w("TAG_1", "good");*/
        //}
    }
}
