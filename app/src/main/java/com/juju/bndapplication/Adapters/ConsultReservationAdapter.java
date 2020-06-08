package com.juju.bndapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.juju.bndapplication.ChoixPrestationActivity;
import com.juju.bndapplication.ConsultReservationActivity;
import com.juju.bndapplication.R;
import com.juju.bndapplication.models.PrestationBean;
import com.juju.bndapplication.models.ReservationBean;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ConsultReservationAdapter extends RecyclerView.Adapter<ConsultReservationAdapter.ViewHolder>{

    private ArrayList<ReservationBean> data;
    Context context;

    private static ConsultReservationAdapter.ItemClickListener itemClickListener;

    public ConsultReservationAdapter(Context context, ArrayList<ReservationBean> data){
        this.context = context;
        this.data = data;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvMesReservDate;
        TextView tvMesReservSecteur;
        ImageView ivMesReserv;

        public ViewHolder(View itemView){
            super(itemView);
            tvMesReservDate = itemView.findViewById(R.id.tvMesReservDate);
            tvMesReservSecteur = itemView.findViewById(R.id.tvMesReservSecteur);
            ivMesReserv = itemView.findViewById(R.id.ivMesReserv);
        }

        @Override
        public void onClick(View view){
            if (itemClickListener != null){itemClickListener.onItemClick(view, getAdapterPosition());}
        }

    }

    @NonNull
    @Override
    public ConsultReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mes_reservations_view, parent, false);
        return new ConsultReservationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultReservationAdapter.ViewHolder holder, final int position) {
        final ReservationBean datum = data.get(position);
        holder.tvMesReservDate.setText(datum.getDateReservation());
        //holder.tvMesReservSecteur.setText(datum.getAdresseID());//Todo
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, data.get(position).getPrenom(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "OK_ITEM" + position, Toast.LENGTH_SHORT).show();
                ConsultReservationActivity.checkSyntheseReservation(context, datum);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ReservationBean getItem(int position){
        return data.get(position);
    }

    public interface ItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

}
