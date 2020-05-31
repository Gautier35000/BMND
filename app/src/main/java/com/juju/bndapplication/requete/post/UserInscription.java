package com.juju.bndapplication.requete.post;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;
import com.juju.bndapplication.models.ResultBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.outils.AccesHTTP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInscription {
    private final static String URL = "http://jeanmarie-gautier.fr/integration/request.php";


    public static ArrayList<UserBean> inscription(EditText pseudo, EditText mail, EditText mdp, EditText confirmMdp) throws Exception {
        String S_mdp = mdp.getText().toString();
        String S_confirmMdp = confirmMdp.getText().toString();

        String S_pseudo = pseudo.getText().toString();
        String S_mail = mail.getText().toString();
        String json ="";

        Gson gson1 = new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key", "inscription");
        valeurs.put("pseudo", S_pseudo);
        valeurs.put("mail", S_mail);
        valeurs.put("password", S_mdp);
        valeurs.put("password_control", S_confirmMdp);
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
            return resultBean.getResults();
        }

    }
}


