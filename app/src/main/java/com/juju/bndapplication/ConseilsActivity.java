package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Adapters.ConseilAdapter;
import com.juju.bndapplication.models.ConseilBean;
import com.juju.bndapplication.models.UserBean;

import java.util.ArrayList;

public class ConseilsActivity extends AppCompatActivity implements ConseilAdapter.ItemClickListener {

    private final ArrayList<ConseilBean> data = new ArrayList<>();
    private UserBean user;
    private ConseilAdapter adapter;
    private RecyclerView rvConseils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseils);

        rvConseils = findViewById(R.id.rvConseils);

        for (int i = 1; i < 13; i++) {
            ConseilBean conseil = new ConseilBean();
            conseil.setNomConseil("Conseil n°" + i);
            data.add(conseil);
        }

        //Récupération des informations de l'intent précédente
        Intent currentIntent = getIntent();

        if (currentIntent != null) {
            //Récupération de l'objet reservation de l'intent récupérée
            user = currentIntent.getParcelableExtra("user");
            if (user.getPseudo() == null) {
                //Si erreur lors de la récupération, redirection vers la début de la réservation
                Intent intent1 = new Intent(this, LoginActivity.class);
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

        rvConseils.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConseilAdapter(this, data);
        adapter.setClickListener(this);
        rvConseils.setAdapter(adapter);
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

        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        alerte.setTitle("Déconnexion");
        alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Mettre fin à la session avant le retour vers la page de login

                Intent intent = new Intent(ConseilsActivity.this, LoginActivity.class);
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

    public void onBtAccueilClick(View view) {
        Intent intent = new Intent(this, AcceuilActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBtReservationClick(View view) {
        if (user.getPrincipal() > 0) {
            Intent intent = new Intent(this, ReservationAdresseActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this, ReservationAutreAdresseActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
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

    @Override
    public void onItemClick(View v, int position) {

    }

    @Override
    public void onBackPressed() {
    }

}
