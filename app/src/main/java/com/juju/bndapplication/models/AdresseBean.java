package com.juju.bndapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AdresseBean implements Parcelable {

    private int adresseID;
    private int numero;
    private String voie;
    private String ville;
    private int cp;
    private String message;
    private String code;
    private String erreur;
    private int userID;

    public AdresseBean(int adresseID, int numero, String voie, String ville, int cp, String message, String code, String erreur, int userID) {
        this.adresseID = adresseID;
        this.numero = numero;
        this.voie = voie;
        this.ville = ville;
        this.cp = cp;
        this.message = message;
        this.code = code;
        this.erreur = erreur;
        this.userID = userID;
    }

    public AdresseBean() {
    }


    protected AdresseBean(Parcel in) {
        adresseID = in.readInt();
        numero = in.readInt();
        voie = in.readString();
        ville = in.readString();
        cp = in.readInt();
        message = in.readString();
        code = in.readString();
        erreur = in.readString();
        userID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(adresseID);
        dest.writeInt(numero);
        dest.writeString(voie);
        dest.writeString(ville);
        dest.writeInt(cp);
        dest.writeString(message);
        dest.writeString(code);
        dest.writeString(erreur);
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
        this.numero = i;
        this.voie = "voie du numéro " + i;
        this.ville = "ville"+i;
        this.cp = 31000 + i;
        this.userID = i;

    }

    public String toStringAdresse1(){
        String adresse1 = this.getNumero() + " " + this.getVoie();
        return adresse1;
    }

    public  String toStringAdresse2(){
        String adresse2 = this.getCp() + " " + this.getVille();
        return adresse2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }
}
