package com.example.universityeventstools.app.controller;

import com.example.universityeventstools.app.model.ServicePerson;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class RegisterController {

    public int register(ServicePerson servicePerson) {
        final String registerUrl = "http://env-4918584.jelastic.neohost.net/persons";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(registerUrl);

        Gson gson = new Gson();
        String request = gson.toJson(servicePerson);

        try {
            StringEntity entity = new StringEntity(request,HTTP.UTF_8);
            entity.setContentEncoding(HTTP.UTF_8);
            entity.setContentType("application/json");
            httpPut.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = null;

        try {
            response = httpClient.execute(httpPut);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.getStatusLine().getStatusCode();

    }
}
