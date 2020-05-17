package com.juju.bndapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.ReservationBean;

@RequiresApi(api = Build.VERSION_CODES.N)
public class OptionsActivity extends AppCompatActivity {

    private ProgressBar progressBar2;
    private ReservationBean reservation;
    private CheckBox cbOption1;
    private CheckBox cbOption2;
    private CheckBox cbOption3;
    private CheckBox cbOption4;
    private CheckBox cbOption5;
    private CheckBox cbOption6;

    private String compteurOptions = "Options :";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        progressBar2 = findViewById(R.id.progressBar2);
        cbOption1 = findViewById(R.id.cbOption1);
        cbOption2 = findViewById(R.id.cbOption2);
        cbOption3 = findViewById(R.id.cbOption3);
        cbOption4 = findViewById(R.id.cbOption4);
        cbOption5 = findViewById(R.id.cbOption5);
        cbOption6 = findViewById(R.id.cbOption6);

        progressBar2.setProgress(40);

        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            reservation = intentReservation.getParcelableExtra("reservation2");
            if (reservation.getCoiffure() == null) {
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                Toast.makeText(this, reservation.getCoiffure(), Toast.LENGTH_SHORT).show();
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

    public void onBtValiderClick(View view) {

        if (cbOption1.isChecked()){compteurOptions = compteurOptions + " Shampoing,";}
        if (cbOption2.isChecked()){compteurOptions = compteurOptions + " Teinte,";}
        if (cbOption3.isChecked()){compteurOptions = compteurOptions + " Truc avec les cheveux,";}
        if (cbOption4.isChecked()){compteurOptions = compteurOptions + " Comptage de poux,";}
        if (cbOption5.isChecked()){compteurOptions = compteurOptions + " Grattage de l'oreille gauche,";}
        if (cbOption6.isChecked()){compteurOptions = compteurOptions + " Finition au fer à souder.";}

        reservation.setOptions(compteurOptions);

        Intent intent = new Intent(this, ChoixCoiffeuseActivity.class);
        intent.putExtra("reservation3", reservation);
        startActivity(intent);
        finish();
    }
}
