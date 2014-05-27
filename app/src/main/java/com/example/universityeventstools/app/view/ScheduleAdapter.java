package com.example.universityeventstools.app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.universityeventstools.app.R;
import com.example.universityeventstools.app.model.InternalPeriod;

import java.util.ArrayList;

/**
 * Created by ykoby_000 on 20.05.2014.
 */
public class ScheduleAdapter extends BaseAdapter {
    ArrayList<InternalPeriod> internalPeriods;
    private Context context;
    private LayoutInflater layoutInflater;

    ScheduleAdapter(Context context, ArrayList<InternalPeriod> internalPeriods) {
        this.context = context;
        this.internalPeriods = internalPeriods;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return internalPeriods.size();
    }

    @Override
    public Object getItem(int position) {
        return internalPeriods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_schedule, parent, false);
        }

        InternalPeriod internalPeriod = getInternalPeriod(position);
        ((TextView) view.findViewById(R.id.dayOfWeek)).setText(internalPeriod.getDay() + ": " + internalPeriod.getPeriodNumber());
        ((TextView) view.findViewById(R.id.discipline)).setText(internalPeriod.getDiscipline());
        ((TextView) view.findViewById(R.id.auditorium)).setText(internalPeriod.getAuditoriumLocation() + " " + internalPeriod.getAuditoriumNumber() + " " + internalPeriod.getLecturer());
        if (internalPeriod.getPeriodType().equals("period")) {
            ((ImageView) view.findViewById(R.id.periodType)).setImageResource(R.drawable.ic_lecture);
        } else {
            ((ImageView) view.findViewById(R.id.periodType)).setImageResource(R.drawable.ic_event);
        }

        return view;
    }

    InternalPeriod getInternalPeriod(int position) {
        return ((InternalPeriod) getItem(position));
    }
}
