package com.juju.bndapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CoiffeuseBean implements Parcelable {

    private String nom;
    private String prenom;
    private String secteurs;
    private int note;
    private String pretations;
    private int coiffeuseID;

    public CoiffeuseBean(String nom, String prenom, String secteurs, int note, String pretations) {
        this.nom = nom;
        this.prenom = prenom;
        this.secteurs = secteurs;
        this.note = note;
        this.pretations = pretations;
    }

    public CoiffeuseBean() {
    }

    protected CoiffeuseBean(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        secteurs = in.readString();
        note = in.readInt();
        pretations = in.readString();
        coiffeuseID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(secteurs);
        dest.writeInt(note);
        dest.writeString(pretations);
        dest.writeInt(coiffeuseID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CoiffeuseBean> CREATOR = new Creator<CoiffeuseBean>() {
        @Override
        public CoiffeuseBean createFromParcel(Parcel in) {
            return new CoiffeuseBean(in);
        }

        @Override
        public CoiffeuseBean[] newArray(int size) {
            return new CoiffeuseBean[size];
        }
    };

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(String secteurs) {
        this.secteurs = secteurs;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getPretations() {
        return pretations;
    }

    public void setPretations(String pretations) {
        this.pretations = pretations;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCoiffeuseID() {
        return coiffeuseID;
    }

    public void setCoiffeuseID(int coiffeuseID) {
        this.coiffeuseID = coiffeuseID;
    }

    //Template Coiffeuse
    public void getCoiffeuse(int id){
        this.coiffeuseID = id;
        this.note = id;

        switch (id){
            case 1 :
                this.prenom = "Dark";
                this.nom = "Vador";
                this.pretations = "Erradication des poux, annihilation des cheveux blancs, génocide des enfants Jedi";
                this.secteurs = "Etoile noire, capsule de secours de l'étoile noire";
                break;
            case 2 :
                this.prenom = "New Hero";
                this.nom = "on Create";
                this.pretations = "Jonglage, saut périlleux arrière sur appui du pied droit, grande maitrise de la meringue au citron";
                this.secteurs = "Narnia, La Moria, Padhiver, Bourg-palette, Utopia, Tatooin";
                break;
            case 3 :
                this.prenom = "Michelle";
                this.nom = "Drucker";
                this.pretations = "Interview trop longue pour sa propre émission, analyse approfondie des épisodes de Columbo";
                this.secteurs = "France 2, petit café à côté des studios de France 2, chez sa maman";
                break;
            case 4 :
                this.prenom = "Roseline";
                this.nom = "Bachelot";
                this.pretations = "Doublage intempestif du stock de vaccin, provocation de réchauffement climatique avec un ventilateur";
                this.secteurs = "Elysée, trottoir de l'élysée, taxi de l'élysée, pôle-emploi";
                break;
            case 5 :
                this.prenom = "Père";
                this.nom = "Noël";
                this.pretations = "Distribution de cadeaux, mangeage de cookie";
                this.secteurs = "Cheminées diverses, tréneau, pôle nord";
                break;
            case 6 :
                this.prenom = "Obiwan";
                this.nom = "Kenobi";
                this.pretations = "Coupe au sabre laser, finition au sabre laser, et d'autres trucs au sabre laser";
                this.secteurs = "La maison de Yoda, où qu'elle soit...";
                break;
            case 7 :
                this.prenom = "Natsu";
                this.nom = "Dragneer";
                this.pretations = "Poing du dragon de feu, ailes du dragons de feu, et des trucs du dragon de feu";
                this.secteurs = "QG de Fairy Tail, la chambre de Lucy, là où il fait chaud, là où ça castagne";
                break;
            case 8 :
                this.prenom = "Lucifer";
                this.nom = "Fils de dieu";
                this.pretations = "Réalisation de tous vos rêves, lecture de vos pensées, sourire ravageur, gestions personnelle des petites clauses";
                this.secteurs = "Où vous le désirez, la cage où il fait chaud que lui a personnellement construit son papa";
                break;
            case 9 :
                this.prenom = "Rei";
                this.nom = "Ayanami";
                this.pretations = "Entendre des trucs en gobant tout, pilotage d'eva, soutien de Shinji";
                this.secteurs = "Tokyo, Tokyo 2, Tokyo 3, QG de la NERV, appart pourri";
                break;
            case 10 :
                this.prenom = "Père";
                this.nom = "Noël";
                this.pretations = "Distribution de cadeaux, mangeage de cookie";
                this.secteurs = "Cheminées diverses, tréneau, pôle nord, et un peu partout parce que oui, il peut !";
                break;
            case 11 :
                this.prenom = "Michou";
                this.nom = "Starfoullah";
                this.pretations = "Kessekoi ton histoire de prestation, kesseta à me faire chier toi t'as vu, tu cherches à méfu ?";
                this.secteurs = "Seine Saint-Denis, neuf deux chez momo, neuf un chez mamoud, neuf sept chez Homard, neuf trois chez Steevy de loft story";
                break;
            case 12 :
                this.prenom = "Saga";
                this.nom = "du Gémeau";
                this.pretations = "Another dimension, Athéna exclamation";
                this.secteurs = "Maison du grand Pope, sanctuaire d'Athéna, mais surtout pas dans la maison des gémeaux !";
                break;

                //Ajouter kenny XD
        }
    }

    @Override
    public String toString(){
        String text = this.nom + " " + this.prenom + " " + this.note + " " + " " + this.secteurs +
                " " + this.pretations + " " + this.coiffeuseID;
        return text;
    }
}
