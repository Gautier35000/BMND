package com.juju.bndapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Adapters.CGAdapter;
import com.juju.bndapplication.Adapters.CoiffeuseAdapter;
import com.juju.bndapplication.models.UserBean;

import java.util.ArrayList;

public class CGActivity extends AppCompatActivity implements CGAdapter.ItemClickListener {

    ArrayList<String> data = new ArrayList<>();
    UserBean user;

    private CGAdapter adapter;
    private RecyclerView rvCG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_g);

        rvCG = findViewById(R.id.rvCG);

        String cgLorem = getString(R.string.loremIpsum);

        for (int i = 0; i < 12; i++) {
            data.add(cgLorem);
        }

        rvCG.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CGAdapter(this, data);
        adapter.setClickListener(this);
        rvCG.setAdapter(adapter);

    }

    public void onBtCGRetourClick(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onItemClick(View v, int position) {

    }

}
