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

@RequiresApi(api = Build.VERSION_CODES.N)
public class ChoixCoiffureActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables
    private ProgressBar progressBar;
    private ImageView ivCoif1;
    private ImageView ivCoif2;
    ReservationBean reservation;

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

        //Récupération de l'objet réservation initié en ReservationAdresseActivity
        //Redirection vers la ReservationAdresseActivity si les données n'ont pas été récupérées
        Intent intentReservation = getIntent();

        if (intentReservation != null){
            reservation = intentReservation.getParcelableExtra("reservation1");
            if (reservation.getAdresse() == null || reservation .getCpVille() == null){
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            }
            else {
                Toast.makeText(this, reservation.getAdresse(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        //Choix de la coiffure
        if (v == ivCoif1) {
            reservation.setCoiffure("Tresses");
        }
        else if (v == ivCoif2) {
            reservation.setCoiffure("Perruque");
        }

        //Lancement du prochain écran
        Intent intent = new Intent(this, OptionsActivity.class);
        intent.putExtra("reservation2", reservation);
        startActivity(intent);
        finish();
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
