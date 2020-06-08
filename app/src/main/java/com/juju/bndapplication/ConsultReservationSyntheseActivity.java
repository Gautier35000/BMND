package com.juju.bndapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ConsultReservationSyntheseActivity extends AppCompatActivity {

    ReservationBean reservation;
    UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_reservation_synthese);

        //Récupération de la vue précédente
        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            //Récupère les différents objets
            reservation = intentReservation.getParcelableExtra("reservSynthese");
            //adresseReservation = intentReservation.getParcelableExtra("adresseReservation5");
            user = intentReservation.getParcelableExtra("user");
            if (reservation.getCreneauHoraire() == null) {
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {

            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }

    }

    public void onBtEffacerReservClick(View view) {

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment effacer votre réservation ?");
        alerte.setTitle("Quitter commande");
        alerte.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Requête effacement
                Toast.makeText(ConsultReservationSyntheseActivity.this, "Réservation supprimée", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alerte.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alerte.setIcon(R.mipmap.ic_launcher_round);
        alerte.show();

    }

    public void onBtModifierReservClick(View view) {
        //Modification de la réservation
        if (user.getPrincipal() > 0) {
            Intent intent = new Intent(this, ReservationAdresseActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("baseRéservation", reservation);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, ReservationAutreAdresseActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("baseRéservation", reservation);
            startActivity(intent);
            finish();
        }
    }

    public void onBtValiderSyntheseClick(View view) {
        finish();
    }

    public void onBtAccueilClick(View view) {
        Intent intent = new Intent(this, AcceuilActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void onBtReservationClick(View view) {
        if (user.getPrincipal() < 0) {
            Intent intent = new Intent(this, ReservationAdresseActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, ReservationAutreAdresseActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
    }

    public void onBtPrestationClick(View view) {
        Intent intent = new Intent(this, GaleriePrestationActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBtCoiffeuseClick(View view) {
        Intent intent = new Intent(this, GalerieCoiffeuseActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}