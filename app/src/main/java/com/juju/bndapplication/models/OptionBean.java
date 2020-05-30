package com.juju.bndapplication.models;

public class OptionBean {
    int optionID;
    String optionNom;
    int optionPrix;

    public OptionBean(int optionID, String optionNom, int optionPrix) {
        this.optionID = optionID;
        this.optionNom = optionNom;
        this.optionPrix = optionPrix;
    }

    public OptionBean() {
    }

    public int getOptionID() {
        return optionID;
    }

    public void setOptionID(int optionID) {
        this.optionID = optionID;
    }

    public String getOptionNom() {
        return optionNom;
    }

    public void setOptionNom(String optionNom) {
        this.optionNom = optionNom;
    }

    public int getOptionPrix() {
        return optionPrix;
    }

    public void setOptionPrix(int optionPrix) {
        this.optionPrix = optionPrix;
    }

    public void getOption(int i){
        this.optionID = i;
        this.optionNom = "option " + i;
        this.optionPrix = 10 + i;
    }
}
