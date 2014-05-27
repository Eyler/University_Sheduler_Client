package com.example.universityeventstools.app.view;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.controller.*;
import com.example.universityeventstools.app.model.*;
import com.example.universityeventstools.app.util.EmailValidator;
import com.example.universityeventstools.app.util.PasswordValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class ScheduleActivity extends TabActivity implements TabHost.OnTabChangeListener, View.OnClickListener {
    private static final String PERIODS_TAB = "Розклад";
    private static final String EVENTS_TAB = "Події";
    private static final String EVENT_CONFIG_TAB = "Керування подіями";

    private TabHost tabHost;
    private ListView periods;
    private ListView events;
    private RelativeLayout eventsConf;
    private Button cret;
    private Button del;

    private ArrayAdapter<String> groupsAdapter;
    private ArrayAdapter<String> dayAdapter;
    private ArrayAdapter<String> auditAdapter;
    private ArrayAdapter<String> numAdapter;
    private List<ServiceAuditoriumPeriod> auditoriums;
    private List<Auditoriums> auditor;
    private EventsController eventsControll;
    private EditText descr;
    private Spinner groupsSpinner;
    private Spinner daySpinner;
    private Spinner numSpinner;
    private Spinner auditSpinner;
    private ServiceSchedule eventsSchedule;
    private TextView messageq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule);
        ServiceSchedule serviceSchedule = null;
        eventsControll = new EventsController();
        ScheduleController scheduleController = new ScheduleController();
        periods = (ListView) findViewById(R.id.scheduleList);
        eventsConf = (RelativeLayout) findViewById(R.id.eventsLayout);
        events = (ListView) findViewById(R.id.eventsList);

        descr = (EditText) findViewById(R.id.descriptionInput);
        groupsSpinner = (Spinner) findViewById(R.id.groupp);
        daySpinner = (Spinner) findViewById(R.id.dayy);
        numSpinner = (Spinner) findViewById(R.id.numss);
        auditSpinner = (Spinner) findViewById(R.id.auditoriums);

        try {
            serviceSchedule = scheduleController.getSchedule(getIntent().getExtras().getInt("groupId"));
        } catch (IOException e) {
        }

        if (serviceSchedule == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("message", "Відсутній інтернет");
            intent.putExtra("messageColor", "red");
            startActivity(intent);
        } else {


            messageq = (TextView) findViewById(R.id.messageq);
            List<ServicePeriod> servicePeriodsList = serviceSchedule.getServicePeriod();


            ArrayList<InternalPeriod> internalPeriods = new ArrayList<InternalPeriod>();


            InstituteGroupsProvider instituteGroupsProvider = new InstituteGroupsProvider();
            Map<String, List<String>> tmp = instituteGroupsProvider.getInstituteGroups();
            ArrayList<String> groups = new ArrayList<String>();
            for (List<String> tt : tmp.values()) {
                groups.addAll(tt);
            }

            groupsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, groups);
            groupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            groupsSpinner.setAdapter(groupsAdapter);
            groupsSpinner.setPrompt("Група");

            ArrayList<String> days = new ArrayList<String>();
            days.add("Пн");
            days.add("Вт");
            days.add("Ср");
            days.add("Чт");
            days.add("Пт");

            dayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, days);
            dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            daySpinner.setAdapter(dayAdapter);
            daySpinner.setPrompt("День");

            final FreeAuditoriumsProvider freeAuditoriumsProvider = new FreeAuditoriumsProvider();

            ArrayList<String> num = new ArrayList<String>();
            num.add("1");
            num.add("2");
            num.add("3");
            num.add("4");
            num.add("5");

            numAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, num);
            numAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            numSpinner.setAdapter(numAdapter);
            numSpinner.setPrompt("Пара");

            numSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {

                    auditoriums = freeAuditoriumsProvider.getFreeAuditoriums(daySpinner.getSelectedItem().toString());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            auditoriums = freeAuditoriumsProvider.getFreeAuditoriums(daySpinner.getSelectedItem().toString());
            auditor = new ArrayList<Auditoriums>();
            ArrayList<String> audit = new ArrayList<String>();
            for (ServiceAuditoriumPeriod serviceAuditorium : auditoriums) {
                if (serviceAuditorium.getPeriodNumber().equals(numSpinner.getSelectedItem().toString())) {
                    auditor.addAll(serviceAuditorium.getAuditoriums());
                }
            }

            for (Auditoriums auditoriums1 : auditor) {
                audit.add(auditoriums1.getAuditoriumLocation() + " " + auditoriums1.getAuditoriumNumber());
            }

            auditAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, audit);
            auditAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            auditSpinner.setAdapter(auditAdapter);
            auditSpinner.setPrompt("Аудиторія");

            cret = (Button) findViewById(R.id.createButton);
            cret.setOnClickListener(this);
            del = (Button) findViewById(R.id.deletedButton);
            cret.setOnClickListener(this);


            for (ServicePeriod servicePeriod : servicePeriodsList) {
                internalPeriods.add(new InternalPeriod(
                        servicePeriod.getDay(),
                        servicePeriod.getPeriodNumber(),
                        servicePeriod.getDiscipline(),
                        servicePeriod.getLecturer(),
                        servicePeriod.getServiceAuditorium().getAuditoriumLocation(),
                        servicePeriod.getServiceAuditorium().getAuditoriumNumber(), servicePeriod.getPeriodType()));
            }

            try {
                eventsSchedule = eventsControll.getSchedule(getIntent().getExtras().getInt("groupId"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<ServicePeriod> serviceEventsList = new LinkedList<ServicePeriod>();
            if (eventsSchedule != null) {
                serviceEventsList = eventsSchedule.getServicePeriod();
            }
            //List<ServicePeriod>
            ArrayList<InternalPeriod> eventsPer = new ArrayList<InternalPeriod>();
            for (ServicePeriod servicePeriod : serviceEventsList) {
                eventsPer.add(new InternalPeriod(
                        servicePeriod.getDay(),
                        servicePeriod.getPeriodNumber(),
                        servicePeriod.getDiscipline(),
                        servicePeriod.getLecturer(),
                        servicePeriod.getServiceAuditorium().getAuditoriumLocation(),
                        servicePeriod.getServiceAuditorium().getAuditoriumNumber(), servicePeriod.getPeriodType()));
            }
            final ScheduleAdapter eventsAdapter = new ScheduleAdapter(this, eventsPer);
            events.setAdapter(eventsAdapter);

            final ScheduleAdapter periodAdapter = new ScheduleAdapter(this, internalPeriods);

            periods.setAdapter(periodAdapter);
            tabHost = getTabHost();
            tabHost.setOnTabChangedListener(this);

            tabHost.addTab(tabHost.newTabSpec(PERIODS_TAB).setIndicator(PERIODS_TAB).setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return periods;
                }
            }));


            tabHost.addTab(tabHost.newTabSpec(EVENTS_TAB).setIndicator(EVENTS_TAB).setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return events;
                }
            }));

            tabHost.addTab(tabHost.newTabSpec(EVENT_CONFIG_TAB).setIndicator(EVENT_CONFIG_TAB).setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return eventsConf;
                }
            }));
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        if (tabId.equals(PERIODS_TAB)) {
            eventsConf.setVisibility(View.GONE);
            events.setVisibility(View.GONE);
        } else if (tabId.equals(EVENTS_TAB)) {
            eventsControll = new EventsController();
            try {
                eventsSchedule = eventsControll.getSchedule(getIntent().getExtras().getInt("groupId"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<ServicePeriod> serviceEventsList = new LinkedList<ServicePeriod>();
            if (eventsSchedule != null) {
                serviceEventsList = eventsSchedule.getServicePeriod();
            }
            ArrayList<InternalPeriod> eventsPer = new ArrayList<InternalPeriod>();
            for (ServicePeriod servicePeriod : serviceEventsList) {
                eventsPer.add(new InternalPeriod(
                        servicePeriod.getDay(),
                        servicePeriod.getPeriodNumber(),
                        servicePeriod.getDiscipline(),
                        servicePeriod.getLecturer(),
                        servicePeriod.getServiceAuditorium().getAuditoriumLocation(),
                        servicePeriod.getServiceAuditorium().getAuditoriumNumber(), servicePeriod.getPeriodType()));
            }
            final ScheduleAdapter eventsAdapter = new ScheduleAdapter(this, eventsPer);
            events.setAdapter(eventsAdapter);

            eventsConf.setVisibility(View.GONE);
            events.setVisibility(View.VISIBLE);
        } else if (tabId.equals(EVENT_CONFIG_TAB)) {
            eventsConf.setVisibility(View.VISIBLE);
            events.setVisibility(View.GONE);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createButton:
                //ServiceAuditorium serviceAuditorium, String day, String discipline, String groupID, String lecturer, String periodID, String periodNumber, String periodType) {
                String tmp = auditSpinner.getSelectedItem().toString();
                InstituteGroupsProvider instituteGroupsProvider = new InstituteGroupsProvider();
                instituteGroupsProvider.getGroupIdByGroupName(groupsSpinner.getSelectedItem().toString());
                if (tmp == null || tmp.isEmpty()) {
                    messageq.setText("Виберіть аудиторію");
                    messageq.setTextColor(Color.RED);
                } else {
                    ServicePeriod servicePeriod = new ServicePeriod(new ServiceAuditorium(tmp.replace(" ", ""), tmp.split(" ")[0], tmp.split(" ")[1]), daySpinner.getSelectedItem().toString(), descr.getText().toString(), String.valueOf(instituteGroupsProvider.getGroupIdByGroupName(groupsSpinner.getSelectedItem().toString())), getIntent().getExtras().getString("username"), "", numSpinner.getSelectedItem().toString(), "event");
                    ServicePeriod servicePeriodCopy = new ServicePeriod(new ServiceAuditorium(tmp.replace(" ", ""), tmp.split(" ")[0], tmp.split(" ")[1]), daySpinner.getSelectedItem().toString(), descr.getText().toString(), String.valueOf(getIntent().getExtras().getInt("groupId")), groupsSpinner.getSelectedItem().toString(), "", numSpinner.getSelectedItem().toString(), "event");
                    //descr.getText();
                    EventsController eventsController = new EventsController();
                    if ((eventsController.createEvent(servicePeriod)) == 201 && (eventsController.createEvent(servicePeriodCopy) == 201)) {

                        messageq.setText("Подію створено");

                        messageq.setTextColor(Color.GREEN);


                    }
                    break;
                }
            case R.id.deletedButton:

                break;
        }
    }
}