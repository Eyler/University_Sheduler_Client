package com.example.universityeventstools.app.view;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.example.universityeventstools.app.R;

/**
 * Created by ykoby_000 on 19.05.2014.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LecturerFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lecturer_fragment, null);


        //RegisterCotroller registerCotroller = new RegisterCotroller();
        //Set<String> institutes = registerCotroller.getInstitutes();

        //List<String> institutesList = new LinkedList<String>();
        //institutesList.addAll(institutes);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, institutesList);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) view.findViewById(R.id.departmentInput);
        //spinner.setAdapter(adapter);
        spinner.setPrompt("Кафедра");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Set<String> groups = registerCotroller.getGroups();

        //List<String> groupList = new LinkedList<String>();
        //groupList.addAll(groups);

        //ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, groupList);
       // adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1 = (Spinner) view.findViewById(R.id.surnameInput);
        //spinner1.setAdapter(adapter1);
        spinner1.setPrompt("Ім'я");
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Spinner spinner2 = (Spinner) view.findViewById(R.id.nameInput);
        //spinner1.setAdapter(adapter1);
        spinner2.setPrompt("Прізвище");
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
