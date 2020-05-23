package com.juju.bndapplication;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Utils.ChoixCoiffeuseAdapter;
import com.juju.bndapplication.Utils.ChoixCoiffureAdapter;
import com.juju.bndapplication.models.CoiffeuseBean;
import com.juju.bndapplication.models.PrestationBean;
import com.juju.bndapplication.models.ReservationBean;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ChoixCoiffeuseActivity extends AppCompatActivity implements View.OnClickListener, ChoixCoiffeuseAdapter.ItemClickListener {

    //Déclarations des objets du layout
    private ProgressBar progressBar2;
    private Button btRéservation;
    private Button btPrestation;
    private Button btCoiffeuse;
    private Button btConseils;
    private RecyclerView rvChoixCoiffeuse;

    //Déclaration des variables locales
    private static ReservationBean reservation;
    private final ArrayList<CoiffeuseBean> data = new ArrayList<>();
    private ChoixCoiffeuseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_coiffeuse);

        //FindViewByIDs
        progressBar2 = findViewById(R.id.progressBar2);
        btRéservation = findViewById(R.id.btRéservation);
        btPrestation = findViewById(R.id.btPrestation);
        btCoiffeuse = findViewById(R.id.btCoiffeuse);
        btConseils = findViewById(R.id.btConseils);
        rvChoixCoiffeuse = findViewById(R.id.rvChoixCoiffeuse);

        //progressbar à 60%
        progressBar2.setProgress(60);

        //setOnClickListenner pour les boutons du bas
        btRéservation.setOnClickListener(this);
        btCoiffeuse.setOnClickListener(this);
        btConseils.setOnClickListener(this);
        btPrestation.setOnClickListener(this);

        for (int i = 1; i < 13; i++) {
            CoiffeuseBean coiffeuse = new CoiffeuseBean();
            coiffeuse.getCoiffeuse(i);
            data.add(coiffeuse);
        }

        //Récupération des informations de l'intent précédente
        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            //Récupération de l'objet reservation de l'intent récupérée
            reservation = intentReservation.getParcelableExtra("reservation3");
            if (reservation.getOptions() == null) {
                //Si erreur lors de la récupération, redirection vers la début de la réservation
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                //A titre de test, à supp
                Toast.makeText(this, reservation.getOptions(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }

        rvChoixCoiffeuse.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChoixCoiffeuseAdapter(this, data);
        adapter.setClickListener(this);
        rvChoixCoiffeuse.setAdapter(adapter);

    }

    @Override
    public void onClick(final View v) {

        //Si clic sur les boutons du bas, demande la confirmation à l'utilisateur d'abandonner la commande en cours
        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous abandonner votre réservation en cours ?");
        alerte.setTitle("Quitter commande");
        alerte.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Si oui, dirige vers la vue correspondant au bouton appuyé
                if (v == btRéservation) {
                    Intent intent = new Intent(ChoixCoiffeuseActivity.this, ReservationAdresseActivity.class);
                    startActivity(intent);
                    finish();
                } else if (v == btPrestation) {
                    Intent intent = new Intent(ChoixCoiffeuseActivity.this, GaleriePrestationActivity.class);
                    startActivity(intent);
                    finish();
                } else if (v == btCoiffeuse) {
                    Intent intent = new Intent(ChoixCoiffeuseActivity.this, GalerieCoiffeuseActivity.class);
                    startActivity(intent);
                    finish();
                } else if (v == btConseils) {
                    Intent intent = new Intent(ChoixCoiffeuseActivity.this, ConseilsActivity.class);
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

                Intent intent = new Intent(ChoixCoiffeuseActivity.this, LoginActivity.class);
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

    //A titre de test
    //Simulation d'un choix multiple de coiffeuses
    public static void onMichelleClick(Context context, CoiffeuseBean coiffeuse) {

        reservation.setCoiffeuse(coiffeuse.getPrenom());

        //Vers choix du créneau horaire
        Intent intent = new Intent(context, ChoixHoraireActivity.class);
        intent.putExtra("reservation4", reservation);
        context.startActivity(intent);
        //finish();
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    /*public void onRoselineClick(View view) {

        reservation.setCoiffeuse("Roseline");

        //Vers choix du créneau horaire
        Intent intent = new Intent(this, ChoixHoraireActivity.class);
        intent.putExtra("reservation4", reservation);
        startActivity(intent);
        finish();
    }*/
}
