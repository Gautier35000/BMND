package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;

public class ReservationAutreAdresseActivity extends AppCompatActivity {

    private EditText etNouvAdresseNum;
    private EditText etNouvAdresseVoie;
    private EditText etNouvAdresseCP;
    private EditText etNouvAdresseVille;

    private String tampCP;
    private String tampVille;
    private String tampVoie;
    private String tampNumero;

    private UserBean user = new UserBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_autre_adresse);

        etNouvAdresseNum = findViewById(R.id.etNouvAdresseNum);
        etNouvAdresseVoie = findViewById(R.id.etNouvAdresseVoie);
        etNouvAdresseCP = findViewById(R.id.etNouvAdresseCP);
        etNouvAdresseVille = findViewById(R.id.etNouvAdresseVille);

        //Récupération des informations de l'intent précédente
        Intent currentIntent = getIntent();

        if (currentIntent != null) {
            //Récupération de l'objet reservation de l'intent récupérée
            user = currentIntent.getParcelableExtra("user");
            if (user.getPseudo() == null) {
                //Si erreur lors de la récupération, redirection vers la début de la réservation
                //Intent intent1 = new Intent(this, AcceuilActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                //startActivity(intent1);
                finish();
            } else {
                //A titre de test, à supp
                //Toast.makeText(this, user.getPseudo(), Toast.LENGTH_SHORT).show();

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

                Intent intent = new Intent(ReservationAutreAdresseActivity.this, LoginActivity.class);
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

    }

    public void onBtPrestationClick(View view) {
        Intent intent = new Intent(this, GaleriePrestationActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBtCoiffeuseClick(View view) {
        Intent intent = new Intent(this, GalerieCoiffeuseActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBtConseilClick(View view) {
        Intent intent = new Intent(this, ConseilsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onBtAutreAdresseValiderClick(View view) {
        //Enregistrement de l'adresse
        ReservationBean reservation = new ReservationBean();
        AdresseBean adresseReservation = new AdresseBean();

        tampCP = String.valueOf(etNouvAdresseCP.getText());
        tampNumero = String.valueOf(etNouvAdresseNum.getText());
        tampVille = String.valueOf(etNouvAdresseVille.getText());
        tampVoie = String.valueOf(etNouvAdresseVoie.getText());

        adresseReservation.setCp(Integer.parseInt(tampCP));
        adresseReservation.setVoie(tampVoie);
        adresseReservation.setVille(tampVille);
        adresseReservation.setNumero(Integer.parseInt(tampNumero));
        reservation.setAdresseID(adresseReservation.getAdresseID());

        //Passage à l'étape suivante
        Intent intent = new Intent(this, ChoixPrestationActivity.class);
        intent.putExtra("reservation1", reservation);
        intent.putExtra("adresseReservation1", adresseReservation);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
