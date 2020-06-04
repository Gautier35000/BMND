package com.juju.bndapplication.requete.post;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.PrestationBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.ResultBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.outils.AccesHTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Synthese {
    private final static String URL = "http://jeanmarie-gautier.fr/integration/request.php";
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<UserBean> validerReservation(UserBean user, ReservationBean reservation, AdresseBean adresseReservation)throws Exception {
        String idUser = String.valueOf(user.getIdUsers());
        String date_reservation =reservation.getDateReservation();
        String commentaire=reservation.getCommentaire();
        String coiffeuse_id= String.valueOf(reservation.getCoiffeuse().getCoiffeuseID());
        String options=reservation.getOptions();
        String prestation= String.valueOf(reservation.getPrestation().getPrestationID());
        String adresse= String.valueOf(adresseReservation.getAdresseID());
        String json ="";


        Gson gson1 = new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key","synthese");
        valeurs.put("idUser",idUser);
        valeurs.put("date_reservation", date_reservation);
        valeurs.put("commentaire", commentaire);
        valeurs.put("coiffeuse_id", coiffeuse_id);
        valeurs.put("options", options);
        valeurs.put("prestation", prestation);
        valeurs.put("adresse", adresse);
        String requete = gson1.toJson(valeurs);

        try {
            json = AccesHTTP.sendGetOkHttpRequest(URL, requete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(json, ResultBean.class);


        if (resultBean.getErrors() != null) {
            throw new Exception(resultBean.getErrors().getMessage());
        } else {
            return resultBean.getResults();
        }


    }
}
