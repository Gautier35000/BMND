package com.juju.bndapplication.requete.post;

import com.google.gson.Gson;
import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.ResultBean;
import com.juju.bndapplication.outils.AccesHTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adresse {
    private final static String URL = "http://jeanmarie-gautier.fr/integration/request.php";
    public static ArrayList<AdresseBean>validerAdresse(AdresseBean adresse)throws Exception{
        String voie = adresse.getVoie();
        String numero= String.valueOf(adresse.getNumero());
        String ville=adresse.getVille();
        String cp= String.valueOf(adresse.getCp());
        String post = "";

        if(adresse.getAdresseID() != 0){
             post="upload_adress";
            String id= String.valueOf(adresse.getAdresseID());
        }else{
             post="record_adress";
            String id = "0";
        }
        String json ="";

        Gson gson= new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key",post);
        valeurs.put("voie",voie);
        valeurs.put("ville",ville);
        valeurs.put("cp",cp);
        String requete = gson.toJson(valeurs);

        try{
            json= AccesHTTP.sendGetOkHttpRequest(URL,requete);
        }catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson2 = new Gson();
        ResultBean resultBean = gson2.fromJson(json, ResultBean.class);


        if (resultBean.getErrors() != null) {
            throw new Exception(resultBean.getErrors().getMessage());
        } else {
            return resultBean.getResult_adresse();
        }


    }
}
