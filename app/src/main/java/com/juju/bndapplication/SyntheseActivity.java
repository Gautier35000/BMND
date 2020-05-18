package com.juju.bndapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.ReservationBean;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SyntheseActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar4;

    private ReservationBean reservation;
    private TextView tvAdresse;
    private TextView tvCPVille;
    private TextView tvPrestation;
    private TextView tvCoiffeuse;
    private TextView tvHoraire;
    private Button btRéservation;
    private Button btPrestation;
    private Button btCoiffeuse;
    private Button btConseils;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese);

        progressBar4 = findViewById(R.id.progressBar4);
        tvAdresse = findViewById(R.id.tvAdresse);
        tvCPVille = findViewById(R.id.tvCPVille);
        tvPrestation = findViewById(R.id.tvPrestation);
        tvCoiffeuse = findViewById(R.id.tvCoiffeuse);
        tvHoraire = findViewById(R.id.tvHoraire);
        btRéservation = findViewById(R.id.btRéservation);
        btPrestation = findViewById(R.id.btPrestation);
        btCoiffeuse = findViewById(R.id.btCoiffeuse);
        btConseils = findViewById(R.id.btConseils);

        progressBar4.setProgress(100);

        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            reservation = intentReservation.getParcelableExtra("reservation5");
            if (reservation.getCreneauHoraire() == null) {
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur de date est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                tvAdresse.setText(reservation.getAdresse());
                tvCPVille.setText(reservation.getCpVille());
                tvPrestation.setText(reservation.getCoiffure() + " , " + reservation.getOptions());
                tvCoiffeuse.setText(reservation.getCoiffeuse());
                //String dateReservation = récupérer la date une fois celle-ci gérée
                tvHoraire.setText(reservation.getDateReservation() + " à " + reservation.getCreneauHoraire());
            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }

        btCoiffeuse.setOnClickListener(this);
        btConseils.setOnClickListener(this);
        btPrestation.setOnClickListener(this);
        btRéservation.setOnClickListener(this);

    }

    @Override
    public void onClick(final View v) {

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous abandonner votre réservation en cours ?");
        alerte.setTitle("Quitter commande");
        alerte.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (v == btRéservation) {
                    Intent intent = new Intent(SyntheseActivity.this, ReservationAdresseActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (v == btPrestation){
                    Intent intent = new Intent(SyntheseActivity.this, GaleriePrestationActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (v == btCoiffeuse){
                    Intent intent = new Intent(SyntheseActivity.this, GalerieCoiffeuseActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (v == btConseils){
                    Intent intent = new Intent(SyntheseActivity.this, ConseilsActivity.class);
                    startActivity(intent);
                    finish();
                }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Paramètres");
        menu.add(0, 2, 0, "Contact");
        menu.add(0, 3, 0, "CGV/CGU");
        menu.add(0, 4, 0, "Déconnexion");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                launchParametre();
                break;
            case 2:
                launchContact();
                break;
            case 3:
                launchCG();
                break;
            case 4:
                deconnexion();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchParametre() {
        Intent intent = new Intent(this, ParametreActivity.class);
        startActivity(intent);
    }

    private void launchContact() {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    private void launchCG() {
        Intent intent = new Intent(this, CGActivity.class);
        startActivity(intent);
    }

    private void deconnexion() {

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        alerte.setTitle("Déconnexion");
        alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Mettre fin à la session avant le retour vers la page de login

                Intent intent = new Intent(SyntheseActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alerte.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alerte.setIcon(R.mipmap.ic_launcher_round);
        alerte.show();

    }

    public void onBtValiderSyntheseClick(View view) {
        //Enregistrement de la commande
    }

}
