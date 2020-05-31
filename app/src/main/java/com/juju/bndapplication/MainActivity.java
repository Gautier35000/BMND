package com.juju.bndapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

//Gérer pourquoi l'image be s'affiche pas
public class MainActivity extends AppCompatActivity {

    //UserBean tampUser;

    //RequeteSetDAO requeteSetDAO = new RequeteSetDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Premier remplissage
        /*
         *NE PEUT PAS MARCHER !!!!
         * IL FAUT INSTANCIER ET OUVRIR LA CONNECTION !!!! fait ... ;p
         */
        /*for (int i = 1; i < 4; i++) {
            tampUser.getUser(i);
            requeteSetDAO.open();
            requeteSetDAO.setUserbean(tampUser);
            requeteSetDAO.close();
        }*/


        //Lancement d'une tempo en AsyncTask pour ne pas bloquer la progressbar
        //TempAsyncTask monAT = new TempAsyncTask(this);
        //monAT.execute();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public class TempoAsyncTask extends AsyncTask {

        Context context;

        public TempoAsyncTask (Context context){this.context = context;}

        @Override
        protected Object doInBackground(Object[] objects) {
            //Tempo
            SystemClock.sleep(1500);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //lancement de la page de login à la fin de la tempo
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public class TempAsyncTask extends AsyncTask{

        Context context;

        public TempAsyncTask (Context context){this.context = context;}

        @Override
        protected Object doInBackground(Object[] objects) {
            SystemClock.sleep(1500);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
