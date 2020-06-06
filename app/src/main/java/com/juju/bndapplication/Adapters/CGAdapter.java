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

public class CGAdapter extends RecyclerView.Adapter<CGAdapter.ViewHolder>{

    private ArrayList<String> data;
    Context context;

    private static CGAdapter.ItemClickListener itemClickListener;

    public CGAdapter(Context context, ArrayList<String> data){
        this.context = context;
        this.data = data;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCG;

        public ViewHolder(View itemView){
            super(itemView);
            tvCG = itemView.findViewById(R.id.tvCG);
        }

        @Override
        public void onClick(View view){
            if (itemClickListener != null){itemClickListener.onItemClick(view, getAdapterPosition());}
        }

    }

    @NonNull
    @Override
    public CGAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cg_view, parent, false);
        return new CGAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CGAdapter.ViewHolder holder, final int position) {
        final String datum = data.get(position);
        holder.tvCG.setText(datum);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getItem(int position){
        return data.get(position);
    }

    public interface ItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setClickListener(CGAdapter.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}

