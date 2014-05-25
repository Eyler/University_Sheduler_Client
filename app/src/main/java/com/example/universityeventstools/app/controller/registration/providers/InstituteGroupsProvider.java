package com.example.universityeventstools.app.controller.registration.providers;

import com.example.universityeventstools.app.model.Response;
import com.example.universityeventstools.app.model.ServiceGroup;
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
import java.util.*;

/**
 * Created by ykoby_000 on 19.05.2014.
 */
public class InstituteGroupsProvider {

    public Map<String, List<String>> getInstituteGroups() {
        final String groupsUrl = "http://env-4918584.jelastic.neohost.net/groups";

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(groupsUrl);
        Set<String> institutes = new LinkedHashSet<String>();

        Map<String, List<String>> instituteGroupsMapping = new HashMap<String, List<String>>();
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
                Response serviceGroups = gson.fromJson(tmp, Response.class);

                List<ServiceGroup> serviceGroupList = serviceGroups.getServiceGroup();

                for (ServiceGroup serviceGroup : serviceGroupList) {
                    institutes.add(serviceGroup.getInstitute());
                }

                for (String institute : institutes) {
                    List<String> groups = new LinkedList<String>();
                    for (ServiceGroup sg : serviceGroupList) {
                        if (sg.getInstitute().equals(institute)) {
                            groups.add(sg.getGroupName());
                        }
                    }
                    instituteGroupsMapping.put(institute, groups);
                }


            } else {
                //User does not exist
            }
        } catch (ClientProtocolException e) {
            // handle exception
        } catch (IOException e) {
            // handle exception
        }
        return instituteGroupsMapping;
    }

    public int getGroupIdByGroupName(String groupName) {
        final String groupsUrl = "http://env-4918584.jelastic.neohost.net/groups";

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(groupsUrl);

        int groupId = 0;

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
                Response serviceGroups = gson.fromJson(tmp, Response.class);

                List<ServiceGroup> serviceGroupList = serviceGroups.getServiceGroup();

                for (ServiceGroup serviceGroup : serviceGroupList) {
                    if (serviceGroup.getGroupName().equals(groupName)) {
                        groupId = Integer.parseInt(serviceGroup.getGroupID());
                    }
                }


            } else {
                //User does not exist
            }
        } catch (ClientProtocolException e) {
            // handle exception
        } catch (IOException e) {
            // handle exception
        }
        return groupId;
    }


}
