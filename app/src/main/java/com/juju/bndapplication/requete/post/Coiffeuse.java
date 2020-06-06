package com.juju.bndapplication.requete.post;

import com.google.gson.Gson;
import com.juju.bndapplication.models.CoiffeuseBean;
import com.juju.bndapplication.models.ResultBean;
import com.juju.bndapplication.outils.AccesHTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Coiffeuse {
    private final static String URL = "http://jeanmarie-gautier.fr/integration/request.php";

    public static ArrayList<CoiffeuseBean> randomCoiffeuse() throws Exception {
        String json = "";
        Gson gson = new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key", "random_coiffeuse");
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
            return resultBean.getResult_coiffeuse();
        }

    }

}

