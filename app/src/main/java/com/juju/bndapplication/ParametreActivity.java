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
            case 2:
                launchParametre();
                ;
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

    private void launchConsultReservation() {
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

        //Création d'une alertDialog demandant confirmation de l'utilisateur avant de quitter la session
        //Et redirection vers la page de login si confirmation
        AlertDialog.Builder alerte = new AlertDialog.Builder(this);
        alerte.setMessage("Souhaitez-vous vraiment vous déconnecter ?");
        alerte.setTitle("Déconnexion");
        alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Mettre fin à la session avant le retour vers la page de login

                Intent intent = new Intent(ParametreActivity.this, LoginActivity.class);
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
            alerte.setMessage("Souhaitez-vous vraiment Modifier vos données ?");
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
                            //intent.putExtra("user", user);
                            Toast.makeText(ParametreActivity.this, "Modif validée", Toast.LENGTH_SHORT).show();
                            finish();//A mettre dans le onPostExecute

                        }
                        else {
                            Toast.makeText(ParametreActivity.this, "Les deux champs de mot de passe ne sont pas identiques", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        if (tampPseudo.equals("") == false){user.setPseudo(tampPseudo);}
                        if (tampMail.equals("") == false){user.setMail(tampMail);}
                        if (tampMdp.equals("") == false){user.setPassword(tampPseudo);}

                        //Mise à jour BdD////////////////////////////////////////
                        //intent.putExtra("user", user);
                        Toast.makeText(ParametreActivity.this, "Modif validée", Toast.LENGTH_SHORT).show();
                        finish();//A mettre dans le onPostExecute
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
