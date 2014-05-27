package com.example.universityeventstools.app.controller;

import com.example.universityeventstools.app.model.Auditoriums;
import com.example.universityeventstools.app.model.FreeAud;
import com.example.universityeventstools.app.model.ServiceAuditorium;
import com.example.universityeventstools.app.model.ServiceAuditoriumPeriod;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykoby_000 on 25.05.2014.
 */
public class FreeAuditoriumsProvider {

    public List<ServiceAuditoriumPeriod> getFreeAuditoriums(String day) {
        final String groupsUrl = "http://env-4918584.jelastic.neohost.net/persons/free_auditoriums/" + day;

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(groupsUrl);
        List<ServiceAuditoriumPeriod> auditoriumList = new ArrayList<ServiceAuditoriumPeriod>();
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();

            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {

                HttpEntity entity = response.getEntity();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                Gson gson = new Gson();
                entity.writeTo(out);
                out.close();
                String tmp = out.toString();
                FreeAud auditoriums = gson.fromJson(tmp, FreeAud.class);

                auditoriumList = auditoriums.getServiceAuditoriumPeriod();




            } else {
                //User does not exist
            }
        } catch (ClientProtocolException e) {
            // handle exception
        } catch (IOException e) {
            // handle exception
        }
        return auditoriumList;
    }
}
