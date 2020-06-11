package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.OptionBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.requete.post.Option;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar2;
    private CheckBox cbOption1;
    private CheckBox cbOption2;
    private CheckBox cbOption3;
    private CheckBox cbOption4;
    private CheckBox cbOption5;
    private CheckBox cbOption6;
    private Button btPrestation;
    private Button btCoiffeuse;
    private Button btConseils;

    private ReservationBean reservation;
    private AdresseBean adresseReservation;
    private UserBean user;

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
        btPrestation = findViewById(R.id.btPrestation);
        btCoiffeuse = findViewById(R.id.btCoiffeuse);
        btConseils = findViewById(R.id.btConseils);

        progressBar2.setProgress(40);

        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            reservation = intentReservation.getParcelableExtra("reservation2");
            adresseReservation = intentReservation.getParcelableExtra("adresseReservation2");
            user = intentReservation.getParcelableExtra("user");
            if (reservation.getPrestation() == null) {
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
                Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
                finish();
            } else {

            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }

        /*for(int i = 1; i < 7; i++){
            OptionBean tampOption = new OptionBean();
            tampOption.getOption(i);
            String text = tampOption.getOptionNom();
            switch (i){
                case 1 : cbOption1.setText(text); break;
                case 2 : cbOption2.setText(text); break;
                case 3 : cbOption3.setText(text); break;
                case 4 : cbOption4.setText(text); break;
                case 5 : cbOption5.setText(text); break;
                case 6 : cbOption6.setText(text); break;
            }
        }*/


        btCoiffeuse.setOnClickListener(this);
        btConseils.setOnClickListener(this);
        btPrestation.setOnClickListener(this);

        ChoixOptionsAT choixOptionsAT = new ChoixOptionsAT();
        choixOptionsAT.execute();

    }

    public void onBtAccueilClick(View view) {

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous abandonner votre réservation en cours ?");
        alerte.setTitle("Quitter commande");
        alerte.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(OptionsActivity.this, AcceuilActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        alerte.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alerte.setIcon(R.mipmap.ic_launcher_round);
        alerte.show();
    }

    @Override
    public void onClick(final View v) {

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous abandonner votre réservation en cours ?");
        alerte.setTitle("Quitter commande");
        alerte.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (v == btPrestation){
                    Intent intent = new Intent(OptionsActivity.this, GaleriePrestationActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
                else if (v == btCoiffeuse){
                    Intent intent = new Intent(OptionsActivity.this, GalerieCoiffeuseActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
                else if (v == btConseils){
                    Intent intent = new Intent(OptionsActivity.this, ConseilsActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }

            }
        });
        alerte.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alerte.setIcon(R.mipmap.ic_launcher_round);
        alerte.show();

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

                Intent intent = new Intent(OptionsActivity.this, LoginActivity.class);
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

    public void onBtValiderClick(View view) {

        String option = " pas d'option";
        OptionBean optionBean = new OptionBean();

        if (cbOption1.isChecked()) {
            if(option == " pas d'option"){ option = "";}
            optionBean.getOption(1);
            option = option + "/" + cbOption1.getText();
        }
        if (cbOption2.isChecked()) {
            if(option == " pas d'option"){ option = "";}
            optionBean.getOption(2);
            option = option + "/" + cbOption2.getText();
        }
        if (cbOption3.isChecked()) {
            if(option == " pas d'option"){ option = "";}
            optionBean.getOption(3);
            option = option + "/" + cbOption3.getText();
        }
        if (cbOption4.isChecked()) {
            if(option == " pas d'option"){ option = "";}
            optionBean.getOption(4);
            option = option + "/" + cbOption4.getText();
        }
        if (cbOption5.isChecked()) {
            if(option == " pas d'option"){ option = "";}
            optionBean.getOption(5);
            option = option + "/" + cbOption5.getText();
        }
        if (cbOption6.isChecked()) {
            if(option == " pas d'option"){ option = "";}
            optionBean.getOption(6);
            option = option + "/" + cbOption6.getText();
        }

        reservation.setOptions(option);

        Intent intent = new Intent(this, ChoixCoiffeuseActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("adresseReservation3", adresseReservation);
        intent.putExtra("reservation3", reservation);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoixPrestationActivity.class);
        intent.putExtra("reservation1", reservation);
        intent.putExtra("user", user);
        intent.putExtra("adresseReservation1", adresseReservation);
        startActivity(intent);
        finish();
    }

    public class ChoixOptionsAT extends AsyncTask {
        ArrayList<OptionBean> request;
        Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                request = Option.OptionSelect();
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (exception != null) {
                Toast.makeText(OptionsActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            } else {
                int i = 1;
                String text;
                for (OptionBean optionBean : request) {
                    switch (i){
                        case 1 : cbOption1.setText(optionBean.getOptionNom()); break;
                        case 2 : cbOption2.setText(optionBean.getOptionNom()); break;
                        case 3 : cbOption3.setText(optionBean.getOptionNom()); break;
                        case 4 : cbOption4.setText(optionBean.getOptionNom()); break;
                        case 5 : cbOption5.setText(optionBean.getOptionNom()); break;
                        case 6 : cbOption6.setText(optionBean.getOptionNom()); break;
                    }
                    i++;
                }
            }
        }
    }

}
