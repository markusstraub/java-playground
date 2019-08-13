package com.github.mstraub.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * A complete Java class that shows how to open a URL, then read data (text)
 * from that URL, HttpURLConnection class (in combination with an
 * InputStreamReader and BufferedReader).
 *
 * @author alvin alexander, http://alvinalexander.com.
 *
 */
public class JavaHttpUrlConnectionReader {
    public static void main(String[] args) throws Exception {
        new JavaHttpUrlConnectionReader().post();
    }

    public JavaHttpUrlConnectionReader() {
        try {
            //String myUrl = "https://waalter-dev.ilogs.com/api/v1/users/me";
            // if your url can contain weird characters you will want to
            // encode it here, something like this:
            // myUrl = URLEncoder.encode(myUrl, "UTF-8");

            String results = post();
            System.out.println(results);
            
            results = doHttpUrlConnectionAction();
            System.out.println(results);
        } catch (Exception e) {
            // deal with the exception in your "controller"
        }
    }

    /**
     * Returns the output from the given URL.
     * 
     * I tried to hide some of the ugliness of the exception-handling in this
     * method, and just return a high level Exception from here. Modify this
     * behavior as desired.
     * 
     * @param desiredUrl
     * @return
     * @throws Exception
     */
    private String doHttpUrlConnectionAction() throws Exception {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            url = new URL("https://waalter-dev.ilogs.com/api/v1/users/me");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");
            
            connection.setRequestProperty("Authorization", "Bearer f7d6b7e6-1cd9-45f7-9dfa-053756239455");

            // uncomment this if you want to write output to this url
            // connection.setDoOutput(true);

            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
    
    private String post() throws Exception {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            // create the HttpURLConnection
            url = new URL("https://waalter-mobis-dev.ilogs.com/WebService/oauth/token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("POST");
            
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            // uncomment this if you want to write output to this url
            connection.setDoOutput(true);
            
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
            osw.write("grant_type=password&client_id=d6c382fc-f525-4467-a8c1-a05b664a614e&username=api.events&password=4pp$3v3nt");
            osw.flush();
            osw.close();
            os.close();  //don't forget to close the OutputStream

            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}