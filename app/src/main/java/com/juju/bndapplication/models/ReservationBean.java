package com.juju.bndapplication.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ReservationBean implements Parcelable {

    private int adresseID;
    private PrestationBean prestation;
    private String options;
    private CoiffeuseBean coiffeuse;
    private String dateReservation; //Obligatoirement en format String, car les dates ne peuvent se tranférer d'une vue à l'autre
    private String creneauHoraire;
    private String client; //à changer en model client ou en nomClient
    //Peut-être rajouter l'ID

    //Constructeur full réservation sans ID
    public ReservationBean(int adresseID, PrestationBean prestationID, String options, CoiffeuseBean coiffeuse, String dateReservation, String creneauHoraire, String client) {
        this.adresseID = adresseID;
        this.prestation = prestationID;
        this.options = options;
        this.coiffeuse = coiffeuse;
        this.dateReservation = dateReservation;
        this.creneauHoraire = creneauHoraire;
        this.client = client;
    }

    public ReservationBean() {
    }


    protected ReservationBean(Parcel in) {
        adresseID = in.readInt();
        prestation = in.readParcelable(PrestationBean.class.getClassLoader());
        options = in.readString();
        coiffeuse = in.readParcelable(CoiffeuseBean.class.getClassLoader());
        dateReservation = in.readString();
        creneauHoraire = in.readString();
        client = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(adresseID);
        dest.writeParcelable(prestation, flags);
        dest.writeString(options);
        dest.writeParcelable(coiffeuse, flags);
        dest.writeString(dateReservation);
        dest.writeString(creneauHoraire);
        dest.writeString(client);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReservationBean> CREATOR = new Creator<ReservationBean>() {
        @Override
        public ReservationBean createFromParcel(Parcel in) {
            return new ReservationBean(in);
        }

        @Override
        public ReservationBean[] newArray(int size) {
            return new ReservationBean[size];
        }
    };

    public int getAdresseID() {
        return adresseID;
    }

    public void setAdresseID(int adresseID) {
        this.adresseID = adresseID;
    }

    public PrestationBean getPrestation() {
        return prestation;
    }

    public void setPrestation(PrestationBean prestation) {
        this.prestation = prestation;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public CoiffeuseBean getCoiffeuse() {
        return coiffeuse;
    }

    public void setCoiffeuse(CoiffeuseBean coiffeuse) {
        this.coiffeuse = coiffeuse;
    }

    public String getCreneauHoraire() {
        return creneauHoraire;
    }

    public void setCreneauHoraire(String creneauHoraire) {
        this.creneauHoraire = creneauHoraire;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDateReservation() {return dateReservation;}

    public void setDateReservation(String dateReservation) {this.dateReservation = dateReservation;}
}
