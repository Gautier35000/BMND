package com.juju.bndapplication.models;

import java.util.ArrayList;

public class ResultBean {
    //Connexion d'un utilisateur
    private ArrayList<UserBean> results;
    private ErrorBean errors;
    private ArrayList<AdresseBean> result_adresse;
    private ArrayList<CoiffeuseBean> result_coiffeuse;
    private ArrayList<OptionBean> choix_options;
    private ArrayList<PrestationBean> choix_prestations;
    private ArrayList<ReservationBean> request_reservation;

    public ResultBean(ArrayList<UserBean> results, ErrorBean errors, ArrayList<AdresseBean> result_adresse, ArrayList<CoiffeuseBean> result_coiffeuse) {
        this.results = results;
        this.errors = errors;
        this.result_adresse = result_adresse;
        this.result_coiffeuse = result_coiffeuse;
    }

    public ResultBean() {
    }

    public void setResults(ArrayList<UserBean> results) {
        this.results = results;
    }

    public ArrayList<UserBean> getResults() {
        return results;
    }

    public ErrorBean getErrors() {
        return errors;
    }

    public void setErrors(ErrorBean errors) {
        this.errors = errors;
    }

    public ArrayList<AdresseBean> getResult_adresse() {
        return result_adresse;
    }

    public void setResult_adresse(ArrayList<AdresseBean> result_adresse) {
        this.result_adresse = result_adresse;
    }

    public ArrayList<CoiffeuseBean> getResult_coiffeuse() {
        return result_coiffeuse;
    }

    public void setResult_coiffeuse(ArrayList<CoiffeuseBean> result_coiffeuse) {
        this.result_coiffeuse = result_coiffeuse;
    }

    public ArrayList<OptionBean> getChoix_options() {
        return choix_options;
    }

    public void setChoix_options(ArrayList<OptionBean> choix_options) {
        this.choix_options = choix_options;
    }

    public ArrayList<PrestationBean> getChoix_prestations() {
        return choix_prestations;
    }

    public void setChoix_prestations(ArrayList<PrestationBean> choix_prestations) {
        this.choix_prestations = choix_prestations;
    }

    public ArrayList<ReservationBean> getRequest_reservation() {
        return request_reservation;
    }

    public void setRequest_reservation(ArrayList<ReservationBean> request_reservation) {
        this.request_reservation = request_reservation;
    }
}
