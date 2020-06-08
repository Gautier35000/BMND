package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.juju.bndapplication.models.UserBean;

public class ParametreActivity extends AppCompatActivity {

    private EditText etPseudo;
    private EditText etAdresseMail;
    private EditText etMdp;
    private EditText etConfirmMdp;

    private UserBean user;

    String tampPseudo;
    String tampMail;
    String tampMdp;
    String tampConfirmMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        etPseudo = findViewById(R.id.etPseudo);
        etAdresseMail = findViewById(R.id.etAdresseMail);
        etMdp = findViewById(R.id.etMdp);
        etConfirmMdp = findViewById(R.id.etConfirmMdp);

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

            }
        } else {
            Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
            Toast.makeText(this, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
            finish();
        }

    }

    public void onBtParamRetourClick(View view) {
        finish();
    }

    public void onBtParamValiderCLick(View view) {

         tampPseudo = String.valueOf(etPseudo.getText());
         tampMail = String.valueOf(etAdresseMail.getText());
         tampMdp = String.valueOf(etMdp.getText());
         tampConfirmMdp = String.valueOf(etConfirmMdp.getText());

        if ((tampPseudo.equals("") == false) || (tampMail.equals("") == false) || (tampMdp.equals("") == false) || (tampConfirmMdp.equals("") == false)){

            AlertDialog.Builder alerte = new AlertDialog.Builder(this);
            alerte.setMessage("Souhaitez-vous vraiment Modifier vos données ? (cela vous renverra vers l'accueil)");
            alerte.setTitle("Déconnexion");
            alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if ((tampMdp.equals("") == false) && (tampConfirmMdp.equals("") == false)){
                        if (tampMdp.equals(tampConfirmMdp)){
                            //Mise à jour des données user
                            if (tampPseudo.equals("") == false){user.setPseudo(tampPseudo);}
                            if (tampMail.equals("") == false){user.setMail(tampMail);}
                            if (tampMdp.equals("") == false){user.setPassword(tampPseudo);}

                            //Mise à jour BdD////////////////////////////////////////
                            Toast.makeText(ParametreActivity.this, "Mise à jour des données", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ParametreActivity.this, AcceuilActivity.class);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            finish();//A mettre dans le onPostExecute////////////////

                        }
                        else {
                            Toast.makeText(ParametreActivity.this, "Les deux champs de mot de passe ne sont pas identiques", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        if (tampPseudo.equals("") == false){user.setPseudo(tampPseudo);}
                        if (tampMail.equals("") == false){user.setMail(tampMail);}

                        //Mise à jour BdD////////////////////////////////////////
                        Toast.makeText(ParametreActivity.this, "Pseudo et/ou adresse mail modifiée", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ParametreActivity.this, AcceuilActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();//A mettre dans le onPostExecute////////////////
                    }

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
        else{
            finish();
        }

    }

    @Override
    public void onBackPressed() {
    }

}
