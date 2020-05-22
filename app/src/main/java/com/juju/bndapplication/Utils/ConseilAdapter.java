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
import com.juju.bndapplication.models.ConseilBean;

import java.util.ArrayList;

public class ConseilAdapter extends RecyclerView.Adapter<ConseilAdapter.ViewHolder>{

    private ArrayList<ConseilBean> data;
    Context context;

    private static ConseilAdapter.ItemClickListener itemClickListener;

    public ConseilAdapter(Context context, ArrayList<ConseilBean> data){
        this.context = context;
        this.data = data;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNumberConseil;

        public ViewHolder(View itemView){
            super(itemView);
            tvNumberConseil = itemView.findViewById(R.id.tvNumberConseil);
        }

        @Override
        public void onClick(View view){
            if (itemClickListener != null){itemClickListener.onItemClick(view, getAdapterPosition());}
        }

    }

    @NonNull
    @Override
    public ConseilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conseils_view, parent, false);
        return new ConseilAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConseilAdapter.ViewHolder holder, final int position) {
        final ConseilBean datum = data.get(position);
        holder.tvNumberConseil.setText(datum.getNomConseil());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ConseilBean getItem(int position){
        return data.get(position);
    }

    public interface ItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setClickListener(ConseilAdapter.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}