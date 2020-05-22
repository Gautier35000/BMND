package com.juju.bndapplication.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.ProfilCoiffeuseActivity;
import com.juju.bndapplication.R;
import com.juju.bndapplication.models.CoiffeuseBean;

import java.util.ArrayList;

public class CoiffeuseAdapter extends RecyclerView.Adapter<CoiffeuseAdapter.ViewHolder>{

    private ArrayList<CoiffeuseBean> data;
    Context context;

    private static ItemClickListener itemClickListener;

    public CoiffeuseAdapter(Context context, ArrayList<CoiffeuseBean> data){
        this.context = context;
        this.data = data;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvGalerieNom, tvGaleriePrenom;
        ImageView ivGalerieCoiffeuse;

        public ViewHolder(View itemView){
            super(itemView);
            tvGalerieNom = itemView.findViewById(R.id.tvGalerieNom);
            tvGaleriePrenom = itemView.findViewById(R.id.tvGaleriePrenom);
            ivGalerieCoiffeuse = itemView.findViewById(R.id.ivGalerieCoiffeuse);
        }

        @Override
        public void onClick(View view){
            if (itemClickListener != null){itemClickListener.onItemClick(view, getAdapterPosition());}
        }

    }

    @NonNull
    @Override
    public CoiffeuseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coiffeuse_galerie_view, parent, false);
        return new CoiffeuseAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CoiffeuseAdapter.ViewHolder holder, final int position) {
        final CoiffeuseBean datum = data.get(position);
        holder.tvGalerieNom.setText(datum.getNom());
        holder.tvGaleriePrenom.setText(datum.getPrenom());
        holder.ivGalerieCoiffeuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfilCoiffeuseActivity.class);
                intent.putExtra("profilCoiffeuse", datum);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.get(position).getPrenom(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public CoiffeuseBean getItem(int position){
        return data.get(position);
    }

    public interface ItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
