package com.juju.bndapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ChoixCoiffeuseActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar2;
    private ImageView ivCoiffeuse1;
    private ImageView ivCoiffeuse2;
    private ImageView ivViewProfile1;
    private ImageView ivViewProfile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_coiffeuse);

        progressBar2 = findViewById(R.id.progressBar2);
        ivCoiffeuse1 = findViewById(R.id.ivCoiffeuse1);
        ivCoiffeuse2 = findViewById(R.id.ivCoiffeuse2);
        ivViewProfile1 = findViewById(R.id.ivViewProfile1);
        ivViewProfile2 = findViewById(R.id.ivViewProfile2);

        progressBar2.setProgress(60);

        ivCoiffeuse1.setOnClickListener(this);
        ivCoiffeuse2.setOnClickListener(this);
        ivViewProfile1.setOnClickListener(this);
        ivViewProfile2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Choix de la coiffeuse

        if (v == ivCoiffeuse1 || v == ivCoiffeuse2) {
            //Vers choix du créneau horaire
            Intent intent = new Intent(this, ChoixHoraireActivity.class);
            startActivity(intent);
        }
        else if (v== ivViewProfile1 || v == ivViewProfile2){
            Intent intent = new Intent(this, ProfilCoiffeuseActivity.class);
            startActivity(intent);
        }
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

    }

    private void launchContact() {

    }

    private void launchCG() {

    }

    private void deconnexion() {

    }


    public void onBtReservtionClick(View view) {
    }

    public void onBtPrestationClick(View view) {
    }

    public void onBtCoiffeuseClick(View view) {
    }

    public void onBtConseilClick(View view) {
    }

}
