package com.juju.bndapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;

public class ConsultReservationActivity extends AppCompatActivity implements ConsultReservationAdapter.ItemClickListener {

    private final ArrayList<ReservationBean> data = new ArrayList<>();
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

        for (int i = 0; i < 9; i++) {
            PrestationBean prestationBean = new PrestationBean();
            prestationBean.getPrestation(i);
            CoiffeuseBean coiffeuseBean = new CoiffeuseBean();
            coiffeuseBean.getCoiffeuse(i);
            ReservationBean reservationBean = new ReservationBean(i, prestationBean, "option",
                    coiffeuseBean, "string", "string", "string", i, i);
            data.add(reservationBean);
        }

        rvMesReservations.setLayoutManager(new LinearLayoutManager(this));

        //rvMesReservations.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ConsultReservationAdapter(this, data);
        adapter.setClickListener(this);
        rvMesReservations.setAdapter(adapter);

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

}