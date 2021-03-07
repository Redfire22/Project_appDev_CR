package com.example.appdev_account_consultation_clment_ramond;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class httpsConnection extends AsyncTask<Void,Void,Void> {

    String data = "";
    String singleParsed = "";
    String dataParsed = "";
    String DATA;
    JSONArray ACCOUNT_LIST;
    JSONObject OBJECT = null;
    httpsConnection CONTEXT = this;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(BuildConfig.Base_URL);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            DATA = bufferedReader.readLine();
            ACCOUNT_LIST = new JSONArray(DATA);
            for (int i = 0; i<ACCOUNT_LIST.length(); i++) {
                JSONObject object = ACCOUNT_LIST.getJSONObject(i);

                data = data + object.getString("accountName") + "\n"
                        + "AMOUNT : " + object.getInt("amount") + "\n"
                        + "IBAN : " + object.getString("iban") + "\n"
                        + "CURRENCY : " + object.getString("currency")
                        + "\n\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        List_account.ACCOUNT_DISPLAY.setText(data);
    }
}