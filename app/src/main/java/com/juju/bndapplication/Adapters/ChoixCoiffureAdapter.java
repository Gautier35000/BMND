package com.juju.bndapplication.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.ChoixPrestationActivity;
import com.juju.bndapplication.R;
import com.juju.bndapplication.models.PrestationBean;

import java.util.ArrayList;

public class ChoixCoiffureAdapter extends RecyclerView.Adapter<ChoixCoiffureAdapter.ViewHolder>{

    private ArrayList<PrestationBean> data;
    Context context;

    private static ChoixCoiffureAdapter.ItemClickListener itemClickListener;

    public ChoixCoiffureAdapter(Context context, ArrayList<PrestationBean> data){
        this.context = context;
        this.data = data;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvChoixPrestation;
        ImageView ivChoixPrestation;

        public ViewHolder(View itemView){
            super(itemView);
            tvChoixPrestation = itemView.findViewById(R.id.tvChoixPrestation);
            ivChoixPrestation = itemView.findViewById(R.id.ivChoixPrestation);
        }

        @Override
        public void onClick(View view){
            if (itemClickListener != null){itemClickListener.onItemClick(view, getAdapterPosition());}
        }

    }

    @NonNull
    @Override
    public ChoixCoiffureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.choix_coiffure_view, parent, false);
        return new ChoixCoiffureAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoixCoiffureAdapter.ViewHolder holder, final int position) {
        final PrestationBean datum = data.get(position);
        holder.tvChoixPrestation.setText(datum.getNomPrestation());
        holder.ivChoixPrestation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChoixPrestationActivity.onIvPrestationClick(context, datum);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, data.get(position).getPrenom(), Toast.LENGTH_SHORT).show();
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

    public void setClickListener(ChoixCoiffureAdapter.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
