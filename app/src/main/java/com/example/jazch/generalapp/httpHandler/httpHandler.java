package com.example.jazch.generalapp.httpHandler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class httpHandler {

    public httpHandler() {
    }

    public  String get() {
        String result = "";
        URL url = null;
        try {
            url = new URL("http://63.251.232.134/v0.1/media");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
             result = in.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return result;
    }

}
