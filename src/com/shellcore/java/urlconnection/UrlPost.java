package com.shellcore.java.urlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Cesar. 07/06/2017.
 */
public class UrlPost {

    public static void main(String[] args) throws Exception {
        // Check the input arguments, has to be the url and a value to post
        if (args.length != 2) {
            System.err.println("Usage: java Reverse http://<location-for-your-servlet/script> name-to-post");
            System.exit(1);
        }

        // Encode the value to post string to avoid problem with special characters
        String stringToPost = URLEncoder.encode(args[1], "UTF-8");

        // Create the url and the connection
        URL url = new URL(args[0]);
        URLConnection connection = url.openConnection();
        // Enable write capability on the url
        connection.setDoOutput(true);
        // Get the connection outputstream, write to it and close
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());

        // Write to the connection, the method write will do a http post to the url
        out.write("name=" + stringToPost);
        out.close();

        // Get the connection inputstream
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String welcomeMessage;
        // read the returned lines and print to standard output
        while ((welcomeMessage = in.readLine()) != null) {
            System.out.println(welcomeMessage);
        }
        in.close();
    }
}
