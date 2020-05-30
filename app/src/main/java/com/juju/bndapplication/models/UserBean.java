package com.juju.bndapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {

    int userID;
    String pseudo;
    String adresseMail;
    String password;
    int adresseID;

    public UserBean(int userID, String pseudo, String adresseMail, String password, int adresseID) {
        this.userID = userID;
        this.pseudo = pseudo;
        this.adresseMail = adresseMail;
        this.password = password;
        this.adresseID = adresseID;
    }

    public UserBean() {
    }


    protected UserBean(Parcel in) {
        userID = in.readInt();
        pseudo = in.readString();
        adresseMail = in.readString();
        password = in.readString();
        adresseID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(pseudo);
        dest.writeString(adresseMail);
        dest.writeString(password);
        dest.writeInt(adresseID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdresseID() {
        return adresseID;
    }

    public void setAdresseID(int adresseID) {
        this.adresseID = adresseID;
    }

    public void getUser(int i){
        this.userID = i;
        this.adresseMail = "adresse@mail."+ i;
        this.adresseID = i;
        this.pseudo = "user"+i;
        this.password = "mdp"+i;
    }
}
