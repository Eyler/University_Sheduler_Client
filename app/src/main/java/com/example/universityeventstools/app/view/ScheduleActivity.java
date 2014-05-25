package com.example.universityeventstools.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.controller.schedule.ScheduleController;
import com.example.universityeventstools.app.model.Schedule;
import com.example.universityeventstools.app.model.ScheduleTable;
import com.example.universityeventstools.app.model.ServicePeriod;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class ScheduleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Schedule schedule = null;
        ScheduleController scheduleController = new ScheduleController();
        try {
            schedule = scheduleController.getSchedule(getIntent().getExtras().getInt("groupId"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ServicePeriod> servicePeriodsList = schedule.getServicePeriod();
        List<ScheduleTable> scheduleTable = new LinkedList<ScheduleTable>();

        for (ServicePeriod servicePeriod : servicePeriodsList) {
            scheduleTable.add(new ScheduleTable(servicePeriod.getDay(),
                    servicePeriod.getPeriodNumber(),
                    servicePeriod.getDiscipline(),
                    servicePeriod.getLecturer(),
                    servicePeriod.getAuditorium().getAuditoriumLocation(),
                    servicePeriod.getAuditorium().getAuditoriumNumber()));
        }
//(String day, String periodNumber, String discipline, String lecturer, String auditoriumLocation, String auditoriumNumber)
        ListView listView = (ListView) findViewById(R.id.scheduleList);

        final ArrayAdapter<ScheduleTable> adapter = new ArrayAdapter<ScheduleTable>(this,
                android.R.layout.simple_list_item_1, scheduleTable);

        listView.setAdapter(adapter);

    }
}
