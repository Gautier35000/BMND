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

import com.juju.bndapplication.ChoixCoiffeuseActivity;
import com.juju.bndapplication.R;
import com.juju.bndapplication.models.CoiffeuseBean;

import java.util.ArrayList;

public class ChoixCoiffeuseAdapter extends RecyclerView.Adapter<ChoixCoiffeuseAdapter.ViewHolder>{

    private ArrayList<CoiffeuseBean> data;
    Context context;

    private static ChoixCoiffeuseAdapter.ItemClickListener itemClickListener;

    public ChoixCoiffeuseAdapter(Context context, ArrayList<CoiffeuseBean> data){
        this.context = context;
        this.data = data;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNomChoixCoiffeuse, tvNoteChoixCoiffeuse;
        ImageView ivChoixCoiffeuse;

        public ViewHolder(View itemView){
            super(itemView);
            tvNomChoixCoiffeuse = itemView.findViewById(R.id.tvNomChoixCoiffeuse);
            tvNoteChoixCoiffeuse = itemView.findViewById(R.id.tvNoteChoixCoiffeuse);
            ivChoixCoiffeuse = itemView.findViewById(R.id.ivChoixCoiffeuse);
        }

        @Override
        public void onClick(View view){
            if (itemClickListener != null){itemClickListener.onItemClick(view, getAdapterPosition());}
        }

    }

    @NonNull
    @Override
    public ChoixCoiffeuseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.choix_coiffeuse_view, parent, false);
        return new ChoixCoiffeuseAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoixCoiffeuseAdapter.ViewHolder holder, final int position) {
        final CoiffeuseBean datum = data.get(position);
        holder.tvNomChoixCoiffeuse.setText(datum.getPrenom() + " " + datum.getNom());
        holder.tvNoteChoixCoiffeuse.setText(String.valueOf(datum.getNote()));
        holder.ivChoixCoiffeuse.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChoixCoiffeuseActivity.onIvCoiffeuseClick(context, datum);
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

    public CoiffeuseBean getItem(int position){
        return data.get(position);
    }

    public interface ItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setClickListener(ChoixCoiffeuseAdapter.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
