package com.juju.bndapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import com.juju.bndapplication.Utils.RequeteSetDAO;
import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.requete.post.Synthese;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SyntheseActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar4;
    private TextView tvAdresse;
    private TextView tvCPVille;
    private TextView tvPrestation;
    private TextView tvCoiffeuse;
    private TextView tvHoraire;
    private Button btRéservation;
    private Button btPrestation;
    private Button btCoiffeuse;
    private Button btConseils;

    private ReservationBean reservation;
    private AdresseBean adresseReservation;
    private UserBean user;
    private String optionString;

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

        //Récupération de la vue précédente
        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            //Récupère les différents objets
            reservation = intentReservation.getParcelableExtra("reservation5");
            adresseReservation = intentReservation.getParcelableExtra("adresseReservation5");
            user = intentReservation.getParcelableExtra("user");
            if (reservation.getCreneauHoraire() == null) {
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                AdresseBean adresseBean = new AdresseBean();
                adresseBean.getAdresse(reservation.getAdresseID());
                tvAdresse.setText(adresseReservation.getNuméro() + " " + adresseReservation.getVoie());
                tvCPVille.setText(adresseReservation.getCp() + " " + adresseReservation.getVille());
                tvPrestation.setText(reservation.getPrestation().getNomPrestation() + " ," + reservation.getOptions());
                tvCoiffeuse.setText(reservation.getCoiffeuse().getPrenom() + " " + reservation.getCoiffeuse().getNom());
                tvHoraire.setText(reservation.getDateReservation() + " à " + reservation.getCreneauHoraire());
                reservation.setClientID(user.getIdUsers());
                Log.w("tagSynthese", String.valueOf(reservation.getClientID()));
                Log.w("tagSynthese", reservation.getCreneauHoraire());
                Log.w("tagSynthese", reservation.getDateReservation());
                Log.w("tagSynthese", reservation.getOptions());
                Log.w("tagSynthese", String.valueOf(reservation.getAdresseID()));
                Log.w("tagSynthese", reservation.getCoiffeuse().toString());
                Log.w("tagSynthese", reservation.getPrestation().getNomPrestation());
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
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else if (v == btPrestation) {
                    Intent intent = new Intent(SyntheseActivity.this, GaleriePrestationActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else if (v == btCoiffeuse) {
                    Intent intent = new Intent(SyntheseActivity.this, GalerieCoiffeuseActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else if (v == btConseils) {
                    Intent intent = new Intent(SyntheseActivity.this, ConseilsActivity.class);
                    intent.putExtra("user", user);
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

        SyntheseAT syntheseAT = new SyntheseAT();
        syntheseAT.execute();

    }


    public class SyntheseAT extends AsyncTask {
        Exception exception;
        ArrayList<UserBean> request;

        @Override
        protected Object doInBackground(Object[] objects) {
            //Enregistrement de la commande
            try {
               request= Synthese.validerReservation(user, reservation, adresseReservation);
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //RequeteSetDAO.validationReservation(this, reservation);
            if (exception != null) {
//                Toast.makeText(SyntheseActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            }else{
                String text = "";
                for (UserBean userBean : request) {
                    text = userBean.getMessage();
//                    Intent intent = new Intent(SyntheseActivity.this, AcceuilActivity.class);
//                    intent.putExtra("user", user);
//                    startActivity(intent);
//                    finish();
                    Toast.makeText(SyntheseActivity.this, text, Toast.LENGTH_LONG).show();

                }
            }
        }
    }

}
