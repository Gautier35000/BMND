package com.juju.bndapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.ReservationBean;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SyntheseActivity extends AppCompatActivity {

    private ProgressBar progressBar4;

    private ReservationBean reservation;
    private TextView tvAdresse;
    private TextView tvCPVille;
    private TextView tvPrestation;
    private TextView tvCoiffeuse;
    private TextView tvHoraire;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese);

        progressBar4 = findViewById(R.id.progressBar4);
        tvAdresse = findViewById(R.id.tvAdresse);
        tvCPVille = findViewById(R.id.tvCPVille);
        tvPrestation = findViewById(R.id.tvPrestation);
        tvCoiffeuse = findViewById(R.id.tvCoiffeuse);
        tvHoraire = findViewById(R.id.tvHoraire);

        progressBar4.setProgress(100);

        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            reservation = intentReservation.getParcelableExtra("reservation5");
            if (reservation.getCreneauHoraire() == null) {
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur de date est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                tvAdresse.setText(reservation.getAdresse());
                tvCPVille.setText(reservation.getCpVille());
                tvPrestation.setText(reservation.getCoiffure() + " , " + reservation.getOptions());
                tvCoiffeuse.setText(reservation.getCoiffeuse());
                //String dateReservation = récupérer la date une fois celle-ci gérée
                /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = dateFormat.format(reservation.getDateReservation());
                Toast.makeText(this, date, Toast.LENGTH_SHORT).show();*/

                try {

                }
                catch (Exception e){

                }

                tvHoraire.setText("date" + " à " + reservation.getCreneauHoraire());
            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
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

    public void onBtValiderSyntheseClick(View view) {
        //Enregistrement de la commande
    }
}
