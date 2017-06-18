package com.example.felipe.bartapp;

/**
 * Created by Felipe on 27/03/2017.
 */

import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class Connection{

    private final String USER_AGENT = "Mozilla/5.0";



    // HTTP GET request
    public String login(String userName,String password) throws Exception{

        String urlString = "https://lisawebservicetest.herokuapp.com/login";

        JSONObject postDataParams = new JSONObject();
        postDataParams.put("usuario", userName);
        postDataParams.put("senha", password);
        Log.e("params",postDataParams.toString());

        return request(urlString,postDataParams);

    }

    public String request(String urlString, JSONObject postDataParams) throws Exception{


        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(postDataParams.toString());

        writer.flush();
        writer.close();
        os.close();

        int responseCode=conn.getResponseCode();

        if (responseCode == HttpsURLConnection.HTTP_OK) {

            BufferedReader in=new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line="";

            while((line = in.readLine()) != null) {

                sb.append(line);
                break;
            }

            in.close();
            return sb.toString();

        }
        else {
            return new String("false : "+responseCode);
        }
    }

    /*
    public List<Music> findAllItems(JSONArray response) {

        List<Music> found = new LinkedList<Music>();

        try {


            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                found.add(new Music(obj.getString("chords"), obj.getString("band"), obj.getString("musicName")));
            }

        } catch (JSONException e) {
            // handle exception
        }

        return found;
    }
    */

}
