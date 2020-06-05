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

import com.juju.bndapplication.Adapters.ChoixCoiffeuseAdapter;
import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.CoiffeuseBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;

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
    private static AdresseBean adresseReservation;
    private static UserBean user;
    private final ArrayList<CoiffeuseBean> data = new ArrayList<>();
    private ChoixCoiffeuseAdapter adapter;
    String test = "test";

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
            adresseReservation = intentReservation.getParcelableExtra("adresseReservation3");
                user = intentReservation.getParcelableExtra("user");
            if (reservation.getOptions() == null) {
                //Si erreur lors de la récupération, redirection vers la début de la réservation
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
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else if (v == btPrestation) {
                    Intent intent = new Intent(ChoixCoiffeuseActivity.this, GaleriePrestationActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else if (v == btCoiffeuse) {
                    Intent intent = new Intent(ChoixCoiffeuseActivity.this, GalerieCoiffeuseActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else if (v == btConseils) {
                    Intent intent = new Intent(ChoixCoiffeuseActivity.this, ConseilsActivity.class);
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
        startActivity(intent);
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
    public static void onIvCoiffeuseClick(Context context, CoiffeuseBean coiffeuse) {

        reservation.setCoiffeuse(coiffeuse);

        //Vers choix du créneau horaire
        Intent intent = new Intent(context, ChoixHoraireActivity.class);
        intent.putExtra("reservation4", reservation);
        intent.putExtra("adresseReservation4", adresseReservation);
        intent.putExtra("user", user);
        context.startActivity(intent);
        //finish();
    }

    @Override
    public void onItemClick(View v, int position) {

    }

}
