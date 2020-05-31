package com.juju.bndapplication.models;

import java.util.ArrayList;

public class ResultBean {
    //Connexion d'un utilisateur
    private ArrayList<UserBean> results;
    private ErrorBean errors;

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










}
