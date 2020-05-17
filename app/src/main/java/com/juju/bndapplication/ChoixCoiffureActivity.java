package com.juju.bndapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juju.bndapplication.models.ReservationBean;

public class ChoixCoiffureActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private ProgressBar progressBar;
    private ImageView ivCoif1;
    private ImageView ivCoif2;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_coiffure);

        progressBar = findViewById(R.id.progressBar);
        ivCoif1 = findViewById(R.id.ivCoif1);
        ivCoif2 = findViewById(R.id.ivCoif2);

        progressBar.setProgress(40);

        ivCoif1.setOnClickListener(this);
        ivCoif2.setOnClickListener(this);

        Intent intent = getIntent();

        if (intent != null){
            ReservationBean reservation = intent.getParcelableExtra("reservation");
            if (reservation.getAdresse() != null){
                //A titre de test
                //Toast.makeText(this, reservation.getAdresse(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {

        //Choix de la coiffure

        //Lancement du prochain écran
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
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
