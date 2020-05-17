package com.juju.bndapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.ReservationBean;

public class ReservationAdresseActivity extends AppCompatActivity {

    private ProgressBar progressBar5;
    private TextView tvAdresse1;
    private TextView tvAdresse2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_adresse);

        progressBar5 = findViewById(R.id.progressBar5);
        tvAdresse1 = findViewById(R.id.tvAdresse1);
        tvAdresse2 = findViewById(R.id.tvAdresse2);

        progressBar5.setProgress(20);

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onBtValiderAdresseClick(View view) {
        //Enregistrement de l'adresse
        ReservationBean reservation = new ReservationBean();
        reservation.setAdresse(tvAdresse1.getText().toString());
        reservation.setCpVille(tvAdresse2.getText().toString());
        //Passage à l'étape suivante
        Intent intent = new Intent(this, ChoixCoiffureActivity.class);
        intent.putExtra("reservation", reservation);
        startActivity(intent);
    }
}
