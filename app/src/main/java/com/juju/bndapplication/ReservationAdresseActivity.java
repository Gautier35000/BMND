package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;

public class ReservationAdresseActivity extends AppCompatActivity {

    private ProgressBar progressBar5;
    private TextView tvAdresse1;
    private TextView tvAdresse2;
    private RadioButton rdbtAdresseActuelle;
    private RadioButton rdbtAutreAdresse;

    AdresseBean adresseReservation = new AdresseBean();
    private UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_adresse);

        progressBar5 = findViewById(R.id.progressBar5);
        tvAdresse1 = findViewById(R.id.tvAdresse1);
        tvAdresse2 = findViewById(R.id.tvAdresse2);
        rdbtAdresseActuelle = findViewById(R.id.rdbtAdresseActuelle);
        rdbtAutreAdresse = findViewById(R.id.rdbtAutreAdresse);

        progressBar5.setProgress(20);

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
                adresseReservation.getAdresse(user.getAdresseID());
                tvAdresse1.setText(adresseReservation.toStringAdresse1());
                tvAdresse2.setText(adresseReservation.toStringAdresse2());
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

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        alerte.setTitle("Déconnexion");
        alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Mettre fin à la session avant le retour vers la page de login

                Intent intent = new Intent(ReservationAdresseActivity.this, LoginActivity.class);
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
    public void onBtValiderAdresseClick(View view) {
        //Choix Adresse ou autre vue
        if(rdbtAdresseActuelle.isChecked()) {

            //Enregistrement de l'adresse
            ReservationBean reservation = new ReservationBean();
            reservation.setAdresseID(adresseReservation.getAdresseID());

            //Passage à l'étape suivante
            Intent intent = new Intent(this, ChoixPrestationActivity.class);
            intent.putExtra("reservation1", reservation);
            intent.putExtra("adresseReservation1", adresseReservation);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
        else if(rdbtAutreAdresse.isChecked()){
            Intent intent = new Intent(this, ReservationAutreAdresseActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Veuillez faire un choix", Toast.LENGTH_SHORT).show();
        }
    }
}
