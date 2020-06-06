package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Adapters.CoiffeuseAdapter;
import com.juju.bndapplication.models.CoiffeuseBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.requete.post.Coiffeuse;

import java.util.ArrayList;

public class GalerieCoiffeuseActivity extends AppCompatActivity implements CoiffeuseAdapter.ItemClickListener {

    private ArrayList<CoiffeuseBean> data = new ArrayList<>();
    private UserBean user;
    private CoiffeuseAdapter adapter;
    private RecyclerView rvGalerieCoiffeuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie_coiffeuse);

        rvGalerieCoiffeuse = findViewById(R.id.rvGalerieCoiffeuse);


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

//        for (int i = 1; i < 13; i++) {
//            CoiffeuseBean coiffeuse = new CoiffeuseBean();
//            coiffeuse.getCoiffeuse(i);
//            data.add(coiffeuse);
//        }

        RandomAT randomAT = new RandomAT();
        randomAT.execute();

//        rvGalerieCoiffeuse.setLayoutManager(new GridLayoutManager(this, 2));
//        adapter = new CoiffeuseAdapter(this, data, user);
//        adapter.setClickListener(this);
//        rvGalerieCoiffeuse.setAdapter(adapter);

    }

    //Création du menu et de ses liens
    //Les menu dirigent vers les activities du même nom
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

        //Création d'une alertDialog demandant confirmation de l'utilisateur avant de quitter la session
        //Et redirection vers la page de login si confirmation
        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        alerte.setTitle("Déconnexion");
        alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Mettre fin à la session avant le retour vers la page de login

                Intent intent = new Intent(GalerieCoiffeuseActivity.this, LoginActivity.class);
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
        Intent intent = new Intent(this, ReservationAdresseActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBtPrestationClick(View view) {
        Intent intent = new Intent(this, GaleriePrestationActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBtCoiffeuseClick(View view) {

    }

    public void onBtConseilClick(View view) {
        Intent intent = new Intent(this, ConseilsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "clic dans le onItemClick de GalerieCoiffeuseActivity", Toast.LENGTH_SHORT).show();
    }




    public class RandomAT extends AsyncTask {
        Exception exception;
        ArrayList<CoiffeuseBean> request;

        @Override
        protected Object doInBackground(Object[] objects) {
            try{
                request= Coiffeuse.randomCoiffeuse();
            }catch (Exception e){
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(exception != null){
                Toast.makeText(GalerieCoiffeuseActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }else{
                for(CoiffeuseBean coiffeuseBean:request){
                    //todo
                    data = request;

                    rvGalerieCoiffeuse.setLayoutManager(new GridLayoutManager(GalerieCoiffeuseActivity.this, 2));
                    adapter = new CoiffeuseAdapter(GalerieCoiffeuseActivity.this, data, user);
                    adapter.setClickListener(GalerieCoiffeuseActivity.this);
                    rvGalerieCoiffeuse.setAdapter(adapter);
                }
            }
        }
    }
}
