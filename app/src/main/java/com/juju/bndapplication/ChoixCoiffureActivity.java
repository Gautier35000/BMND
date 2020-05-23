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

import com.juju.bndapplication.Utils.ChoixCoiffureAdapter;
import com.juju.bndapplication.Utils.ConseilAdapter;
import com.juju.bndapplication.models.ConseilBean;
import com.juju.bndapplication.models.PrestationBean;
import com.juju.bndapplication.models.ReservationBean;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ChoixCoiffureActivity extends AppCompatActivity implements View.OnClickListener, ChoixCoiffureAdapter.ItemClickListener {

    //Déclarations des objets du layout
    private ProgressBar progressBar;
    private Button btRéservation;
    private Button btPrestation;
    private Button btCoiffeuse;
    private Button btConseils;

    //Déclaration des variables locales
    private static ReservationBean reservation;
    private final ArrayList<PrestationBean> data = new ArrayList<>();
    private ChoixCoiffureAdapter adapter;
    private RecyclerView rvChoixCoiffure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_coiffure);

        //FindViewByIDs
        progressBar = findViewById(R.id.progressBar);
        btRéservation = findViewById(R.id.btRéservation);
        btPrestation = findViewById(R.id.btPrestation);
        btCoiffeuse = findViewById(R.id.btCoiffeuse);
        btConseils = findViewById(R.id.btConseils);
        rvChoixCoiffure = findViewById(R.id.rvChoixCoiffure);

        //Remplissage de la rv de prestation
        for (int i = 1; i < 13; i++) {
            PrestationBean prestation = new PrestationBean();
            prestation.getPrestation(i);
            data.add(prestation);
        }

        //progressbar à 40%
        progressBar.setProgress(40);

        //setOnClickListenner pour les boutons du bas
        btCoiffeuse.setOnClickListener(this);
        btConseils.setOnClickListener(this);
        btPrestation.setOnClickListener(this);
        btRéservation.setOnClickListener(this);

        //Récupération des informations de l'intent précédente
        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            //Récupération de l'objet reservation de l'intent récupérée
            reservation = intentReservation.getParcelableExtra("reservation1");
            if (reservation.getAdresse() == null || reservation.getCpVille() == null) {
                //Si erreur lors de la récupération, redirection vers la début de la réservation
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                //Toast.makeText(this, reservation.getAdresse(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }

        rvChoixCoiffure.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChoixCoiffureAdapter(this, data);
        adapter.setClickListener(this);
        rvChoixCoiffure.setAdapter(adapter);
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
                    Intent intent = new Intent(ChoixCoiffureActivity.this, ReservationAdresseActivity.class);
                    startActivity(intent);
                    finish();
                } else if (v == btPrestation) {
                    Intent intent = new Intent(ChoixCoiffureActivity.this, GaleriePrestationActivity.class);
                    startActivity(intent);
                    finish();
                } else if (v == btCoiffeuse) {
                    Intent intent = new Intent(ChoixCoiffureActivity.this, GalerieCoiffeuseActivity.class);
                    startActivity(intent);
                    finish();
                } else if (v == btConseils) {
                    Intent intent = new Intent(ChoixCoiffureActivity.this, ConseilsActivity.class);
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

                Intent intent = new Intent(ChoixCoiffureActivity.this, LoginActivity.class);
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
    //Simulation d'un choix multiple de coiffures
    public static void onIvCoif1Click(Context context, PrestationBean prestationBean) {

        reservation.setCoiffure(prestationBean.getNomPrestation());

        //Lancement du prochain écran
        Intent intent = new Intent(context, OptionsActivity.class);
        intent.putExtra("reservation2", reservation);
        context.startActivity(intent);
        //finish();
    }

    @Override
    public void onItemClick(View v, int position) {

    }

   /* public void onIvCoif2Click(View view) {

        reservation.setCoiffure("Perruque");

        //Lancement du prochain écran
        Intent intent = new Intent(this, OptionsActivity.class);
        intent.putExtra("reservation2", reservation);
        startActivity(intent);
        finish();
    }*/
}
