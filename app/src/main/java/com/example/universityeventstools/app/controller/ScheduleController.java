package com.example.universityeventstools.app.controller;

import com.example.universityeventstools.app.model.ServicePeriod;
import com.example.universityeventstools.app.model.ServiceSchedule;
import com.example.universityeventstools.app.model.ServiceSingleSchedule;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ykoby_000 on 20.05.2014.
 */
public class ScheduleController {

    public ScheduleController() {
    }

    public ServiceSchedule getSchedule(int groupId) throws IOException {
        final String registerUrl = "http://env-4918584.jelastic.neohost.net/persons/" + groupId;

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(registerUrl);
        Gson gson = new Gson();
        ServiceSchedule serviceSchedule = null;

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String ent = EntityUtils.toString(entity, HTTP.UTF_8);
                try {
                    serviceSchedule = gson.fromJson(ent, ServiceSchedule.class);
                } catch (Exception e) {
                    List<ServicePeriod> tmp = new LinkedList<ServicePeriod>();
                    ServiceSingleSchedule tm = gson.fromJson(ent, ServiceSingleSchedule.class);
                    tmp.add(tm.getServicePeriod());
                    serviceSchedule = new ServiceSchedule();
                    serviceSchedule.setServicePeriod(tmp);

                }

            }
        }
        return serviceSchedule;
    }
}
