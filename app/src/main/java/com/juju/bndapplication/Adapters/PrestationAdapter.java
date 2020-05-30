package com.juju.bndapplication.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.R;
import com.juju.bndapplication.models.PrestationBean;

import java.util.ArrayList;

public class PrestationAdapter extends RecyclerView.Adapter<PrestationAdapter.ViewHolder>{

    private ArrayList<PrestationBean> data;
    Context context;

    private static PrestationAdapter.ItemClickListener itemClickListener;

    public PrestationAdapter(Context context, ArrayList<PrestationBean> data){
        this.context = context;
        this.data = data;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvPrestationNom;
        ImageView ivGalerieCoiffeuse;

        public ViewHolder(View itemView){
            super(itemView);
            tvPrestationNom = itemView.findViewById(R.id.tvGaleriePrestationNom);
            ivGalerieCoiffeuse = itemView.findViewById(R.id.ivGaleriePrestation);
        }

        @Override
        public void onClick(View view){
            if (itemClickListener != null){itemClickListener.onItemClick(view, getAdapterPosition());}
        }

    }

    @NonNull
    @Override
    public PrestationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prestation_galerie_view, parent, false);
        return new PrestationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestationAdapter.ViewHolder holder, final int position) {
        final PrestationBean datum = data.get(position);
        holder.tvPrestationNom.setText(datum.getNomPrestation());
        holder.ivGalerieCoiffeuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder alerte = new AlertDialog.Builder(context);

                /*String prix;

                switch (datum.getPrestationID()){
                    case 1 :
                        prix = "10 à 15 €";
                        break;
                    case 2 :
                        prix = "12 choufleurs";
                        break;
                    case 3 :
                        prix = "8 mars et une chouquette";
                        break;
                    case 4 :
                        prix = "10 000 000 € !! MOUAHAHAHAHAH !";
                        break;
                    case 5 :
                        prix = "Un sourire et un calin (^.^)";
                        break;
                    case 6 :
                        prix = "Le ... de la crémière";
                        break;
                    case 7 :
                        prix = "un sandwich permesan/pepperonni";
                        break;
                    case 8 :
                        prix = "14 pack de 86";
                        break;
                    case 9 :
                        prix = "un kebab - frite sauce BBQ";
                        break;
                    case 10 :
                        prix = "GRATUIT !!";
                        break;
                    case 11 :
                        prix = "25€ ttc sous présentation d'une carte étudiant";
                        break;
                    case 12 :
                        prix = "2 chouquettes è.é";
                        break;
                    default :
                        prix = "oups :/";
                        break;
                }*/

                alerte.setMessage("Prix : entre " + String.valueOf(datum.getPrixMin()) + " et " + String.valueOf(datum.getPrixMax()) + " €");
                alerte.setTitle("Prix prestation");
                alerte.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(context, "T'AS VU ??!!", Toast.LENGTH_SHORT).show();
                    }
                });

                alerte.setIcon(R.mipmap.ic_launcher_round);
                alerte.show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.get(position).getNomPrestation(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public PrestationBean getItem(int position){
        return data.get(position);
    }

    public interface ItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setClickListener(PrestationAdapter.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
