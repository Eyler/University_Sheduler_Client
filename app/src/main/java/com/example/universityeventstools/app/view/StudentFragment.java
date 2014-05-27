package com.example.universityeventstools.app.view;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.controller.InstituteGroupsProvider;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ykoby_000 on 19.05.2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StudentFragment extends Fragment {
    private Map<String, List<String>> instituteGroups;
    private ArrayAdapter<String> institutesAdapter;
    private List<String> groups;
    private Spinner groupsSpinner;
    private int pos;
    //private String institute;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();

        View view = inflater.inflate(R.layout.fragment_student, null);

        InstituteGroupsProvider instituteGroupsProvider = new InstituteGroupsProvider();
        instituteGroups = instituteGroupsProvider.getInstituteGroups();
        if (instituteGroups.isEmpty() || instituteGroups == null) {
            intent = new Intent(getActivity(),LoginActivity.class);
            intent.putExtra("message", "Відсутній інтернет");
            intent.putExtra("messageColor", "red");
            startActivity(intent);
        } else {
            List<String> institutes = new LinkedList<String>();
            institutes.addAll(instituteGroups.keySet());

            institutesAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, institutes);
            institutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            final Spinner institutesSpinner = (Spinner) view.findViewById(R.id.instituteInput);
            institutesSpinner.setAdapter(institutesAdapter);
            institutesSpinner.setPrompt("Інститут");
            institutesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    groups = instituteGroups.get(institutesSpinner.getSelectedItem());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            groups = instituteGroups.get(institutesSpinner.getSelectedItem());

            ArrayAdapter<String> groupsAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, groups);
            groupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            groupsSpinner = (Spinner) view.findViewById(R.id.groupInput);
            groupsSpinner.setAdapter(groupsAdapter);
            groupsSpinner.setPrompt("Група");
            pos = groupsSpinner.getSelectedItemPosition();
            groupsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    pos = position;
                    getActivity().getIntent().putExtra("group", groupsSpinner.getAdapter().getItem(pos).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        return view;
    }
}
