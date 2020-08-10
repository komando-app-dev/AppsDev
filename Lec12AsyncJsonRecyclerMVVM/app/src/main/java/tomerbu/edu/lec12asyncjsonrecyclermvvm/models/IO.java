package tomerbu.edu.lec12asyncjsonrecyclermvvm.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by tomerbuzaglo on 03/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class IO {
    public static String getHttps(String address) throws Exception {
        URL url = new URL(address);
        //base class for all connection
        //URLConnection con = url.openConnection();
        //http -> status code 200 = OK
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        //urlConnection.setConnectTimeout(15);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }//auto close resources

        return sb.toString();
    }
}
