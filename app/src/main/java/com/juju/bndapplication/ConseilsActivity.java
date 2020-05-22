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

import com.juju.bndapplication.Utils.CoiffeuseAdapter;
import com.juju.bndapplication.Utils.ConseilAdapter;
import com.juju.bndapplication.models.CoiffeuseBean;
import com.juju.bndapplication.models.ConseilBean;

import java.util.ArrayList;

public class ConseilsActivity extends AppCompatActivity implements ConseilAdapter.ItemClickListener {

    private final ArrayList<ConseilBean> data = new ArrayList<>();
    private ConseilAdapter adapter;
    private RecyclerView rvConseils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseils);

        rvConseils = findViewById(R.id.rvConseils);

        for (int i = 1; i < 13; i++) {
            ConseilBean conseil = new ConseilBean();
            conseil.setNomConseil("Conseil n°" + i);
            data.add(conseil);
        }

        rvConseils.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConseilAdapter(this, data);
        adapter.setClickListener(this);
        rvConseils.setAdapter(adapter);
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

                Intent intent = new Intent(ConseilsActivity.this, LoginActivity.class);
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
    }

    public void onBtConseilClick(View view) {
        //laissée vide car déjà dans conseils
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
