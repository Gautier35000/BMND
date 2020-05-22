package com.juju.bndapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//Gérer pourquoi l'image be s'affiche pas
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lancement d'une tempo en AsyncTask pour ne pas bloquer la progressbar
        TempoAsyncTask monAT = new TempoAsyncTask();
        monAT.execute();

    }

    public class TempoAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            //Tempo
            SystemClock.sleep(500);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //lancement de la page de login à la fin de la tempo
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
