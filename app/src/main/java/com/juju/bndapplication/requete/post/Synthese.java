package com.juju.bndapplication.requete.post;

import android.content.Context;
import android.widget.Toast;

import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;

public class Synthese {
    public static void validerReservation(UserBean user, ReservationBean reservation, AdresseBean adresseReservation, Context context) {


        Toast.makeText(context, "Votre réservation a bien été enregistrée.", Toast.LENGTH_SHORT).show();
    }
}
