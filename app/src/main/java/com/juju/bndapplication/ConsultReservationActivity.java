package com.juju.bndapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Adapters.ConsultReservationAdapter;
import com.juju.bndapplication.models.CoiffeuseBean;
import com.juju.bndapplication.models.PrestationBean;
import com.juju.bndapplication.models.ReservationBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.requete.post.Synthese;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ConsultReservationActivity extends AppCompatActivity implements ConsultReservationAdapter.ItemClickListener {

    private ArrayList<ReservationBean> data = new ArrayList<>();
    private ConsultReservationAdapter adapter;
    private static UserBean user;
    private ReservationBean reservation;

    private RecyclerView rvMesReservations;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_reservation);

        rvMesReservations = findViewById(R.id.rvMesReservations);

        //Récupération de la vue précédente
        Intent intentReservation = getIntent();

        if (intentReservation != null) {
            //Récupère les différents objets
            user = intentReservation.getParcelableExtra("user");
            if (user.getPseudo() == null) {
                Intent intent1 = new Intent(this, ReservationAdresseActivity.class);
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

        //Renseigner le DATA !

        /*for (int i = 0; i < 9; i++) {
            PrestationBean prestationBean = new PrestationBean();
            prestationBean.getPrestation(i);
            CoiffeuseBean coiffeuseBean = new CoiffeuseBean();
            coiffeuseBean.getCoiffeuse(i);
            ReservationBean reservationBean = new ReservationBean(i, prestationBean, "option",
                    coiffeuseBean, "string", "string", "string", i, i);
            data.add(reservationBean);
        }*/

//        rvMesReservations.setLayoutManager(new LinearLayoutManager(this));
//
//        //rvMesReservations.setLayoutManager(new GridLayoutManager(this, 2));
//        adapter = new ConsultReservationAdapter(this, data);
//        adapter.setClickListener(this);
//        rvMesReservations.setAdapter(adapter);

        AllRervationAT myAT = new AllRervationAT();
        myAT.execute();

    }

    public void onBtRetourClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "POUF", Toast.LENGTH_SHORT).show();

    }

    public static void checkSyntheseReservation(Context context, ReservationBean reservation) {
        Intent intent = new Intent(context, ConsultReservationSyntheseActivity.class);
        intent.putExtra("reservSynthese", reservation);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

    public class AllRervationAT extends AsyncTask {
        Exception exception;
        ArrayList<ReservationBean> request;
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                request= Synthese.allReservation(user.getIdUsers());
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
                Toast.makeText(ConsultReservationActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            }else{
                data = request;
                rvMesReservations.setLayoutManager(new LinearLayoutManager(ConsultReservationActivity.this));
                adapter = new ConsultReservationAdapter(ConsultReservationActivity.this, data);
                adapter.setClickListener(ConsultReservationActivity.this);
                rvMesReservations.setAdapter(adapter);
            }
        }
    }

}