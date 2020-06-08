package com.juju.bndapplication.requete.post;

import com.google.gson.Gson;
import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.PrestationBean;
import com.juju.bndapplication.models.ResultBean;
import com.juju.bndapplication.outils.AccesHTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prestation {
    private final static String URL = "http://jeanmarie-gautier.fr/integration/request.php";

    public static ArrayList<PrestationBean> selectPrestation(AdresseBean adresse)throws Exception{
        String cp = String.valueOf(adresse.getCp());
        String json = "";

        Gson gson1 = new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key", "choix_prestations");
        valeurs.put("cp", cp);
        String requete = gson1.toJson(valeurs);

        try {
            json = AccesHTTP.sendGetOkHttpRequest(URL, requete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(json, ResultBean.class);


        if (resultBean.getErrors() != null) {
            throw new Exception(resultBean.getErrors().getMessage());
        } else {
            return resultBean.getChoix_prestations();
        }

    }


    public static ArrayList<PrestationBean> getGalleryPrestation()throws Exception{
        String json = "";

        Gson gson1 = new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key", "get_gallery_prestation");
        String requete = gson1.toJson(valeurs);

        try {
            json = AccesHTTP.sendGetOkHttpRequest(URL, requete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(json, ResultBean.class);


        if (resultBean.getErrors() != null) {
            throw new Exception(resultBean.getErrors().getMessage());
        } else {
            return resultBean.getChoix_prestations();
        }

    }
}
