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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Adapters.CGAdapter;
import com.juju.bndapplication.Adapters.CoiffeuseAdapter;

import java.util.ArrayList;

public class CGActivity extends AppCompatActivity implements CGAdapter.ItemClickListener {

    ArrayList<String> data = new ArrayList<>();
    private CGAdapter adapter;
    private RecyclerView rvCG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_g);

        rvCG = findViewById(R.id.rvCG);

        for (int i = 0; i < 12; i++) {
            String cgLorem = getString(R.string.loremIpsum);
            data.add(cgLorem);
        }

        rvCG.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CGAdapter(this, data);
        adapter.setClickListener(this);
        rvCG.setAdapter(adapter);

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

                Intent intent = new Intent(CGActivity.this, com.juju.bndapplication.LoginActivity.class);
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

    @Override
    public void onBackPressed() {
    }

    public void onBtReservtionClick(View view) {
    }

    public void onBtPrestationClick(View view) {
    }

    public void onBtCoiffeuseClick(View view) {
    }

    public void onBtConseilClick(View view) {
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
