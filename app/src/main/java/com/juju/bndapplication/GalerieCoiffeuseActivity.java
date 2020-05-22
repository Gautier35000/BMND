package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Utils.CoiffeuseAdapter;
import com.juju.bndapplication.models.CoiffeuseBean;

import java.util.ArrayList;

public class GalerieCoiffeuseActivity extends AppCompatActivity implements CoiffeuseAdapter.ItemClickListener {

    private final ArrayList<CoiffeuseBean> data = new ArrayList<>();
    private CoiffeuseAdapter adapter;
    private RecyclerView rvGalerieCoiffeuse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie_coiffeuse);

        rvGalerieCoiffeuse = findViewById(R.id.rvGalerieCoiffeuse);

        for (int i = 1; i < 13; i++) {
            CoiffeuseBean coiffeuse = new CoiffeuseBean();
            coiffeuse.getCoiffeuse(i);
            data.add(coiffeuse);
        }

        rvGalerieCoiffeuse.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CoiffeuseAdapter(this, data);
        adapter.setClickListener(this);
        rvGalerieCoiffeuse.setAdapter(adapter);

    }

    public void launchProfileActivity(CoiffeuseBean coiffeuseBean){
        Intent intent = new Intent(this, ProfilCoiffeuseActivity.class);
        intent.putExtra("profilCoiffeuse", coiffeuseBean);
        startActivity(intent);
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

        //Création d'une alertDialog demandant confirmation de l'utilisateur avant de quitter la session
        //Et redirection vers la page de login si confirmation
        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        alerte.setTitle("Déconnexion");
        alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Mettre fin à la session avant le retour vers la page de login

                Intent intent = new Intent(GalerieCoiffeuseActivity.this, LoginActivity.class);
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
    public void onBtReservtionClick(View view) {
        Intent intent = new Intent(this, ReservationAdresseActivity.class);
        startActivity(intent);
    }

    public void onBtPrestationClick(View view) {
        Intent intent = new Intent(this, GaleriePrestationActivity.class);
        startActivity(intent);
    }

    public void onBtCoiffeuseClick(View view) {
        Intent intent = new Intent(this, GalerieCoiffeuseActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBtConseilClick(View view) {
        Intent intent = new Intent(this, ConseilsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
