package com.juju.bndapplication.requete.post;

import com.google.gson.Gson;
import com.juju.bndapplication.models.AdresseBean;
import com.juju.bndapplication.models.OptionBean;
import com.juju.bndapplication.models.ResultBean;
import com.juju.bndapplication.outils.AccesHTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Option {
    private final static String URL = "http://jeanmarie-gautier.fr/integration/request.php";

    public static ArrayList<OptionBean> OptionSelect() throws Exception {
        String json="";
        Gson gson = new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key", "choix_options");
        String requete = gson.toJson(valeurs);

        try {
            json = AccesHTTP.sendGetOkHttpRequest(URL, requete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson2 = new Gson();
        ResultBean resultBean = gson2.fromJson(json, ResultBean.class);


        if (resultBean.getErrors() != null) {
            throw new Exception(resultBean.getErrors().getMessage());
        } else {
            return resultBean.getChoix_options();
        }

    }

}
