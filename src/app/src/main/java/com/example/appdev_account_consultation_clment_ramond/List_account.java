package com.example.appdev_account_consultation_clment_ramond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class List_account extends AppCompatActivity {

    public static TextView ACCOUNT_DISPLAY;
    public static Button REFRESH;
    private Context context = this;
    boolean CONNECTED;
    String FILENAME = "FileAcOU";
    String FILE_CONTENT;
    private static TextView INTERNET_STATUS;
    int GREEN;
    int RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GREEN = getResources().getColor(R.color.green);
        RED = getResources().getColor(R.color.red);
        setContentView(R.layout.activity_list_account);
        ACCOUNT_DISPLAY = findViewById(R.id.display_account);
        REFRESH = findViewById(R.id.refresh_button);
        INTERNET_STATUS = findViewById(R.id.OnlineOffline);

        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        CONNECTED = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (CONNECTED) {
            ConnectAndRetreiveData();
        }
        else{
            Toast message = Toast.makeText(context, "Device is not connected, data from storage", Toast.LENGTH_SHORT);
            message.show();
            INTERNET_STATUS.setText("Offline");
            INTERNET_STATUS.setTextColor(RED);
            try {
                FileInputStream fis = context.openFileInput(FILENAME);
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                StringBuilder stringBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    String line = reader.readLine();
                    while (line != null) {
                        stringBuilder.append(line).append('\n');
                        line = reader.readLine();
                    }
                } catch (IOException e) {
                    // Error occurred when opening raw file for reading.
                } finally {
                    ACCOUNT_DISPLAY.setText(stringBuilder.toString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void OnClickRefresh(View view) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        CONNECTED = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (CONNECTED) {
            ConnectAndRetreiveData();
        }
        else{
            Toast message = Toast.makeText(context, "Device is not connected, data from storage", Toast.LENGTH_SHORT);
            message.show();
            INTERNET_STATUS.setText("Offline");
            INTERNET_STATUS.setTextColor(RED);
        }
    }

    public void OnClickReturn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void ConnectAndRetreiveData() {
        httpsConnection loadingAccountData = new httpsConnection();
        loadingAccountData.execute();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                FILE_CONTENT = (String) ACCOUNT_DISPLAY.getText();
                try (FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)) {
                    fos.write(FILE_CONTENT.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast message = Toast.makeText(context, "Data stored !", Toast.LENGTH_SHORT);
                message.show();
            }
        }, 5000);
        INTERNET_STATUS.setText("Online");
        INTERNET_STATUS.setTextColor(GREEN);
    }

    public void Delete_data(View view) {
        try (FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)) {
            fos.write("".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast message = Toast.makeText(context, "Data deleted", Toast.LENGTH_SHORT);
        message.show();
    }
}