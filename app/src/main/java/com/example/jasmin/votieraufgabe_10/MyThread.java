package com.example.jasmin.votieraufgabe_10;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jasmin on 22/12/2017.
 */

public class MyThread extends AsyncTask<String, Integer, String> {
TextView mOutput;
String json;
JSONObject jsonO;
JSONArray jsonA;
int x;
int counter;

    public MyThread(TextView atextParam){
        mOutput = atextParam;
    }
    @Override
    protected String doInBackground(String... urls) {
        for (String urlString : urls) {
            URL url = null;
            try {
                url = new URL(urlString);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = "";
                json ="";
                counter = 0;
                x = 0;
                while ((line = reader.readLine()) != null) {
                    json += line;
                }
                System.out.println("json=" + json);
                if (json.startsWith("{")){
                    try {
                        jsonO = new JSONObject(json);
                       if(jsonO.getString("status").equals("ok")){
                           json = "Erfolgreich gesendet";
                       }
                       else{
                           json = "Fehler beim Senden";
                       }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return json;
                }
                if (json.equals("[]")){
                    json = "Keine Nachrichten verfügbar";
                    return json;
                }
                jsonA  = new JSONArray(json);
                json = "";
                x = jsonA.length();
                for(int i = 0; i < x; i++){
                        try {
                            jsonO = jsonA.getJSONObject(i);
                            counter ++;
                            System.out.println("counter + "+ counter);
                            json = "\n" + "\n" + (counter) + ". Name:   " + jsonO.getString("userid") +  " - Text:   " + jsonO.getString("text") + json;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
            e.printStackTrace();
           }
        }
        return json;
    }

    protected void onPostExecute(String s) {
        System.out.println("JSON" + json);
        mOutput.setText(json);
    }
}
