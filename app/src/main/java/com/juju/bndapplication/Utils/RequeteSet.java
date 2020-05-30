package com.juju.bndapplication.Utils;

import android.content.Context;
import android.widget.Toast;

import com.juju.bndapplication.models.ReservationBean;

public class RequeteSet {
    public static void validationReservation(Context context, ReservationBean reservation) {

        //Une fois la réservation enregistrée
        Toast.makeText(context, "Réservation enregistrée", Toast.LENGTH_SHORT).show();
    }
}
