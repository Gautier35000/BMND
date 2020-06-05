package com.juju.bndapplication.models;

import java.util.ArrayList;

public class ResultBean {
    //Connexion d'un utilisateur
    private ArrayList<UserBean> results;
    private ErrorBean errors;
    private ArrayList<AdresseBean> result_adresse;

    public ResultBean(ArrayList<UserBean> results, ErrorBean errors, ArrayList<AdresseBean> result_adresse) {
        this.results = results;
        this.errors = errors;
        this.result_adresse = result_adresse;
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
}
