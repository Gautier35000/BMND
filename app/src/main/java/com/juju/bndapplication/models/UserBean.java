package com.juju.bndapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {

    private int idUsers;
    private String pseudo;
    private String mail;
    private String password;
    private String token;
    private String date_creation;
    private String message;
    private String code;
    private String erreur;
    private int adresseID;
    private int principal;

    public UserBean(int idUsers, String pseudo, String mail, String password, String token, String date_creation, String message, String code, String erreur, int adresseID) {
        this.idUsers = idUsers;
        this.pseudo = pseudo;
        this.mail = mail;
        this.password = password;
        this.token = token;
        this.date_creation = date_creation;
        this.message = message;
        this.code = code;
        this.erreur = erreur;
        this.adresseID = adresseID;
    }

    public UserBean() {
    }


    protected UserBean(Parcel in) {
        idUsers = in.readInt();
        pseudo = in.readString();
        mail = in.readString();
        password = in.readString();
        token = in.readString();
        date_creation = in.readString();
        message = in.readString();
        code = in.readString();
        erreur = in.readString();
        adresseID = in.readInt();
        principal = in.readInt();
    }

    public UserBean(int idUsers, String pseudo, String mail, String password, int adresseID) {
        this.idUsers = idUsers;
        this.pseudo = pseudo;
        this.mail = mail;
        this.password = password;
        this.adresseID = adresseID;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idUsers);
        dest.writeString(pseudo);
        dest.writeString(mail);
        dest.writeString(password);
        dest.writeString(token);
        dest.writeString(date_creation);
        dest.writeString(message);
        dest.writeString(code);
        dest.writeString(erreur);
        dest.writeInt(adresseID);
        dest.writeInt(principal);
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

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
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

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public void getUser(int i){
        this.idUsers = i;
        this.mail = "adresse@mail."+ i;
        this.adresseID = i;
        this.pseudo = "user"+i;
        this.password = "mdp"+i;
    }
}
