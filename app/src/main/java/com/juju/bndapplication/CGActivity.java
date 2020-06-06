package com.juju.bndapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.Adapters.CGAdapter;
import com.juju.bndapplication.Adapters.CoiffeuseAdapter;

import java.util.ArrayList;

public class CGActivity extends AppCompatActivity implements CGAdapter.ItemClickListener {

    ArrayList<String> data = new ArrayList<>();
    private CGAdapter adapter;
    private RecyclerView rvCG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_g);

        rvCG = findViewById(R.id.rvCG);

        for (int i = 0; i < 12; i++) {
            String cgLorem = getString(R.string.loremIpsum);
            data.add(cgLorem);
        }

        rvCG.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CGAdapter(this, data);
        adapter.setClickListener(this);
        rvCG.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
    }

    public void onBtReservtionClick(View view) {
    }

    public void onBtPrestationClick(View view) {
    }

    public void onBtCoiffeuseClick(View view) {
    }

    public void onBtConseilClick(View view) {
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
