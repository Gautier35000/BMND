package com.juju.bndapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PrestationBean implements Parcelable {

    int prestationID;
    String nomPrestation;
    int prixMin;
    int prixMax;
    //image à gérer

    public PrestationBean(int prestationID, String nomPrestation, int prixMin, int prixMax) {
        this.prestationID = prestationID;
        this.nomPrestation = nomPrestation;
        this.prixMin = prixMin;
        this.prixMax = prixMax;
    }

    public PrestationBean() {
    }

    protected PrestationBean(Parcel in) {
        prestationID = in.readInt();
        nomPrestation = in.readString();
        prixMin = in.readInt();
        prixMax = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(prestationID);
        dest.writeString(nomPrestation);
        dest.writeInt(prixMin);
        dest.writeInt(prixMax);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PrestationBean> CREATOR = new Creator<PrestationBean>() {
        @Override
        public PrestationBean createFromParcel(Parcel in) {
            return new PrestationBean(in);
        }

        @Override
        public PrestationBean[] newArray(int size) {
            return new PrestationBean[size];
        }
    };

    public int getPrestationID() {
        return prestationID;
    }

    public void setPrestationID(int prestationID) {
        this.prestationID = prestationID;
    }

    public String getNomPrestation() {
        return nomPrestation;
    }

    public void setNomPrestation(String nomPrestation) {
        this.nomPrestation = nomPrestation;
    }

    public int getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(int prixMin) {
        this.prixMin = prixMin;
    }

    public int getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(int prixMax) {
        this.prixMax = prixMax;
    }

    //Template pour test
    public void getPrestation(int i){
        this.prestationID = i;
        this.prixMin = i + 5;
        this.prixMax = i + 10;

        switch (i){
            case 1 :
                nomPrestation = "Lissage / Défrisage";
                break;
            case 2 :
                nomPrestation = "Tresses Africaine";
                break;
            case 3 :
                nomPrestation = "Extensions de locks";
                break;
            case 4 :
                nomPrestation = "Mèches Brésilennes";
                break;
//            case 5 :
//                nomPrestation = "Grattage du lobe temporal gauche";
//                break;
//            case 6 :
//                nomPrestation = "Supplément mousse à raser sur les chiquots";
//                break;
//            case 7 :
//                nomPrestation = "Visite du parlement européen";
//                break;
//            case 8 :
//                nomPrestation = "Attribution d'un oscar";
//                break;
//            case 9 :
//                nomPrestation = "Parcours touristique de Los Angeles avec Eddie Murphy";
//                break;
//            case 10 :
//                nomPrestation = "Don d'un ticket de métro";
//                break;
//            case 11 :
//                nomPrestation = "Elevage de poux personnalisés";
//                break;
//            case 12 :
//                nomPrestation = "Initiation au langage JAVA";
//                break;
            default :
                nomPrestation = "oups :/";
                break;
        }
    }
}
