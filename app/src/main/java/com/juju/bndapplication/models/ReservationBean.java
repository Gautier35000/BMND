package com.juju.bndapplication.models;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ReservationBean {

    private String adresse;
    private String cpVille;
    private String coiffure;
    private String options;
    private String coiffeuse;
    private final SimpleDateFormat dateReservation = new SimpleDateFormat("dd/MM/yyyy");
    private String creneauHoraire;
    private String client; //à changer en model client ou en nomClient

    public ReservationBean(String adresse, String cpVille, String coiffure, String options, String coiffeuse, String creneauHoraire, String client) {
        this.adresse = adresse;
        this.cpVille = cpVille;
        this.coiffure = coiffure;
        this.options = options;
        this.coiffeuse = coiffeuse;
        this.creneauHoraire = creneauHoraire;
        this.client = client;
    }

    public ReservationBean() {
    }

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

    public SimpleDateFormat getDateReservation() {
        return dateReservation;
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
}
