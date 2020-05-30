package com.juju.bndapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AdresseBean implements Parcelable {

    private int adresseID;
    private int numéro;
    private String voie;
    private String ville;
    private int cp;
    private int userID;

    public AdresseBean(int adresseID, int numéro, String voie, String ville, int cp, int userID) {
        this.adresseID = adresseID;
        this.numéro = numéro;
        this.voie = voie;
        this.ville = ville;
        this.cp = cp;
        this.userID = userID;
    }

    public AdresseBean() {
    }

    protected AdresseBean(Parcel in) {
        adresseID = in.readInt();
        numéro = in.readInt();
        voie = in.readString();
        ville = in.readString();
        cp = in.readInt();
        userID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(adresseID);
        dest.writeInt(numéro);
        dest.writeString(voie);
        dest.writeString(ville);
        dest.writeInt(cp);
        dest.writeInt(userID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdresseBean> CREATOR = new Creator<AdresseBean>() {
        @Override
        public AdresseBean createFromParcel(Parcel in) {
            return new AdresseBean(in);
        }

        @Override
        public AdresseBean[] newArray(int size) {
            return new AdresseBean[size];
        }
    };

    public int getAdresseID() {
        return adresseID;
    }

    public void setAdresseID(int adresseID) {
        this.adresseID = adresseID;
    }

    public int getNuméro() {
        return numéro;
    }

    public void setNuméro(int numéro) {
        this.numéro = numéro;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void getAdresse(int i) {

        this.adresseID = i;
        this.numéro = i;
        this.voie = "voie du numéro " + i;
        this.ville = "ville"+i;
        this.cp = 31000 + i;
        this.userID = i;

    }

    public String toStringAdresse1(){
        String adresse1 = this.getNuméro() + " " + this.getVoie();
        return adresse1;
    }

    public  String toStringAdresse2(){
        String adresse2 = this.getCp() + " " + this.getVille();
        return adresse2;
    }
}
