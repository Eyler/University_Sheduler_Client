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
import com.example.universityeventstools.app.controller.InstituteLecturerProvider;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ykoby_000 on 19.05.2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LecturerFragment extends Fragment {
    private Map<String, List<String>> instituteLecturers;
    private ArrayAdapter<String> institutesAdapter;
    private List<String> lecturers;
    private Spinner lecturersSpinner;
    private int pos;
    //private String inst

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        View view = inflater.inflate(R.layout.fragment_lecturer, null);

        InstituteLecturerProvider instituteLecturerProvider = new InstituteLecturerProvider();
        instituteLecturers = instituteLecturerProvider.getInstituteLecturers();
        if (instituteLecturers.isEmpty() || instituteLecturers == null) {
            intent = new Intent(getActivity(),LoginActivity.class);
            intent.putExtra("message", "Відсутній інтернет");
            intent.putExtra("messageColor", "red");
            startActivity(intent);
        } else {
            List<String> institutes = new LinkedList<String>();
            institutes.addAll(instituteLecturers.keySet());

            institutesAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, institutes);
            institutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            final Spinner institutesSpinner = (Spinner) view.findViewById(R.id.departmentInput);
            institutesSpinner.setAdapter(institutesAdapter);
            institutesSpinner.setPrompt("Інститут");
            institutesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    lecturers = instituteLecturers.get(institutesSpinner.getSelectedItem());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            lecturers = instituteLecturers.get(institutesSpinner.getSelectedItem());

            ArrayAdapter<String> groupsAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, lecturers);
            groupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            lecturersSpinner = (Spinner) view.findViewById(R.id.surnameInput);
            lecturersSpinner.setAdapter(groupsAdapter);
            lecturersSpinner.setPrompt("Викладач");
            pos = lecturersSpinner.getSelectedItemPosition();
            lecturersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    pos = position;
                    getActivity().getIntent().putExtra("lectur", lecturersSpinner.getAdapter().getItem(pos).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        return view;

    }
}
