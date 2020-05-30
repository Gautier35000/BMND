package com.juju.bndapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.juju.bndapplication.models.UserBean;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onBtLoginClick(View view) { //btValider
        //Déterminer ici si le login est bon ou pas

        //Faux login pour test sur user1
        UserBean user = new UserBean();
        user.getUser(2);
        //Si login OK, redirection vers l'acceuil
        Intent intent = new Intent(this, AcceuilActivity.class);
        intent.putExtra("user", user);
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
