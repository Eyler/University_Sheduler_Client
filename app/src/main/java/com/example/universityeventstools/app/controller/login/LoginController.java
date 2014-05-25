package com.example.universityeventstools.app.controller.login;

import com.example.universityeventstools.app.model.ServicePerson;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class LoginController {

    public Integer login(ServicePerson servicePerson) throws IOException {
        final String loginUrl = "http://env-4918584.jelastic.neohost.net/persons/login";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(loginUrl);

        Gson gson = new Gson();
        String request = gson.toJson(servicePerson);
        ServicePerson responsePerson = null;

        StringEntity entity = new StringEntity(request);
        entity.setContentType("application/json;charset=UTF-8");
        httpPost.setEntity(entity);

        Integer callBack = 0;
        HttpResponse response = null;


        response = httpClient.execute(httpPost);

        StatusLine statusLine = response.getStatusLine();

        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {

            HttpEntity responseEntity = response.getEntity();
            if (entity != null) {
                responsePerson = gson.fromJson(EntityUtils.toString(responseEntity), ServicePerson.class);
                callBack = responsePerson.getGroupID();
            }
        }
        return callBack;
    }
}