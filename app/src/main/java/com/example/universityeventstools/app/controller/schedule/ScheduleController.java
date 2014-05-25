package com.example.universityeventstools.app.controller.schedule;

import com.example.universityeventstools.app.model.Schedule;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * Created by ykoby_000 on 20.05.2014.
 */
public class ScheduleController {

    public ScheduleController() {
    }

    public Schedule getSchedule(int groupId) throws IOException {
        final String registerUrl = "http://env-4918584.jelastic.neohost.net/persons/" + groupId;

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(registerUrl);
        Gson gson = new Gson();
        Schedule schedule = null;

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                schedule = gson.fromJson(EntityUtils.toString(entity, HTTP.UTF_8), Schedule.class);

            }
        }
        return schedule;
    }
}
