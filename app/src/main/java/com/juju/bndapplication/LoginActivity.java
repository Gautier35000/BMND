package com.juju.bndapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onBtLoginClick(View view) { //btValider
        //Déterminer ici si le login est bon ou pas

        //Si login OK, redirection vers l'acceuil
        Intent intent = new Intent(this, AcceuilActivity.class);
        startActivity(intent);
    }

    //Lancement de l'activity de création de compte
    public void onBtInscriptionClick(View view) {
        Intent intent = new Intent(this, CreacptActivity.class);
        startActivity(intent);
    }

    //Dirige vers un acceuil limité (ou vers une vidéo/pub présentant l'appli
    public void onBtDecouverteClick(View view) {
        //Pour l'exercice, relié vers accueil "normal"
        Intent intent = new Intent(this, AcceuilActivity.class);
        startActivity(intent);
    }
}
