package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.UserBean;

public class AcceuilActivity extends AppCompatActivity {

    public static UserBean user;
    private TextView tvBonjour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        //FindViewByIDs
        tvBonjour = findViewById(R.id.tvBonjour);

        //Récupération des informations de l'intent précédente
        Intent currentIntent = getIntent();

        if (currentIntent != null) {
            //Récupération de l'objet reservation de l'intent récupérée
            user = currentIntent.getParcelableExtra("user");
            if (user.getPseudo() == null) {
                //Si erreur lors de la récupération, redirection vers la début de la réservation
                Intent intent1 = new Intent(this, com.juju.bndapplication.LoginActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {
                tvBonjour.setText("Bonjour " + user.getPseudo() + ",");
            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }

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
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void launchParametre() {
        Intent intent = new Intent(this, ParametreActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void launchContact() {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void launchCG() {
        Intent intent = new Intent(this, CGActivity.class);
        intent.putExtra("user", user);
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

                Intent intent = new Intent(AcceuilActivity.this, com.juju.bndapplication.LoginActivity.class);
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
    public void onBtReservationClick(View view) {
       /* if (user.getPrincipal() > 0) {*/
            Intent intent = new Intent(this, ReservationAdresseActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            //finish();
       /* }
        else{
            Intent intent = new Intent(this, ReservationAutreAdresseActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }*/
    }

    public void onBtPrestationClick(View view) {
        Intent intent = new Intent(this, GaleriePrestationActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void onBtCoiffeuseClick(View view) {
        Intent intent = new Intent(this, GalerieCoiffeuseActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void onBtConseilClick(View view) {
        Intent intent = new Intent(this, ConseilsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

    }

}
