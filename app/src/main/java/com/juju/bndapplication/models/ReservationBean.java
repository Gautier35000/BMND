package com.juju.bndapplication.models;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ReservationBean implements Parcelable {

    private String adresse;
    private String cpVille;
    private String coiffure;
    private String options;
    private String coiffeuse;
    //private SimpleDateFormat dateReservationFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateReservation; //Obligatoirement en format String, car les dates ne peuvent se tranférer d'une vue à l'autre
    private String creneauHoraire;
    private String client; //à changer en model client ou en nomClient
    //Peut-être rajouter l'ID

    //Constructeur full réservation sans ID
    public ReservationBean(String adresse, String cpVille, String coiffure, String options, String coiffeuse, String dateReservation, String creneauHoraire, String client) {
        this.adresse = adresse;
        this.cpVille = cpVille;
        this.coiffure = coiffure;
        this.options = options;
        this.coiffeuse = coiffeuse;
        this.dateReservation = dateReservation;
        this.creneauHoraire = creneauHoraire;
        this.client = client;
    }

    public ReservationBean() {
    }


    protected ReservationBean(Parcel in) {
        adresse = in.readString();
        cpVille = in.readString();
        coiffure = in.readString();
        options = in.readString();
        coiffeuse = in.readString();
        dateReservation = in.readString();
        creneauHoraire = in.readString();
        client = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adresse);
        dest.writeString(cpVille);
        dest.writeString(coiffure);
        dest.writeString(options);
        dest.writeString(coiffeuse);
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCpVille() {
        return cpVille;
    }

    public void setCpVille(String cpVille) {
        this.cpVille = cpVille;
    }

    public String getCoiffure() {
        return coiffure;
    }

    public void setCoiffure(String coiffure) {
        this.coiffure = coiffure;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCoiffeuse() {
        return coiffeuse;
    }

    public void setCoiffeuse(String coiffeuse) {
        this.coiffeuse = coiffeuse;
    }

    /*public SimpleDateFormat getDateReservationFormat() {
        return dateReservationFormat;
    }*/

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
