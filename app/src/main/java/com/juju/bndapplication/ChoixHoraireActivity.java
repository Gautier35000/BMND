package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ChoixHoraireActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar3;
    private Button btPrestation;
    private Button btCoiffeuse;
    private Button btConseils;

    private ReservationBean reservation;
    private AdresseBean adresseReservation;
    private UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_horaire);

        progressBar3 = findViewById(R.id.progressBar3);

        btPrestation = findViewById(R.id.btPrestation);
        btCoiffeuse = findViewById(R.id.btCoiffeuse);
        btConseils = findViewById(R.id.btConseils);

        progressBar3.setProgress(80);

        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            reservation = intentReservation.getParcelableExtra("reservation4");
            adresseReservation = intentReservation.getParcelableExtra("adresseReservation4");
            user = intentReservation.getParcelableExtra("user");
            if (reservation.getCoiffeuse() == null) {
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

        btCoiffeuse.setOnClickListener(this);
        btConseils.setOnClickListener(this);
        btPrestation.setOnClickListener(this);

    }

    public void onBtAccueilClick(View view) {

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous abandonner votre réservation en cours ?");
        alerte.setTitle("Quitter commande");
        alerte.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ChoixHoraireActivity.this, AcceuilActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
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

    @Override
    public void onClick(final View v) {

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous abandonner votre réservation en cours ?");
        alerte.setTitle("Quitter commande");
        alerte.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (v == btPrestation){
                    Intent intent = new Intent(ChoixHoraireActivity.this, GaleriePrestationActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
                else if (v == btCoiffeuse){
                    Intent intent = new Intent(ChoixHoraireActivity.this, GalerieCoiffeuseActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
                else if (v == btConseils){
                    Intent intent = new Intent(ChoixHoraireActivity.this, ConseilsActivity.class);
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

    //Création du menu et de ses liens
    //Les menu dirigent vers les activities du même nom
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Mes réservations");
        menu.add(0, 2, 0, "Paramètres");
        menu.add(0, 3, 0, "Contact");
        menu.add(0, 4, 0, "CGV/CGU");
        menu.add(0, 5, 0, "Déconnexion");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                launchConsultReservation();
                break;
            case 2 :
                launchParametre();;
                break;
            case 3:
                launchContact();
                break;
            case 4:
                launchCG();
                break;
            case 5:
                deconnexion();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchConsultReservation(){
        Intent intent = new Intent(this, ConsultReservationActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void launchParametre() {
        Intent intent = new Intent(this, ParametreActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void launchContact() {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void launchCG() {
        Intent intent = new Intent(this, CGActivity.class);
        intent.putExtra("user", user);
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

                Intent intent = new Intent(ChoixHoraireActivity.this, LoginActivity.class);
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

    //Remplace pour le moment le lien devant se faire au niveau du tableau de choix
    public void remplacementTableau(View view) {

        reservation.setCreneauHoraire("8h-18h");
        reservation.setDateReservation("20/02/2020");

        //Toast.makeText(this, reservation.getDateReservation(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, SyntheseActivity.class);
        intent.putExtra("reservation5", reservation);
        intent.putExtra("adresseReservation5", adresseReservation);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoixCoiffeuseActivity.class);
        intent.putExtra("reservation3", reservation);
        intent.putExtra("user", user);
        intent.putExtra("adresseReservation3", adresseReservation);
        startActivity(intent);
        finish();
    }

}
