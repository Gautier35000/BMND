package com.juju.bndapplication.requete.post;

import android.widget.EditText;

import com.juju.bndapplication.models.ResultBean;
import com.juju.bndapplication.models.UserBean;
import com.juju.bndapplication.outils.AccesHTTP;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserConnexion {
    private final static String URL = "http://jeanmarie-gautier.fr/integration/request.php";

    /**
     * @return
     * @throws Exception
     */
    public static ArrayList<UserBean> connexion(EditText mail, EditText mdp) throws Exception {
        String S_mail = mail.getText().toString();
        String S_mdp = mdp.getText().toString();
        String json ="";

        Gson gson1 = new Gson();
        final Map<String, String> valeurs = new HashMap<String, String>();
        valeurs.put("request", "request");
        valeurs.put("key","connexion");
        valeurs.put("mail", S_mail);
        valeurs.put("password", S_mdp);
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
