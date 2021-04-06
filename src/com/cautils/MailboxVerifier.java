package com.cautils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MailboxVerifier {

    private static final String REQUEST_SCHEME = "http://api.quickemailverification.com/v1/verify?email=%s&apikey=%s";
    private static final String API_KEY = "ae277f97353683cc11ca75a3c56ab902a8aece2709a1060a6319b219a2fb";
    public static final String GET_HTTP_METHOD = "GET";

    private MailboxVerifier() {
    }

    public static MailboxVerifier getInstance(){
        return new MailboxVerifier();
    }

    public String verifySingleMailbox(final String mailboxAddress) throws IOException {
        URL url = new URL(String.format(REQUEST_SCHEME, API_KEY, mailboxAddress));
        HttpURLConnection con = fetchURL(url, GET_HTTP_METHOD);
        return getResponse(con);
    }

    private HttpURLConnection fetchURL(URL url, String httpMethod){
        System.out.println("\nSending 'GET' request to URL : " + url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(httpMethod);
        return httpURLConnection;
    }

    private String getResponse(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);
        switch (responseCode){
            case 200:
                return parseSuccessfulResult(con);
            default:
                return "Error!";
        }
    }

    private String parseSuccessfulResult(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response.toString());
        return response.toString();
    }
}
