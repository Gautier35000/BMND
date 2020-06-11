package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Adapters.PrestationAdapter;
import com.juju.bndapplication.models.PrestationBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.requete.post.Prestation;

import java.util.ArrayList;

public class GaleriePrestationActivity extends AppCompatActivity implements PrestationAdapter.ItemClickListener {

    private ArrayList<PrestationBean> data = new ArrayList<>();
    private UserBean user;
    private PrestationAdapter adapter;
    private RecyclerView rvGaleriePrestation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie_prestation);

        rvGaleriePrestation = findViewById(R.id.rvGaleriePrestation);

        for (int i = 1; i < 13; i++) {
            PrestationBean prestation = new PrestationBean();
            prestation.getPrestation(i);
            data.add(prestation);
        }

        //Récupération des informations de l'intent précédente
        Intent currentIntent = getIntent();

        if (currentIntent != null) {
            //Récupération de l'objet reservation de l'intent récupérée
            user = currentIntent.getParcelableExtra("user");
            if (user.getPseudo() == null) {
                //Si erreur lors de la récupération, redirection vers la début de la réservation
                Intent intent1 = new Intent(this, LoginActivity.class);
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

//        PrestationAt prestationAT = new PrestationAt();
//        prestationAT.execute();

        rvGaleriePrestation.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new PrestationAdapter(this, data);
        adapter.setClickListener(this);
        rvGaleriePrestation.setAdapter(adapter);
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

        //Création d'une alertDialog demandant confirmation de l'utilisateur avant de quitter la session
        //Et redirection vers la page de login si confirmation
        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        alerte.setTitle("Déconnexion");
        alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Mettre fin à la session avant le retour vers la page de login

                Intent intent = new Intent(GaleriePrestationActivity.this, LoginActivity.class);
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

    //Boutons de pied d'écran
    //Dirigent vers les vues du même nom
    public void onBtAccueilClick(View view) {
        Intent intent = new Intent(this, AcceuilActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBtReservationClick(View view) {
        if (user.getPrincipal() > 0) {
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

    public void onBtCoiffeuseClick(View view) {
        Intent intent = new Intent(this, GalerieCoiffeuseActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void onBtConseilClick(View view) {
        Intent intent = new Intent(this, ConseilsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    @Override
    public void onBackPressed() {
    }

    public class PrestationAt extends AsyncTask {
        Exception exception;
        ArrayList<PrestationBean> request;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                request = Prestation.getGalleryPrestation();
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (exception != null) {
                Toast.makeText(GaleriePrestationActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            } else {

//                data = request;
//
//                rvGaleriePrestation.setLayoutManager(new GridLayoutManager(GaleriePrestationActivity.this, 2));
//                adapter = new PrestationAdapter(GaleriePrestationActivity.this, data);
//                adapter.setClickListener(GaleriePrestationActivity.this);
//                rvGaleriePrestation.setAdapter(adapter);
            }
        }
    }

}
