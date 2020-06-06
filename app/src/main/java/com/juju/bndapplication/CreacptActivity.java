package com.juju.bndapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.juju.bndapplication.models.UserBean;

import java.util.ArrayList;

public class CreacptActivity extends AppCompatActivity {

    private EditText etPseudo;
    private EditText etMail;
    private EditText etMDP;
    private EditText etConfirmMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacpt);

        etPseudo = (EditText) findViewById(R.id.etPseudo);
        etMail = (EditText) findViewById(R.id.etMail);
        etMDP = (EditText) findViewById(R.id.etMDP);
        etConfirmMdp = (EditText) findViewById(R.id.etConfirmMdp);
    }

    public void onCreacptBt(View view) {
        InscriptAT inscriptAT = new InscriptAT();
        inscriptAT.execute();
    }

    public class InscriptAT extends AsyncTask {
        ArrayList<UserBean> request;
        String mail;
        String mdp;
        Exception exception;
        String text;
        String code;
        private Context context;


        public InscriptAT() {

        }


        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                request = com.juju.bndapplication.requete.post.UserInscription.inscription(etPseudo,etMail,etMDP,etConfirmMdp);
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

                Toast.makeText(com.juju.bndapplication.CreacptActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }else{
                for(UserBean userBean:request){
                    text=userBean.getMessage();
                    code=userBean.getCode();
                }
                switch (code){
                    case "0":
                        //Inscription réussi
                        Toast.makeText(com.juju.bndapplication.CreacptActivity.this, text, Toast.LENGTH_LONG).show();
                        break;
                    case "1":
                        //Le deuxième mot de passe est différent du premier
                        Toast.makeText(com.juju.bndapplication.CreacptActivity.this, text, Toast.LENGTH_LONG).show();
                        break;
                    case "2":
                        //Il existe déjà un compte avec cette adresse mail
                        Toast.makeText(com.juju.bndapplication.CreacptActivity.this, text, Toast.LENGTH_LONG).show();
                        break;
                }

            }
        }
    }

}
