package com.juju.bndapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.requete.post.UserConnexion;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    private EditText etAdresseMail;
    private EditText etMDP;
    private Button btValider;
    private TextView tvOubliMDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etAdresseMail = findViewById(R.id.etAdresseMail);
        etMDP = findViewById(R.id.etMDP);
        btValider = findViewById(R.id.btValider);
        tvOubliMDP = findViewById(R.id.tvOubliMDP);

    }

    public void onBtLoginClick(View view) { //btValider
        //Déterminer ici si le login est bon ou pas

        //Faux login pour test sur user1
        /*UserBean user = new UserBean();
        user.getUser(2);
        //Si login OK, redirection vers l'acceuil
        Intent intent = new Intent(this, AcceuilActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);*/

        LoginAT loginAT = new LoginAT();
        loginAT.execute();
    }

    //Lancement de l'activity de création de compte
    public void onBtInscriptionClick(View view) {
        Intent intent = new Intent(this, CreacptActivity.class);
        startActivity(intent);
    }

    //Dirige vers un acceuil limité (ou vers une vidéo/pub présentant l'appli
    public void onBtDecouverteClick(View view) {
        //Pour l'exercice, relié vers accueil "normal"
        UserBean user = new UserBean();
        user.getUser(1);
        Intent intent = new Intent(this, AcceuilActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public class LoginAT extends AsyncTask {
        ArrayList<UserBean> request;
        String mail;
        String mdp;
        String code;
        Exception exception;

        public LoginAT() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                request = UserConnexion.connexion(etAdresseMail, etMDP);
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
                Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                String text = "";
                for (UserBean userBean : request) {
                    text = userBean.getMessage();
                    code = userBean.getCode();
                }
                switch (code) {
                    case "0":
                        //Connexion réussi
                        Toast.makeText(LoginActivity.this, text, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, AcceuilActivity.class);
                        intent.putExtra("user", request.get(0));
                        startActivity(intent);
                        finish();
                        break;
                    case "1":
                        //Le mail ou le mot de passe ne sont pas valides
                        Toast.makeText(LoginActivity.this, text, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}
